package com.chris.annotation;


import java.lang.annotation.*;

@Target({ElementType.FIELD}) //仅允许作用于类属性之上
@Retention(RetentionPolicy.SOURCE) //只在编译期有效，最终不会打进class文件中
@Documented
public @interface TransientVersion {
}
