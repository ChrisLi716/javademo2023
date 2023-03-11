package com.chris.demo.clone.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 11:01
 * @Description
 */
@Data
@AllArgsConstructor
public class Person implements Cloneable, Serializable {
    private String name;

    private int income;

    private City city;

    public Person shallowClone()
            throws CloneNotSupportedException {
        /*
         * as we know reference variables holds address of the object instead of object itself, which can also be referred from
         * other reference variables and if we change one other will reflect that change. So while cloning process
         * Object.clone() will copy address which person1.city is holding to person2.city
         */
        return (Person) super.clone();
    }

    public Person deepClone()
            throws CloneNotSupportedException {
        Person clonePerson = (Person) super.clone();
        // deep clone city
        clonePerson.setCity(this.city.clone());
        return clonePerson;
    }
}
