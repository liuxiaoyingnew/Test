package com.huayun.autoTest.pageshelper;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.huayun.autoTest.pages.CecPage;
import com.huayun.autoTest.pages.HomePage;
import com.huayun.autoTest.pages.LoginPage;
import com.huayun.autoTest.utils.SeleniumUtil;



/**  
 * @desciption 帮助类：专门提供在这个页面进行操作的方法封装*/
public class CecPageHelper {
	//提供本类中日志输出对象
	public static Logger logger = Logger.getLogger(CecPageHelper.class);
	/** 
	 * @description 等待元素加载
	 * @param seleniumUtil selenium api封装引用对象
	 * @param timeOut 等待元素超时的时间
	 * */
	public static void waitPageLoad(SeleniumUtil seleniumUtil,int timeOut){
	    logger.info("开始等待元素加载");
	    By []by = new By[]{CecPage.CEC_SERIES_BTN,CecPage.CEC_SERIES_BTN};
		for(int i = 0;i<2;i++){
		   seleniumUtil.waitForElementToLoad(timeOut, by[i]);
		}		
		logger.info("元素加载完毕");		
	}
	
	/** 
	 * @description 点击
	 */
	public static void onClick(SeleniumUtil seleniumUtil,By by){
		seleniumUtil.click(by);
	}
	/** 
	 * @description select
	 */
	public static void onSelect(SeleniumUtil seleniumUtil,By by,String value){
		seleniumUtil.selectByValue(by, value);
	}
	
	/** 
	 * @description selectbyindex
	 */
	public static void Selectbyindex(SeleniumUtil seleniumUtil,By by,int index){
		seleniumUtil.selectByIndex(by, index);
	}
	/**
	 * @description send
	 */
	public static void onSend(SeleniumUtil seleniumUtil,By by,String value){
		seleniumUtil.clear(by);
		seleniumUtil.sendkeys(by, value);
	}
	/**
	 * @description send picture
	 * "/upload/x.jpg"
	 */
	public static void SendPic(SeleniumUtil seleniumUtil,By by,String filepath){
		String fileString = seleniumUtil.getFilePath(filepath);
		seleniumUtil.sendkeys(by, fileString);
	}
	
	
	/** 
	 * @description clear
	 */
	public static void onClear(SeleniumUtil seleniumUtil,By by){
		seleniumUtil.clear(by);
	}
	
	/**
	 * 判断元素是否选中
	 */
	public static void isSelect(SeleniumUtil seleniumUtil,WebElement by){
		seleniumUtil.isSelected(by);
	}
	/** 
	 * 获取元素文本
	 * @return 
	 */
	public static String getText(SeleniumUtil seleniumUtil,By by){
		String text = seleniumUtil.findElementBy(by).getText();
		return text;
	}
	/** 
	 * 检查元素是否存在
	 */
	public static boolean isCecPageElements(SeleniumUtil seleniumUtil,By by){
		
		return seleniumUtil.doesElementsExist(by);
	}
	/** 
	 * @description 检查是否包含结果文本
	 */
	public static void isCheckContext(SeleniumUtil seleniumUtil,String actual,String expect){
		logger.info("开始检查当前页面是否存在\n【"+expect+"】");
		seleniumUtil.isContains(actual, expect);
		logger.info("检查结束，当前显示内容为\n【"+actual+"】");
	}
	/**
	 * 获取by集合
	 * @param seleniumUtil
	 * @param by
	 * @return
	 */
	public static List<WebElement> allResult(SeleniumUtil seleniumUtil,By by){
		List<WebElement> list = seleniumUtil.findElementsBy(by);
		return list;
	}
	
	public static void ScrollToElement(SeleniumUtil seleniumUtil,By by){
		seleniumUtil.onScrollByElement(by);
		logger.info("鼠标移动到元素" + by);
	}
	
	
	/** 
	 * 检查登录用户信息
	 * @param seleniumUtil
	 * @param by
	 */
	public static void checkUserName(SeleniumUtil seleniumUtil,String actualusername,String exceptuasername){
		actualusername  = seleniumUtil.getText(HomePage.HP_USERNAME_SPAN);
		logger.info("开始检查用户名是不是："+actualusername);
		seleniumUtil.isTextCorrect(actualusername, exceptuasername);
		logger.info("用户名检查完毕,用户名是："+exceptuasername);
	}
}
