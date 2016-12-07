package com.fx.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试File类 的使用
 * @author Administrator
 *File f=new File(path)
 *1.path可有可无
 *2.path可为相对或者绝对路径
 *
 *File的方法和静态变量
 *1.静态变量：分隔符File.separator
 *2.方法：
 *	f.mkdir（s）
 *	f.createNewFile
 */
public class FileTest {

	public static void fileTest(String path){
		
		//创建多个目录的用法
		File f=new File("E:"+File.separator+"mkdirs"+File.separator+"mkdirs");
		f.mkdirs();
		
		//创建一个目录
		File f1=new File("E:"+File.separator+"mkdir");
		f1.mkdir();
		f1=new File("E:"+File.separator+"fx");
		f1.mkdir();
		//创建一个文件（指定目录要正确）
		try {
			File f2=new File("E:"+File.separator+"fx"+File.separator+"fx.txt");
			f2.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(f1.compareTo(f));
		System.out.println(f1.exists());
		System.out.println(f1.getName());
		System.out.println(f1.getAbsolutePath());
	}
	
	public static void fileAndIOTest(){
		File inputFile=new File("E:\\fx\\a.txt");
		try {
			//1.创建或者读取一个文件
			inputFile.createNewFile();
			//2.创建字节输入流(读入)
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(inputFile));
			//3.读到buf中
			byte[] buf=new byte[1024];
			while((bis.read(buf))!=-1){
				bis.read(buf);
			}
			//4.创建输出流
			File outputFile=new File("c:\\b.txt");
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(outputFile));
			bos.write(buf);
			bos.close();
			System.out.println("成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
	/*	fileTest(null);*/
		fileAndIOTest();
	}
}
