package com.chris.demo.reflect.type;

import com.chris.demo.reflect.type.bean.Person;
import com.chris.demo.reflect.type.bean.TestType;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TypeTest {

    /**
     * integerList 属性的原生类型为 interface java.util.List, 参数类型有:class java.lang.Integer
     * belongs 属性的原生类型为 interface java.util.Map, 参数类型有:class java.lang.String class java.lang.Integer
     */
    @Test
    public void test1() {
        Class<Person> clz = Person.class;
        Field[] declaredFields = clz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            if (field.getGenericType() instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                System.out.printf("%s 属性的原生类型为 %s, 参数类型有:", field.getName(), parameterizedType.getRawType());
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        });
    }


    /**
     * [java.util.List, java.io.Serializable]
     * K
     * class com.chris.reflect.type.bean.TestType
     * -----------------
     * [java.lang.Object]
     * V
     * class com.chris.reflect.type.bean.TestType
     * -----------------
     */
    @Test
    public void test2() {
        TypeVariable[] v = TestType.class.getTypeParameters();

        for (TypeVariable t : v) {
            System.out.println(Arrays.stream(t.getBounds()).map(Type::getTypeName).collect(Collectors.toList()));
            System.out.println(t.getName());
            System.out.println(t.getGenericDeclaration());
            System.out.println("-----------------");
        }

    }

}
