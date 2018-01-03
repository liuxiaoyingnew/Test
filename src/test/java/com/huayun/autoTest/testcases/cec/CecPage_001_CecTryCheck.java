package com.huayun.autoTest.testcases.cec;

import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.huayun.autoTest.base.BaseParpare;
import com.huayun.autoTest.pages.CecPage;
import com.huayun.autoTest.pages.HomePage;
import com.huayun.autoTest.pages.LoginPage;
import com.huayun.autoTest.pageshelper.CecPageHelper;
import com.huayun.autoTest.pageshelper.LoginPageHelper;
import com.huayun.autoTest.testcases.login.LoginPage_001_LoginPassCheck_test;


public class CecPage_001_CecTryCheck extends LoginPage_001_LoginPassCheck_test{

	@Test(dataProvider="testData")
	public void cectrypass(Map<String , String>data){
		seleniumUtil.pause(3);
		super.LoginPageCheck(data);
		CecPageHelper.onClick(seleniumUtil, HomePage.HP_BUSINESSMANAGE_SPAN);
		CecPageHelper.onClick(seleniumUtil, CecPage.CEC_CEC_SPAN);
		CecPageHelper.onClick(seleniumUtil, CecPage.CEC_CECLIST_SPAN);
		seleniumUtil.waitForElementToLoad(timeOut, CecPage.CEC_TRY_BTN);
		CecPageHelper.onClick(seleniumUtil, CecPage.CEC_TRY_BTN);
		seleniumUtil.waitForElementToLoad(timeOut, CecPage.CEC_USERNAME_TYPE);
		CecPageHelper.onSend(seleniumUtil, CecPage.CEC_USERNAME_TYPE, data.get("UserName"));
		CecPageHelper.onClick(seleniumUtil, CecPage.CEC_SEARCH_BTN);
		CecPageHelper.waitPageLoad(seleniumUtil, timeOut);
		CecPageHelper.onClick(seleniumUtil, CecPage.CEC_CPU_BTN);
		//803011-中国联通  803007-中国电信
		CecPageHelper.onSelect(seleniumUtil, CecPage.CEC_CARRIERS_SELECT, "803011");
		CecPageHelper.onSend(seleniumUtil,CecPage.CEC_NETSPEED_SELECT , "5");
		seleniumUtil.pause(3);
		CecPageHelper.ScrollToElement(seleniumUtil, CecPage.CEC_ACCEPT_CHECKBOX);
		CecPageHelper.onClick(seleniumUtil, CecPage.CEC_ACCEPT_CHECKBOX);
		seleniumUtil.pause(10);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
