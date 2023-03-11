package com.chris.demo.reflect;

import com.chris.demo.reflect.Bean.HumanBeing;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther Chris Lee
 * @Date 12/24/2018 12:38
 * @Description
 */
class ReflectUtils {
	
	/**
	 * build the reqeust string of all fileds alphabetically
	 *
	 * @param obj the Object which is using to build the request string
	 * @param <T> the Reference type of Object which is using to build the request string
	 * @return request string
	 * @throws Exception relection excetion will be processed by Caller
	 */
	static <T extends HumanBeing> String buildRequestStr(T obj)
		throws Exception {
		StringBuilder temp = new StringBuilder();
		Class cls = obj.getClass();
		Field[] superFileds = cls.getSuperclass().getDeclaredFields();
		Field[] subFields = cls.getDeclaredFields();
		
		List<Field> allFields = new ArrayList<>();
		allFields.addAll(Arrays.asList(superFileds));
		allFields.addAll(Arrays.asList(subFields));
		
		// sort the field alphabetically
		allFields.sort(Comparator.comparing(Field::getName));
		
		if (!allFields.isEmpty()) {
			for (Field field : allFields) {
				if (null != field) {
					field.setAccessible(true);
					String fieldName = field.getName();
					String fieldValue = "";
					Object value = field.get(obj);
					if (null != value) {
						if (field.getType() == List.class) {
							List list = (List)value;
							fieldValue = list.toString();
						}
						else if (field.getType() == Class.class) {
							fieldValue = ((Class)value).getTypeName();
						}
						else {
							fieldValue = (String)value;
						}
					}
					if (StringUtils.isNotEmpty(fieldName)) {
						if (temp.length() > 0) {
							temp.append("&").append(fieldName).append("=").append(fieldValue);
						}
						else {
							temp.append(fieldName).append("=").append(fieldValue);
						}
					}
				}
			}
		}
		return temp.toString();
	}
}
