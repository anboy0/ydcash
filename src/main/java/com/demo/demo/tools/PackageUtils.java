package com.demo.demo.tools;

import com.demo.demo.bus.Export.ExportReceipt;
import com.demo.demo.bus.Import.base.BaseImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackageUtils {
    /**
     * jar中的文件路径分隔符
     */
    private static final char SLASH_CHAR = '/';
    /**
     * 包名分隔符
     */
    private static final char DOT_CHAR = '.';

    private static final Logger LOG = LoggerFactory.getLogger(PackageUtils.class);
    private static final String fileSeparator = "\\";

    /**
     * 获取某包下（包括该包的所有子包）所有类
     *
     * @param packageName 包名
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName) {
        return getClassName(packageName, false);
    }

    /**
     * 获取某包下所有类
     *
     * @param packageName  包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames = null;

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        LOG.info("package url: {}", url);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage);
            } else if (type.equals("jar")) {
                fileNames = getClassNameByJar(url.getPath(), childPackage);
            }
        } else {
            fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(),
                    packagePath, childPackage);
        }
        return fileNames;
    }
    private static String getFileSeparator(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return "\\";
        }else {
            return "/";
        }
    }
    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath     文件路径
     * @param className    类名集合
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                }
            } else {
                final String classesSeparator = "classes" + getFileSeparator();
                String childFilePath = childFile.getPath();
                System.out.println("childFilePath:"+childFilePath);
                if (childFilePath.endsWith(".class")) {
                    int index = childFilePath.lastIndexOf(classesSeparator);
                    if (index != -1) {
                        childFilePath = childFilePath.substring(index + classesSeparator.length(), childFilePath.length() - 6);
                        childFilePath = childFilePath.replace(getFileSeparator(), ".");
                        myClassName.add(childFilePath);
                    }
                }
            }
        }

        return myClassName;
    }

    /**
     * 从jar获取某包下所有类
     *
     * @param jarPath      jar文件路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByJar(String jarPath,
                                                  boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        String[] jarInfo = jarPath.split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        int ii =  jarPath.lastIndexOf("!");
        String packagePath= jarPath.substring(ii+1,jarPath.length());
        try (JarFile jarFile = new JarFile(jarFilePath);) {
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    final String classesSeparator = "classes/";
                    if (entryName.endsWith(".class")&& entryName.contains(packagePath)) {
                        int index = entryName.lastIndexOf(classesSeparator);
                        if (index != -1) {
                            entryName = entryName.substring(index + classesSeparator.length(), entryName.length() - 6);
                            entryName = entryName.replace("/", ".");
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return myClassName;
    }

    /**
     * 从所有jar中搜索该包，并获取该包下所有类
     *
     * @param urls         URL集合
     * @param packagePath  包路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByJars(URL[] urls,
                                                   String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));
            }
        }
        return myClassName;
    }

    public static String getClassShortName(String clsName) {
        int index = clsName.lastIndexOf(".");
        return clsName.substring(index + 1);
    }

    public static void main(String[] args) {
        System.out.println(ExportReceipt.class.getPackage().getName());
        Map<String,Object> info = SpringContextHolder.getBeans(BaseImport.class);
        List<String> clsInfos = PackageUtils.getClassName(ExportReceipt.class.getPackage().getName());
        for (int i = 0; i < clsInfos.size(); i++) {
            System.out.println(clsInfos.get(i));
            System.out.println(getClassShortName(clsInfos.get(i)));
        }
    }
}
