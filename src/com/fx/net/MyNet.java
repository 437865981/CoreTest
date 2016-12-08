package com.fx.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MyNet {

	public static void main(String[] args) {
		
		
		try {
			//Url
			URL url=new URL("http://www.baidu.com");
			//读取数据
			BufferedReader br=new BufferedReader(
					new InputStreamReader(url.openStream()));
			String line=null;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
			System.out.println(url.getProtocol()+","+url.getPort()+","+url.getHost());
			System.out.println(url.getRef());
			System.out.println(url.getUserInfo());
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
