package com.chris.demo.reflect.Bean;

import java.lang.annotation.*;

/**
 * @Author Lilun
 * @Date 2020-11-30 17:46
 * @Description
 **/
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    String key() default "";
}
