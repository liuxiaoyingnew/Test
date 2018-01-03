package com.huayun.autoTest.testcases.login;



import java.util.Map;
import org.testng.annotations.Test;
import com.huayun.autoTest.base.BaseParpare;
import com.huayun.autoTest.pages.LoginPage;
import com.huayun.autoTest.pageshelper.LoginPageHelper;

public class LoginPage_001_LoginPassCheck_test extends BaseParpare {
 // @Test(dataProvider="testData")
  public void LoginPageCheck(Map<String, String> data) {
	  LoginPageHelper.onClear(seleniumUtil, LoginPage.HP_TYPE_NAME);
      LoginPageHelper.onClear(seleniumUtil, LoginPage.HP_TYPE_PWD);
      LoginPageHelper.onSend(seleniumUtil, LoginPage.HP_TYPE_NAME, data.get("LoginName"));
      LoginPageHelper.onSend(seleniumUtil, LoginPage.HP_TYPE_PWD, data.get("Password"));
      LoginPageHelper.onClick(seleniumUtil, LoginPage.HP_BTN_LOGIN);
      seleniumUtil.pause(5);
      LoginPageHelper.checkUserName(seleniumUtil, data.get("ACTUAL") , data.get("EXCEPT"));
      seleniumUtil.pause(1);
	  
  }
 
  }
  
