package com.ahut.qian.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @brife 读取配置文件
 * @date 2019-7-17
 */
public class Config {
	private Properties table = new Properties();	// 配置文件表

	/**
	 * @param file, 配置文件所在的路径
	 */
	public Config(String file) {
		try {
			/*
			 * 根据file 路径指定的位置将文件读入，并且存储在table 中
			 */
			table.load(new FileInputStream(file));
		}
		catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		catch (IOException b) {
			b.printStackTrace();
		}
	}

	/**
	 * @brife 根据键来取值
	 * @param key, 键
	 * @return int 类型的值数据
	 */
	public int getInt(String key) {
		return Integer.parseInt(table.getProperty(key));
	}

	public String getString(String key) {
		return table.getProperty(key);
	}

	public double getDouble(String key) {
		return Double.parseDouble(table.getProperty(key));
	}


}