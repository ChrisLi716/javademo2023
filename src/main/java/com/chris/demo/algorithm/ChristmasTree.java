package com.chris.demo.algorithm;

public class ChristmasTree {
	
	public static void main(String[] args) {
		
		int layerNumber = 10;
		int layerStarNum = 1;
		
		int rootAsSamelayer = 3;
		int rootLayer = 6;
		int rootWhiteSpaceNum = 0;
		int rootLayerStarNum = 0;
		
		for (int i = 1; i <= layerNumber; i++) {
			int whitespaceNum = layerNumber - i;
			if (i == rootAsSamelayer) {
				rootWhiteSpaceNum = whitespaceNum;
				rootLayerStarNum = layerStarNum;
			}
			printPerLayerStars(layerNumber - i, layerStarNum);
			layerStarNum += 2;
		}
		
		int i = 0;
		while (i <= rootLayer) {
			printPerLayerStars(rootWhiteSpaceNum, rootLayerStarNum);
			i++;
		}
		
	}
	
	private static void printPerLayerStars(int whitespaceNum, int starnum) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < whitespaceNum; i++) {
			sb.append(" ");
		}
		for (int i = 0; i < starnum; i++) {
			sb.append("*");
		}
		System.out.println(sb.toString());
	}
	
}
