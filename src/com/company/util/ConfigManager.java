package com.company.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*加载配置文件，便于项目的部署与发布*/
public class ConfigManager {
    private static final Properties prop= new Properties();
    /*加载静态资源，I/O流导入配置文件内容*/
    static {
        InputStream io = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*创建获取配置文件内容的方法*/
    public static String getProp(String key){
        return prop.getProperty(key);
    }
}
