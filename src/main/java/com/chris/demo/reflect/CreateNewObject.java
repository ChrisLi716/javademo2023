package com.chris.demo.reflect;

import com.chris.demo.reflect.Bean.Male;

/**
 * @Auther Chris Lee
 * @Date 12/24/2018 14:20
 * @Description
 */
public class CreateNewObject {

    public static void main(String[] args)
            throws Exception {
        try {
            // by Class.newInstance()
            Male male = Male.class.newInstance();
            System.out.println(ReflectUtils.buildRequestStr(male));

            // Constructor.newInstance()
            Male male2 = Male.class.getConstructor(String.class, String.class, String.class).newInstance("Chris", "US", "MALE");
            System.out.println(ReflectUtils.buildRequestStr(male2));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
