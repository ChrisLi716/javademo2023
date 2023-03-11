package com.chris.demo.tester;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ExtractWords {

    public static void main(String[] args) throws Exception {
        String newLineSeperator = System.getProperty("line.separator");

        String inputFileName = "C:\\Users\\ad\\Desktop\\words.txt";
        String outputFileName = "C:\\Users\\ad\\Desktop\\newWords.txt";
        FileReader fileReader = new FileReader(new File(inputFileName));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String oneLine = null;
        int skipLines = 4;
        StringBuilder sb = new StringBuilder();

        do {
            for (int i = 0; i <= skipLines; i++) {
                oneLine = bufferedReader.readLine();
            }
            if (StringUtils.isNotEmpty(oneLine) && !sb.toString().contains(oneLine + "|")) {
                oneLine = oneLine.replace(",", "");
                sb.append(oneLine).append("|");
            }
        }
        while (null != oneLine);

        OutputStream outputStream = new FileOutputStream(new File(outputFileName));
        String wantedString = sb.toString().replaceAll("\\|", newLineSeperator);
        outputStream.write(wantedString.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
}
