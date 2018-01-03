package com.huayun.autoTest.pages;

import org.openqa.selenium.By;
 
/**
 * 
 * @author liuxiaoying
 * 云主机页面控件
 *
 */

public class CecPage {
	    /**云主机*/
		public static final By CEC_CEC_SPAN = By.xpath("/html/body/div[1]/aside/section/ul/li[2]/ul/li[1]/a/span");
		/**云主机列表*/
		public static final By CEC_CECLIST_SPAN = By.xpath("/html/body/div[1]/aside/section/ul/li[2]/ul/li[1]/ul/li/a");
		/**试用按钮*/
		public static final By CEC_TRY_BTN = By.xpath("/html/body/div[1]/div/section[3]/div[1]/div[1]/h3/a[2]");
		/**所属用户输入框*/
		public static final By CEC_USERNAME_TYPE = By.id("username");
		/**检索用户按钮*/
		public static final By CEC_SEARCH_BTN = By.xpath("//*[@id=\"buy-form\"]/div[1]/div[2]/div/div/a");
		/**主机系列*/
		public static final By CEC_SERIES_BTN = By.xpath("//*[@id=\"buy-form\"]/div[2]/div[2]/div[1]/ul/li");
		/**CPU 2核 第二个选项*/
		public static final By CEC_CPU_BTN = By.xpath("//*[@id=\"buy-form\"]/div[2]/div[2]/div[3]/ul/li[2]");
		/**内存 第一个选项*/
		public static final By CEC_MEMORY_BTN = By.xpath("//*[@id=\"buy-form\"]/div[2]/div[2]/div[4]/ul/li");
		
		/**需要公网 */
		public static final By CEC_NEED_RADIO = By.xpath("//*[@id=\"need\"]");
		/**普通带宽*/
		public static final By CEC_NORMALNET_BTN = By.xpath("//*[@id=\"buy-form\"]/div[3]/div[2]/div[2]/div/div[1]/div/ul/li[1]");
		/**运营商*/
		public static final By CEC_CARRIERS_SELECT = By.xpath("//*[@id=\"buy-form\"]/div[3]/div[2]/div[2]/div/div[2]/ul[1]/li/div/select");
		/**带宽速度*/
		public static final By CEC_NETSPEED_SELECT = By.id("kbpsValue_");
		
		/**更大带宽*/
		public static final By CEC_BIGNET_BTN = By.xpath("//*[@id=\"buy-form\"]/div[3]/div[2]/div[2]/div/div[1]/div/ul/li[2]");
		/**更大带宽-运营商*/
		public static final By CEC_BIGCARRIERS_BTN = By.className("fleft");
		/**更大带宽-带宽速度*/
		public static final By CEC_BIGNETSPEED_BTN = By.id("kbpsValueM_");
		
		
		/**不需要公网*/
		public static final By CEC_NONEED_RADIO = By.xpath("//*[@id=\"buy-form\"]/div[3]/div[2]/div[1]/div/label[2]");
		/**默认网络*/
		public static final By CEC_BASICNET_RADIO = By.id("basic");
		/**私有网络*/
		public static final By CEC_FOROWNNWT_RADIO = By.id("forown");
		/**防火墙*/
		public static final By CEC_FIREWALL_SELECT = By.id("s-fire");
		
		/**系统镜像*/
		public static final By CEC_OS_BTN = By.xpath("//*[@id=\"select-os\"]/li[1]");
		public static final By CEC_OS_SELECT = By.xpath("//*[@id=\"mrrior\"]/div[2]/ul[1]/li/select");
		/**自定义镜像*/
		public static final By CEC_USEROS_BTN = By.xpath("//*[@id=\"select-os\"]/li[2]");
		public static final By CEC_USEROS_SELECT = By.xpath("//*[@id=\"mrrior\"]/div[2]/ul[2]/li/select");
		
		/**系统盘*/
		/**数据盘*/
		public static final By CEC_DATADISK_SELECT = By.xpath("//*[@id=\"buy-form\"]/div[5]/div[2]/div[2]/select");
		/**容量*/
		public static final By CEC_DISKVALUE_TYPE = By.xpath("//*[@id=\"disk-value-687\"]");
		/**增加一块按钮*/
		public static final By CEC_ADDDISK_RADIO = By.id("add-disk");
	    /**接受*/
		public static final By CEC_ACCEPT_CHECKBOX = By.xpath("//*[@id=\"price_agree\"]");
		/**重置*/
		public static final By CEC_RESET_BTN = By.xpath("//*[@id=\"buy-form\"]/div[8]/div[2]/a[1]");
		/**立即试用*/
		public static final By CEC_TRYNOW_CHECKBOX = By.id("//*[@id=\"buy-form\"]/div[8]/div[2]/a[2]");
		
		
		
		
		
		
		

}
