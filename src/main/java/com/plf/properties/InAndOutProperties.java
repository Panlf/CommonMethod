package com.plf.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class InAndOutProperties {
	public static void main(String[] args) {
		try {
			readProperties("test.properties");
			
			/*writeProperties();
			readProperties();*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//读取properties
	public static void readProperties() throws IOException {
		Properties prop = new Properties(); 
		InputStream in = new BufferedInputStream (new FileInputStream("src/main/resources/test.properties"));
		prop.load(in); 
		Iterator<String> it=prop.stringPropertyNames().iterator();
		while(it.hasNext()){
		    String key=it.next();
		    System.out.println(key+":"+prop.getProperty(key));
		}
		in.close();
	}
	
  	public static void readProperties(String path) throws IOException {
  		Properties prop = new Properties();
  		//InputStream in= ClassLoader.getSystemClassLoader().getResourceAsStream(path);
  		InputStream in=InAndOutProperties.class.getClassLoader().getResourceAsStream(path);
  		BufferedInputStream localBufferedInputStream = new BufferedInputStream(in);
  	    prop.load(localBufferedInputStream);
  	    System.out.println(prop);
  	}
  
	public static void writeProperties() throws IOException{
		Properties prop = new Properties(); 
		FileOutputStream out = new FileOutputStream("src/main/resources/test.properties", true);//true表示追加打开
		prop.setProperty("key3", "value3");
		prop.store(out, "add properties");
		out.close();
	}
}

