package com.chris.jvm;

import org.junit.Test;

public class ClassLoaderTest {

    /**
     * file:/C:/Program%20Files/Java/jdk1.8.0_361/jre/lib/resources.jar
     * file:/C:/Program%20Files/Java/jdk1.8.0_361/jre/lib/rt.jar
     * file:/C:/Program%20Files/Java/jdk1.8.0_361/jre/lib/jsse.jar
     * file:/C:/Program%20Files/Java/jdk1.8.0_361/jre/lib/jce.jar
     * file:/C:/Program%20Files/Java/jdk1.8.0_361/jre/lib/charsets.jar
     * file:/C:/Program%20Files/Java/jdk1.8.0_361/jre/lib/jfr.jar
     * file:/C:/Program%20Files/Java/jdk1.8.0_361/jre/classes
     * null
     * ----------------------扩展类加载器------------------------------
     * C:\Program Files\Java\jdk1.8.0_361\jre\lib\ext
     * C:\WINDOWS\Sun\Java\lib\ext
     * sun.misc.Launcher$ExtClassLoader@6433a2
     */
    @Test
    public void clsLoaderTest01() {
        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2

        // 获取系统类加载器的上层加载器，即扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader); // sun.misc.Launcher$ExtClassLoader@3fee733d

        // 获取扩展类加载器的上层加载器, 发现获取不到
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader); // null


        // 获取自定义类ClassLoaderTest的类加载器, 默认合理使用系统类加载器加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2

        // 获取String类的类加载器，发现获取不到，间接说明了是由引导类加载器加载的，也说明Java的核心类库都是由引导类加载器进行加载的
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);// null
    }


    @Test
    public void clsLoaderTest02() throws ClassNotFoundException {
        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

        ClassLoader parent = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(parent);
    }

}

