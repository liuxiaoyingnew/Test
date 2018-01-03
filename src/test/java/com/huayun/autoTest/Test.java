package com.huayun.autoTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.huayun.autoTest.utils.JdbcUtil;

public class Test {

	public static void main(String[] args) throws Exception {
/**

		        // TODO Auto-generated method stub  
		    //设置访问ChromeDriver的路径  
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\liuxiaoying\\git\\chromedriver.exe");   
		       
		        ChromeOptions options = new ChromeOptions(); 
//		        options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); 
		        WebDriver driver = new ChromeDriver();  
		        driver.get("http://www.baidu.com/");  
		        Thread.sleep(1000);
 		 
 */
		        JdbcUtil.getConnection();
		        
		        
		  
		    }  
		  
		



}
