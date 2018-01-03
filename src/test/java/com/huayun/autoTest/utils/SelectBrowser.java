package com.huayun.autoTest.utils;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;

/**
 * @author  
 * @decription 在不同的平台上选择对应的浏览器,系统平台程序自动判断是什么平台
 * */
public class SelectBrowser {
	static Logger logger = Logger.getLogger(SelectBrowser.class.getName());

	public WebDriver selectExplorerByName(String browser, ITestContext context) {
		Properties props = System.getProperties(); // 获得系统属性集
		String currentPlatform = props.getProperty("os.name"); // 操作系统名称
		logger.info("当前操作系统是:[" + currentPlatform + "]");
		logger.info("启动测试浏览器：[" + browser + "]");
		//从testNG的配置文件读取参数driverConfgFilePath的值
		String driverConfgFilePath = context.getCurrentXmlTest().getParameter("driverConfgFilePath");
		/** 声明好驱动的路径 */
		//String firefoxdriver = PropertiesDataProvider.getTestData(driverConfgFilePath, "firefoxdriver");
		String chromedriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_win");
		String chromedriver_linux = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver2.27");
		String chromedriver_mac = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver2.25");
		String ghostdriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "ghostdriver_win");
		String iedriver = PropertiesDataProvider.getTestData(driverConfgFilePath, "iedriver_64");
	
		String phantomjsDriver_windows = PropertiesDataProvider.getTestData(driverConfgFilePath, "phantomjsDriver_windows");
		String phantomjsDriver_linux = PropertiesDataProvider.getTestData(driverConfgFilePath, "phantomjsDriver_linux");

		if (currentPlatform.toLowerCase().contains("win")) { //如果是windows平台

			if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", iedriver);
				//IE的常规设置，便于执行自动化测试
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				//返回ie浏览器对象
				return new InternetExplorerDriver(ieCapabilities);
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromedriver_win);
				//返回谷歌浏览器对象
				 return new ChromeDriver();
			}else if (browser.equalsIgnoreCase("phantomjs")) {
				System.setProperty("phantomjs.binary.path", phantomjsDriver_windows);
				DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs(); 
				desiredCapabilities.setCapability("loadImages",false); 
				//返回伪浏览器对象
				 return new PhantomJSDriver(desiredCapabilities);
			}else if (browser.equalsIgnoreCase("firefox")) {
				//返回火狐浏览器对象webdriver.gecko.driver  webdriver.firefox.bin C:/Program Files (x86)/Mozilla Firefox/firefox.exe
				//System.setProperty("webdriver.gecko.driver",firefoxdriver);

				return new FirefoxDriver();

			}else if (browser.equalsIgnoreCase("htmlunit")) {
				DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnit();  
				//DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnitWithJs();  
				desiredCapabilities.setCapability("loadImages",false);

				return new HtmlUnitDriver(desiredCapabilities);

			} else if(browser.equalsIgnoreCase("ghost")){
				DesiredCapabilities ghostCapabilities = new DesiredCapabilities();
				ghostCapabilities.setJavascriptEnabled(true);                       
				ghostCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, ghostdriver_win);
				//返回ghost对象
			    return new PhantomJSDriver(ghostCapabilities);
				
			}else {

				logger.error("The [" + browser + "]" + " explorer is not applicable  for  [" + currentPlatform + "] OS");
				Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");

			}

		} else if (currentPlatform.toLowerCase().contains("linux")) { //如果是linux平台

			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromedriver_linux);
				return new ChromeDriver();

			} else if (browser.equalsIgnoreCase("firefox")) {
				return new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("phantomjs")) {
				System.setProperty("phantomjs.binary.path", phantomjsDriver_linux);
				DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs(); 
				desiredCapabilities.setCapability("loadImages",false); 
				//返回伪浏览器对象
				 return new PhantomJSDriver(desiredCapabilities);
			}else if (browser.equalsIgnoreCase("htmlunit")) {
				DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnit();  
				//DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnitWithJs();  
				desiredCapabilities.setCapability("loadImages",false);

				return new HtmlUnitDriver(desiredCapabilities);

			} else {
				logger.error("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
				Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
			}

		} else if (currentPlatform.toLowerCase().contains("mac")) { //如果是mac平台
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromedriver_mac);
				return new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				return new FirefoxDriver();
			} else {
				logger.error("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
				Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
			}

		} else
			logger.error("The [" + currentPlatform + "] is not supported for this automation frame,please change the OS(Windows,MAC or LINUX)");
		
		Assert.fail("The [" + currentPlatform + "] is not supported for this automation frame,please change the OS(Windows,MAC or LINUX)");
		return null;
	}
	

}
