package com.demo.demo.sysConfig;

import com.demo.demo.sys.entity.SysPrintTemplate;
import com.demo.demo.sys.service.ISysPrintTemplateService;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfig {
    @Autowired
    ISysPrintTemplateService sysPrintTemplateService;

    @Bean("freeMarkerTemplateConfig")
    public Configuration getCfg() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        /*try {
            System.out.println(new File("./").getAbsolutePath());
            cfg.setDirectoryForTemplateLoading(new File("./"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateLoader(new DbTemplateLoader());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }

    public class DbTemplateLoader implements TemplateLoader {

        @Override
        public Object findTemplateSource(String s) throws IOException {
            Map search = new HashMap<>();
            search.put("name", s);
            search.put("status", 1);
            List<SysPrintTemplate> lists = sysPrintTemplateService.selectByMap(search);
            if (lists != null && lists.size() > 0) {
                return lists.get(0);
            }
            return null;
        }

        @Override
        public long getLastModified(Object o) {
            return ((SysPrintTemplate) o).getModifyTime().getTime();
        }

        @Override
        public Reader getReader(Object o, String s) throws IOException {
            String txt = ((SysPrintTemplate) o).getText();
            Pattern p = Pattern.compile(">(\\s*|\n|\t|\r)<");
            Matcher m = p.matcher(txt);
            txt = m.replaceAll("><");
            return new StringReader(txt);
        }

        @Override
        public void closeTemplateSource(Object o) throws IOException {

        }
    }
}
