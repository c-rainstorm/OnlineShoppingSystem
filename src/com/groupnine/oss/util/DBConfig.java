package com.groupnine.oss.util;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

/**
 * 
 * 该类作用为从 xml 配置文件中读取数据库的配置信息 默认数据库为 MySQL
 * 
 * 配置文件在/config/mysql.xml
 * 
 * 可供配置的参数有 主机地址 端口号 数据库名称 数据库用户名 数据库密码
 * 
 * @author chen_swe
 *
 */
public class DBConfig {
    private String driver = "com.mysql.jdbc.Driver";
    private String host;
    private String port;
    private String username;
    private String password;
    private String name;

    /**
     * 在初始化时从文件中获取配置并保存
     */
    public DBConfig() {
        SAXBuilder jdomBuilder = new SAXBuilder();

        try {
            String configPath = DBConfig.class.getResource("/")
                    + "../../config/database-config.xml";

            Document document = jdomBuilder.build(configPath);

            Element root = document.getRootElement();

            setDriver(root.getChildText("driver").trim());
            setHost(root.getChildText("host").trim());
            setPort(root.getChildText("port").trim());
            setUsername(root.getChildText("username").trim());
            setPassword(root.getChildText("password").trim());
            setName(root.getChildText("name").trim());
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        System.out.println(new DBConfig().toString());
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DBConfig [driver=" + driver + ", host=" + host + ", port=" + port + ", username="
                + username + ", password=" + password + ", name=" + name + "]";
    }

}
