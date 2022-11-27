package com.boots;

import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
 			Runtime.getRuntime().exec("taskkill /F /IM ngrok.exe");
 			Thread.sleep(1000);
 	    } catch (Exception e) {e.printStackTrace();}
        new Thread(()->{
        	try {
        		String command=new File(new File("").getAbsolutePath()+"\\src\\main\\resources\\ngrok\\ngrok.exe").getAbsolutePath()+" http "+8080;
            	Process proc=Runtime.getRuntime().exec(command);
            	proc.getInputStream().transferTo(System.out);
            	proc.getErrorStream().transferTo(System.out);
            	
            }catch(Exception e) {}
        }).start();
        try {
        	Thread.sleep(3000);
      	   	LineNumberReader reader = new LineNumberReader(new InputStreamReader(new URL("http://localhost:4040/api/tunnels").openStream(),"UTF-8"));
            String rez = reader.readLine();
			while (rez!=null) {
				if(rez.indexOf("\"PublicURL\":\"")!=-1)System.out.println(text(rez,"\"PublicURL\":\"",'"',' '));
				else System.out.println(rez);
				rez = reader.readLine();
			}
			reader.close();
		}catch(Exception e) {}
    }
    public static String text(String str,String type,char sim,char dopsim) {//метод для получения содержимого со строки
		String rez="";
		char[] array=str.substring(str.indexOf(type)+type.length()).toCharArray();//получает отрезок, с которого надо получить строку
		for(int i=0;i<array.length;i++) {//перебирает символы
			if(array[i]!=sim)rez+=array[i];//если текущий не является последним, записывает его в результат
			else if(dopsim!=' '&&array[i+1]!=dopsim);//если указан второй символ после последнего символа и он равняется следующим, прерывает 
			else break;
		}
		array=null;
		return rez;
  }
}
