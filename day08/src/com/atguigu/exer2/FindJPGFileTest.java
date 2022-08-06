package com.atguigu.exer2;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
/**
 * 练习2：判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
 */
public class FindJPGFileTest {

	@Test
	public void test1(){
		File srcFile = new File("d:\\code");
		//获取指定目录下的所有文件或者文件目录的名称数组
		String[] fileNames = srcFile.list();
		for(String fileName : fileNames){
			//是否有后缀名为.jpg的文件
			if(fileName.endsWith(".jpg")){
				System.out.println(fileName);
			}
		}
	}
	@Test
	public void test2(){
		File srcFile = new File("d:\\code");
		//获取指定目录下的所有文件或者文件目录的File数组
		File[] listFiles = srcFile.listFiles();
		for(File file : listFiles){
			//是否有后缀名为.jpg的文件
			if(file.getName().endsWith(".jpg")){
				System.out.println(file.getAbsolutePath());
			}
		}
	}

	/**
	 * File类提供了两个文件过滤器方法
	 * public String[] list(FilenameFilter filter)
	 * public File[] listFiles(FileFilter filter)
	 */

	@Test
	public void test3(){
		File srcFile = new File("d:\\code");
		//使用过滤器FilenameFilter筛选出后缀名为.jpg的文件
		File[] subFiles = srcFile.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jpg");
			}
		});
		//遍历文件输出
		for(File file : subFiles){
			System.out.println(file.getAbsolutePath());
		}
	}
	
}
