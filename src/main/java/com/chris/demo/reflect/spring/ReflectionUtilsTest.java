package com.chris.demo.reflect.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Chris
 * @date 2022-04-10 11:56 AM
 */
@Slf4j
public class ReflectionUtilsTest {

    /*
    private void injectServicesViaAnnotatedFields(final Object bean, final String beanName) {
        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            public void doWith(Field field) {
                ServiceReference s = AnnotationUtils.getAnnotation(field, ServiceReference.class);
                if (s != null && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                    try {
                        if (log.isDebugEnabled())
                            log.debug("Processing annotation [" + s + "] for [" + field + "] on bean [" + beanName + "]");
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        ReflectionUtils.setField(field, bean,
                                getServiceImporter(s, field.getType(), beanName).getObject());
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Error processing service annotation", e);
                    }
                }
            }
        });
    }*/
}
