import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 *输入输出流：字节流（非纯文本建议使用）：inputStream-FileInputStream
 *									   	-FileInputStream-bufferInputStream
 *							 outputStream-FileoutputStream
 *									   	 -FileoputStream-bufferInputStream
 *		       字符流（纯文本时建议使用）	：Reader-InputStreamReader-FileReader
 *									-BufferReader 
 *							 Writer-OutoutStreamWriter-FileWriter
 *								   -BufferWriter
 *当指定绝对路径时：两种表示是方式
 *		1.反斜线：两个
 *		new FileReader("E:\\Program Files\\IoTest.txt");
 *		2.斜线
 *		new FileReader("E:/Program Files/IoTest.txt")
 *
 */

public class IoTest {
	
	//字符流——FileReader
	/*
	 * 1.处理文本文件
	 * 2.有默认编码
	 * 3.该类中有临时缓冲区
	 * */
	public static void FileReaderTest(String inputPath,String outputPath){
		try {
			//1.获得文件输入流
			FileReader fr=new FileReader(inputPath);
			//2.缓冲区(将文本按照字符流读入字符缓冲区（字符数组）)
			char[] buf=new char[1024];
			//3.读入文件（当读到文件末尾返回-1）
			int len=0;
			while( (len=fr.read(buf))!=-1){
				//将buf转化为字符串
				System.out.println(new String(buf,0,len));
			}
			
			//====================================
			//写出(从缓存区中获取)
			FileWriter fw=new FileWriter(outputPath);
			fw.write("hello FileWriter");
			System.out.println(buf.length);
			fw.write(buf);
			fw.flush();
			fw.close();
			System.out.println("写出成功");
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	//字符流————BufferReader
	/*
	 * 1.有特有的方法。readLine(),读一行，读到末尾返回null
	 * 2.BufferWriter:特定方法：newLine（）
	 * */
	public static void BufferReaderTest(String inputPath,String outputPath){
		try {
			//1.获得BufferReader输入流
			BufferedReader br=new BufferedReader(new FileReader(inputPath));
			//2.line（读到的一行保存到line中）
			//readLine(读到末尾返回null)
			String line=null;
			//3.按照行的形式取出数据。取出的每一个行数据不包含回车符
			while( (line=br.readLine())!=null){
				System.out.println(line);
			}
			
			//=======================
			//写出(从输入流中获取)
			br=new BufferedReader(new FileReader(inputPath));
			BufferedWriter bw=new BufferedWriter(new FileWriter(outputPath));
			String hw="Hello BufferWriter";
			bw.write(hw,0,hw.length());
			line=null;
			while( (line=br.readLine())!=null){
				System.out.println(line.length());
				bw.write(line,0,line.length());
			}
			bw.flush();
			bw.close();
			System.out.println("写出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 字节流
	 * 可读取任意类型的文件
	 * 
	 * */
	public static void FileInputStreamTest(String inputPath,String outputPath){
		try {
			FileInputStream fis=new FileInputStream(inputPath);
			byte[] bytes=new byte[1024];
			int len=0;
			//将读入的文件保存到字节数组（字节缓冲区中）
			while( (len=fis.read(bytes))!=-1){
				System.out.println(new String(bytes,0,bytes.length));
			}
			
			
			//写入
			FileOutputStream fos=new FileOutputStream(outputPath);
			fos.write("hello".getBytes());
			//write(int )写入单个字符
			fos.write(65);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void BufferInputStreamTest(String inputPath,String outputPath){
		try {
			BufferedInputStream fis=new BufferedInputStream(new FileInputStream(inputPath));
			byte[] bytes=new byte[1024];
			int len=0;
			//将读入的文件保存到字节数组（字节缓冲区中）
			while( (len=fis.read(bytes))!=-1){
				System.out.println(new String(bytes,0,bytes.length));
			}
			
			//写入
			BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream(outputPath));
			fos.write("hello".getBytes());
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		//字符测试
		//BufferReaderTest("E:\\Program Files\\apache-maven-3.3.9\\README.txt","E:\\Program Files\\apache-maven-3.3.9\\BufferWriter.txt");
		//FileReaderTest("E:\\Program Files\\apache-maven-3.3.9\\README.txt","E:\\Program Files\\apache-maven-3.3.9\\FileWriter.txt");
		
		
		//字节测试
		//FileInputStreamTest("E:\\Program Files\\apache-maven-3.3.9\\README.txt","E:\\Program Files\\apache-maven-3.3.9\\FileInputStream.txt");
		BufferInputStreamTest("E:\\Program Files\\apache-maven-3.3.9\\README.txt","E:\\Program Files\\apache-maven-3.3.9\\bufferInputStream.txt");
	}

}

