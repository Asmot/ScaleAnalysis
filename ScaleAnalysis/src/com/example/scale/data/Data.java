package com.example.scale.data;

import java.io.File;

public class Data {
	
	private static final String PATH = "data" + File.separator;
	private static final String SCALE_PATH = "data" + File.separator + "scale" + File.separator;
	
	public static String[] MENU_ITEMS = {
		"软件简介", "水结垢的原因", "水垢形成过程", "常见的水垢"
		, "影响结垢的因素", "水垢的沉积规律", "水结垢趋势预测", "水垢的危害"
		, "常用的防垢剂", "常用的阻垢剂", "水垢的选择"
	};
	
	public static String[] CONS = {
		FileUtil.readFileByLines(PATH + 0),
		FileUtil.readFileByLines(PATH + 1),
		FileUtil.readFileByLines(PATH + 2),
		FileUtil.readFileByLines(PATH + 3),
		FileUtil.readFileByLines(PATH + 4),
		FileUtil.readFileByLines(PATH + 5),
		FileUtil.readFileByLines(PATH + 6),
		FileUtil.readFileByLines(PATH + 7),
		FileUtil.readFileByLines(PATH + 8),
		FileUtil.readFileByLines(PATH + 9),
	};
	
	public static String[] SCALES_ITEMS = {
		"酸溶性无机盐垢",
		"水垢和凝固油类、砂子的混合物",
		"CaSO4,BaSO4 , SrSO4垢",
		"井眼垢物",
		"管道垢物中存在的凝固油类(约90%以上)和其他非溶性盐垢",
		"只含沥青、石蜡等凝固油类的有机物积垢",
		"CaCO3垢",
		"井筒的垢物"
	};
	
	public static String[] SCALES_CONS = {
		FileUtil.readFileByLines(SCALE_PATH + 1),
		FileUtil.readFileByLines(SCALE_PATH + 2),
		FileUtil.readFileByLines(SCALE_PATH + 3),
		FileUtil.readFileByLines(SCALE_PATH + 4),
		FileUtil.readFileByLines(SCALE_PATH + 5),
		FileUtil.readFileByLines(SCALE_PATH + 6),
		FileUtil.readFileByLines(SCALE_PATH + 7),
		FileUtil.readFileByLines(SCALE_PATH + 8),
	};
}
