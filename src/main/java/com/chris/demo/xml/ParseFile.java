package com.chris.demo.xml;

import com.chris.SysPathUtils;
import com.chris.demo.io.FileBean;
import com.chris.demo.io.FileWriterUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * @Auther Chris Lee
 * @Date 10/18/2018 10:54
 * @Description for more details of Dom4J you can check link
 *              "https://dom4j.github.io/javadoc/2.1.1/overview-summary.html"
 */
public class ParseFile {
	public static void main(String[] args)
		throws Exception {
		String filePath = "C:/Users/lenovo/Desktop";
		String fileInName = "word-list.xml";
		String fileOutName = "word-list-new";
		File xml = new File(filePath + File.separator + fileInName);
		ParseFile parseFile = getInstance();
		StringBuilder sb = parseFile.simplifiedContents(xml);
		
		FileBean fileBean = new FileBean(fileOutName, "txt", filePath, sb.toString().getBytes("UTF-8"));
		FileWriterUtils.writeFile(fileBean);
	}
	
	private Document parse(File file)
		throws DocumentException {
		SAXReader reader = new SAXReader();
		return reader.read(file);
	}
	
	private StringBuilder simplifiedContents(File file)
		throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		Document document = parse(file);
		Element root = document.getRootElement();
		
		/*
		 * for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) { Element element = it.next();
		 * System.out.println(element.getName()); }
		 */
		
		// iterate through child elements of root with element name "foo"
		for (Iterator<Element> it = root.elementIterator("item"); it.hasNext();) {
			Element item = it.next();
			Element wordEle = item.element("word");
			
			String word = wordEle.getTextTrim();
			sb.append(word);
			String phonetic = item.element("phonetic").getTextTrim();
			if (StringUtils.isNotEmpty(phonetic)) {
				sb.append(" ").append(phonetic);
			}
			sb.append(SysPathUtils.getNewLineCharacter());
		}
		
		// iterate through attributes of root
		/*
		 * for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext(); ) { Attribute attribute = it.next(); }
		 */
		
		return sb;
	}
	
	private static ParseFile getInstance() {
		return new ParseFile();
	}
}
