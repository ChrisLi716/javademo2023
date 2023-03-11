package com.chris;

import java.io.File;
import java.net.URL;

/**
 * @Auther Chris Lee
 * @Date 10/18/2018 18:11
 * @Description
 */
public class SysPathUtils {


    public static String getNewLineCharacter() {
        return System.getProperty("line.separator");
    }

    public static void main(String[] args) {
        System.out.println(File.separator);
        String path = getRootPath(SysPathUtils.class);
        System.out.println(path);
    }

    public static String getRootPath(Class clz) {
        // 第一种1：获取类加载的根路径 /D:/IdeaWorksapce/javademo/target/classes/
        String rootPath = clz.getResource("/").getPath();
        System.out.println(rootPath);
        return rootPath;
    }

    public static String getCurrentClzPath(Class clz) {
        // 第一种2：获取类加载的当前天路径,或者是"." /D:/IdeaWorksapce/javademo/target/classes/com/chris/lambda/
        String rootPath = clz.getResource("").getPath();
        System.out.println(rootPath);
        return rootPath;
    }

    public static String getProjectPath() throws Exception {
        // 第二种：获取项目路径 D:\IdeaWorksapce\javademo
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);

        // 第四种：获取当前工程路径 D:\IdeaWorksapce\javademo
        // System.out.println(System.getProperty("user.dir"));
        return courseFile;
    }

    public static String getClzRootURL(Class clz) {
        //获取类加载的根路径/D:/IdeaWorksapce/javademo/target/classes/
        URL xmlpath = clz.getClassLoader().getResource("");
        System.out.println(xmlpath);
        if (null != xmlpath) {
            return xmlpath.getPath();
        }
        return null;
    }

}
