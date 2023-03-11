package com.chris.demo.hutool;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Lilun
 * @Date 2020-11-03 10:56
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonWithAlias {

    private String name;

    @Alias("aliasSubName")
    private String subName;

}
