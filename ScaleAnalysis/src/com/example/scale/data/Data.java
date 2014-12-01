package com.example.scale.data;

import java.io.File;

public class Data {
	
	private static final String PATH = "data" + File.separator;
	private static final String SCALE_PATH = "data" + File.separator + "scale" + File.separator;
	
	public static String[] MENU_ITEMS = {
		"������", "ˮ�Ṹ��ԭ��", "ˮ���γɹ���", "������ˮ��"
		, "Ӱ��Ṹ������", "ˮ���ĳ�������", "ˮ�Ṹ����Ԥ��", "ˮ����Σ��"
		, "���õķ�����", "���õ��蹸��", "ˮ����ѡ��"
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
		"�������޻��ι�",
		"ˮ�����������ࡢɰ�ӵĻ����",
		"CaSO4,BaSO4 , SrSO4��",
		"���۹���",
		"�ܵ������д��ڵ���������(Լ90%����)�������������ι�",
		"ֻ�����ࡢʯ��������������л������",
		"CaCO3��",
		"��Ͳ�Ĺ���"
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
