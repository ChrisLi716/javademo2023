package com.chris.demo.io;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther Chris Lee
 * @Date 10/18/2018 17:51
 * @Description
 */
@Data
@AllArgsConstructor
public class FileBean {

    private String fileName;

    private String extension;

    private String filePath;

    private byte[] content;
}
