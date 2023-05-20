package com.chris.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Chris
 * @date 2022-09-04 10:37 AM
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private String subject;
    private Integer score;
}
