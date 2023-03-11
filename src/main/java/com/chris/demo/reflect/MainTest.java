package com.chris.demo.reflect;

import cn.hutool.core.collection.CollUtil;
import com.chris.demo.reflect.Bean.*;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/24/2018 12:47
 * @Description
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        HumanBeing.desc();
        Male.desc();
        Male male = new Male("Chris", "CN", "Male");
        male.setCarList(Arrays.asList("Lamborghini", "VM"));
        String descStr = ReflectUtils.buildRequestStr(male);
        System.out.println(descStr);
    }


    @Test
    public void testConstructor() throws Exception {
        Class<?> clz = Male.class;
        Constructor<?>[] constructors = clz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            String name = constructor.getName();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            System.out.println(Arrays.toString(parameterTypes));
        }

        Constructor<?> declaredConstructor = clz.getDeclaredConstructor(String.class, String.class);
        declaredConstructor.setAccessible(true);
        Male m = (Male) declaredConstructor.newInstance("Chris", "ShannXi");
        System.out.println(m.toString());
    }


    @Test
    public void testField() throws Exception {
        Class<?> clz = Male.class;

        Constructor<?> declaredConstructor = clz.getDeclaredConstructor(String.class, String.class, String.class);
        declaredConstructor.setAccessible(true);
        Male m = (Male) declaredConstructor.newInstance("Chris", "ShannXi", "man");

        Field[] declaredFields = clz.getDeclaredFields();
        Field[] superClzDeclaredFields = clz.getSuperclass().getDeclaredFields();

        ArrayList<Field> fields = CollUtil.newArrayList(declaredFields);
        fields.addAll(Arrays.asList(superClzDeclaredFields));

        for (Field declaredField : fields) {
            declaredField.setAccessible(true);
            Object o = declaredField.get(m);
            System.out.println("value:" + o);
        }

        Field name = clz.getSuperclass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(m, "John");

        Object nameValue = name.get(m);
        System.out.println(nameValue);

    }


    @Test
    public void testAnnotation() throws IllegalAccessException {
        AnnotationBean annoBean = new AnnotationBean();
        annoBean.setValue("xxx");
        annoBean.setAttr("eee");

        Class cls = annoBean.getClass();
        Field[] superFileds = cls.getSuperclass().getDeclaredFields();
        Field[] subFields = cls.getDeclaredFields();

        List<Field> allFields = new ArrayList<>();
        allFields.addAll(Arrays.asList(superFileds));
        allFields.addAll(Arrays.asList(subFields));

        if (!allFields.isEmpty()) {
            for (Field field : allFields) {
                if (null != field && field.isAnnotationPresent(AnnotationTest.class)) {
                    AnnotationTest annotation = field.getAnnotation(AnnotationTest.class);
                    AnnotatedType annotatedType = field.getAnnotatedType();
                    Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                    String key = annotation.key();
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    String fieldValue = "";
                    Object value = field.get(annoBean);
                    if (null != value) {
                        if (field.getType() == List.class) {
                            List list = (List) value;
                            fieldValue = list.toString();
                        } else if (field.getType() == Class.class) {
                            fieldValue = ((Class) value).getTypeName();
                        } else {
                            fieldValue = (String) value;
                        }
                    }

                }
            }
        }
    }


}
