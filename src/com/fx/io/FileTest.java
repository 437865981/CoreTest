package com.fx.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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
		/*System.out.println(f1.compareTo(f));
		System.out.println(f1.exists());
		System.out.println(f1.getName());
		System.out.println(f1.getAbsolutePath());*/
		File f3=new File("c:");
		String[] fileaname=f3.list();
		for(int i=0;i<fileaname.length;i++){
			System.out.println(fileaname[i]);
		}
		Arrays.sort(fileaname);
	}
	
	public static void fileAndIOTest() throws Exception{
		
		//处理图片的例子（bufferedinputStream）
	/*	try {
			//1.创建或者读取一个文件
			File inputFile=new File("E:\\fx\\one.jpeg");
			//2.创建字节输入流(读入)
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(inputFile));
			
			File outputFile=new File("c:\\one.jpeg");
			
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(outputFile));
			//3.读到buf中
			byte[] buf=new byte[1024];
			int len;
			while( (len=bis.read(buf))!=-1){
			//4.写入
				bos.write(buf,0,len);
			}
			
			int len=bis.read(buf);
			while(len!=-1){ 
				bos.write(buf, 0, len); 
				len=bis.read(buf); 
				} 
			//关闭流（很重要）
			bos.close();
			bis.close();
			System.out.println("成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		*/
		/*String path1="E:\\fx\\one.jpeg";
		String path2="c:\\one.jpeg";*/
		
		//处理文本（字符流，BufferReader 传入 转换流 InputStreamReader）（编码问题）
		String path1="E:\\fx\\b.txt";
		String path2="c:\\b.txt";
		try {
			//输入流
		    File file = new File(path1);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"gb2312");
            BufferedReader br = new BufferedReader(read);
            
            //缓冲区
            char[] buf=new char[1024];
            int len;
            //输出流
			File outputFile=new File(path2);
			OutputStreamWriter write=new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8");
			BufferedWriter bw=new BufferedWriter(write);
			
			/*String line=null;
			while((line= br.readLine() )!=null){
				bw.write(line);
			}
			*/
			while( (len=br.read(buf) ) !=-1){
				bw.write(buf);
			}
			bw.close();
			System.out.println("成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		//fileTest(null);
		try {
			fileAndIOTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
