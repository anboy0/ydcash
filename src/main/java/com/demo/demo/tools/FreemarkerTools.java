package com.demo.demo.tools;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Freemark 生成打印模板
 */
public class FreemarkerTools {
    private static final String HTML_TEMPLATE_HEADER = "<!DOCTYPE HTML>\n" +
            "<html lang=\"en-US\">\n" +
            "<head>\n" +
            "\t<meta charset=\"UTF-8\">\n" +
            "\t<title></title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<script type=\"text/javascript\" src=\"http://ueditor.baidu.com/ueditor/ueditor.config.js\"></script>\n" +
            "\t<script type=\"text/javascript\" src=\"http://ueditor.baidu.com/ueditor/ueditor.all.js\"></script>\n" +
            "\t<script type=\"text/javascript\">\n" +
            "\t    var ue = UE.getEditor('container',{\n" +
            "\t\ttoolbars :[],\n" +
            "\t\tisShow:false,\n" +
            "\t\t});\n" +
            "\t\tue.ready(function() {\n" +
            "\t\tue.setContent(\"";
    private static final String HTML_TEMPLATE_FOOTER = "\");\n" +
            "\t\tue.execCommand( 'print' );\n" +
            "\t\t})\n" +
            "\t</script>\n" +
            "\t<div id='container'>\n" +
            "\t</div>\n" +
            "</body>\n" +
            "</html>";
    private static final String HTML_NEXT_PAGE = "<p style=\"page-break-before:always\"></p>";

    public static String getHtml(String ftlName, HashMap data, Writer out) {
        Configuration cfg = SpringContextHolder.getBean("freeMarkerTemplateConfig");
        try {
            Template template = cfg.getTemplate(ftlName, "utf-8");
            template.setEncoding("UTF-8");
            out.write(HTML_TEMPLATE_HEADER);
            template.process(data, out);
            out.write(HTML_TEMPLATE_FOOTER);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成打印文件
     *
     * @param ftlName 模板名称
     * @param data    模板数据
     * @return html数据
     * @throws Exception
     */
    public static String getHtmlData(String ftlName, HashMap data) throws Exception {
        Configuration cfg = SpringContextHolder.getBean("freeMarkerTemplateConfig");
        StringWriter wr = new StringWriter();
        Template template = cfg.getTemplate(ftlName, "UTF-8");
        template.setEncoding("UTF-8");
        template.process(data, wr);
        wr.flush();
        wr.close();
        return wr.getBuffer().toString();
    }

    /**
     * 批量生成打印数据
     *
     * @param ftlName 模板名称     默认请求的模板文件为 ftlName+'_zh'
     * @param data    模板数据
     * @return
     * @throws Exception
     */
    public static String getHtmlDataFromArrayList(String ftlName, ArrayList data) throws Exception {
        Configuration cfg = SpringContextHolder.getBean("freeMarkerTemplateConfig");
        StringWriter wr = new StringWriter();
        Template template = cfg.getTemplate(ftlName, "UTF-8");
        template.setEncoding("UTF-8");
        for (int i = 0; i < data.size(); i++) {
            HashMap map = new HashMap();
            map.put("data", data.get(i));
            template.process(map, wr);
            if (data.size() != 1 && i != data.size() - 1) {
                wr.write(HTML_NEXT_PAGE);
            }
        }
        wr.flush();
        wr.close();
        return wr.getBuffer().toString();
    }

}
