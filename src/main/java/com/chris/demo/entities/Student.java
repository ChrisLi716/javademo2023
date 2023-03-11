package com.chris.demo.entities;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Chris
 * @date 2022-09-04 10:37 AM
 */
@Data
@Accessors(chain = true)
public class Student {
    private String name;
    private String subject;
    private Integer score;
}
