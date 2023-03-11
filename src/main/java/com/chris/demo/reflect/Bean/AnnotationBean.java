package com.chris.demo.reflect.Bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chris
 * @date 2022-04-10 11:13 AM
 */
@Data
@NoArgsConstructor
public class AnnotationBean {

    private String value;

    @AnnotationTest(key = "import")
    private String attr;
}
