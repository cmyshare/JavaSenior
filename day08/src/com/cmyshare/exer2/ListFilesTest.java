package com.cmyshare.exer2;

import java.io.File;
/**
 * 遍历指定目录所有文件名称，包括子文件目录中的文件。
 * 拓展1：并计算指定目录占用空间的大小
 * 拓展2：删除指定文件目录及其下的所有文件
 */
public class ListFilesTest {

	public static void main(String[] args) {
		// 递归:文件目录
		/** 打印出指定目录所有文件名称，包括子文件目录中的文件 */
		// 1.创建目录对象
		File dir = new File("G:\\IDEA2022\\workmenu\\JavaSenior\\day08\\src\\3_软件");
		// 2.打印目录的子文件
		printSubFile(dir);
	}

	/**
	 * 方式一 递归
	 * 递归打印目录的子文件绝对路径
	 * @param dir
	 */
	public static void printSubFile(File dir) {
		//获取此文件下的所有下级文件/目录
		File[] subfiles = dir.listFiles();

		for (File f : subfiles) {
			if (f.isDirectory()) {
				//是文件目录，递归获取下级
				printSubFile(f);
			} else {
				//文件绝对路径
				System.out.println(f.getAbsolutePath());
			}

		}
	}

	/**
	 * 	方式二：循环实现
	 * 	列出file目录的下级内容，仅列出一级的话
	 * 	使用File类的String[] list()比较简单
	 * @param file
	 */
	public static void listSubFiles(File file) {
		if (file.isDirectory()) {
			//获取指定目录下的所有文件或者文件目录的名称数组
			String[] all = file.list();
			for (String s : all) {
				System.out.println(s);
			}
		} else {
			System.out.println(file + "是文件！");
		}
	}

	// 列出file目录的下级，如果它的下级还是目录，接着列出下级的下级，依次类推
	// 建议使用File类的File[] listFiles()
	public void listAllSubFiles(File file) {
		if (file.isFile()) {
			System.out.println(file);
		} else {
			File[] all = file.listFiles();
			// 如果all[i]是文件，直接打印
			// 如果all[i]是目录，接着再获取它的下一级
			for (File f : all) {
				// 递归调用：自己调用自己就叫递归
				listAllSubFiles(f);
			}
		}
	}

	/**
	 * 拓展1：求指定目录所在空间的大小
	 * 求任意一个目录的总大小
	 * @param file
	 * @return
	 */
	public long getDirectorySize(File file) {
		// file是文件，那么直接返回file.length()
		// file是目录，把它的下一级的所有大小加起来就是它的总大小
		long size = 0;
		// 判断file是否为文件
		if (file.isFile()) {
			//length表示的文件的长度以字节为单位
			size += file.length();
		} else {
			// 获取file的下一级
			File[] all = file.listFiles();
			// 累加all[i]的大小
			for (File f : all) {
				// f的大小
				size += getDirectorySize(f);
			}
		}
		return size;
	}

	/**
	 * 拓展2：删除指定的目录
	 * @param file
	 */
	public void deleteDirectory(File file) {
		// 如果file是文件，直接delete
		// 如果file是目录，先把它的下一级干掉，然后删除自己
		if (file.isDirectory()) {
			File[] all = file.listFiles();
			// 循环删除的是file的下一级
			for (File f : all) {
				//递归删除，f代表file的每一个下级
				deleteDirectory(f);
			}
		}
		// 删除自己
		file.delete();
	}
}
