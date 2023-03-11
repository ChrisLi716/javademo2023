package com.chris.demo.clone.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther Chris Lee
 * @Date 12/25/2018 11:02
 * @Description
 */
@Data
@AllArgsConstructor
public class City implements Cloneable, Serializable {
	private String name;
	
	public City clone()
		throws CloneNotSupportedException {
		return (City)super.clone();
	}
}
