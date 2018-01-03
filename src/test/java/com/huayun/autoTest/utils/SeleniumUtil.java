package com.huayun.autoTest.utils;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

import bsh.This;

import com.mysql.jdbc.Driver;
import com.thoughtworks.selenium.webdriven.commands.IsVisible;

public class SeleniumUtil {
	/** 使用Log4j，第一步就是获取日志记录器，这个记录器将负责控制日志信息 */
	public static Logger logger = Logger
			.getLogger(SeleniumUtil.class.getName());
	public static WebDriver driver = null;
	public WebDriver window = null;

	/**
	 * 启动浏览器并打开测试页面
	 */
	public void launchBrowser(String browserName, ITestContext context,
			String webUrl, int timeOut) {
		SelectBrowser select = new SelectBrowser();
		driver = select.selectExplorerByName(browserName, context);
		try {
			maxWindow(browserName);
			waitForPageLoading(timeOut);
			get(webUrl);
		} catch (TimeoutException e) {
			logger.warn("注意：页面没有完全加载出来，刷新重试！！");
			refresh();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String status = (String) js
					.executeScript("return document.readyState");
			logger.info("打印状态：" + status);
		}

	}

	/**
	 * 打开被测试页面
	 */
	public void get(String webUrl) {
		driver.get(webUrl);
		logger.info("打开测试页面:[" + webUrl + "]");

	}

	/**
	 * 刷新方法包装
	 */
	public void refresh() {
		driver.navigate().refresh();
		logger.info("页面刷新成功！");
	}

	/**
	 * 后退方法包装
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 前进方法包装
	 */
	public void forward() {
		driver.navigate().forward();
	}

	/**
	 * 等待页面加载
	 * pageLoadTimeout。页面加载时的超时时间。因为webdriver会等页面加载完毕在进行后面的操作，
	 * 所以如果页面在这个超时时间内没有加载完成，那么webdriver就会抛出异常
	 */

	public void waitForPageLoading(int pageLoadTime) {
		driver.manage().timeouts()
				.pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
	}

	/** 
	 * 最大化浏览器
	 */
	public void maxWindow(String browserName) {
		logger.info("最大化浏览器:" + browserName);
		driver.manage().window().maximize();

	}

	/**
	 * 获得页面的标题
	 * */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 退出
	 */
	public void quit() {
		driver.quit();
	}

	/**
	 * 等待
	 */
	public void pause(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查找元素是否存在
	 */
	public boolean doesElementsExist(By by) {
		logger.info("开始检查元素[" + by + "]是否存在");
		try {
			Boolean boo = driver.findElement(by).isDisplayed();
			logger.info("[" + by + "]" + "存在");
			return boo;
		} catch (Exception e) {
			logger.error("[" + by + "]" + "不存在");
			return false;
		}

	}

	/**
	 * 在给定的时间内去查找元素，如果没找到则超时，抛出异常
	 * */
	public void waitForElementToLoad(int timeOut, final By By) {
		logger.info("开始查找元素[" + By + "]");
		try {

			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					WebElement element = driver.findElement(By);
					return element.isDisplayed();
				}

			});
		} catch (TimeoutException e) {
			logger.error("超时!! " + timeOut + " 秒之后还没找到元素 [" + By + "]");
			Assert.fail("超时!! " + timeOut + " 秒之后还没找到元素 [" + By + "]");

		}
		logger.info("找到了元素 [" + By + "]");
	}

	/**
	 * 查找元素 
	 */
	public WebElement findElementBy(By by) {
		return driver.findElement(by);
	}

	/**
	 * 包装查找元素的方法 elements
	 * */
	public List<WebElement> findElementsBy(By by) {
		return driver.findElements(by);
	}

	/**
	 * 这是一堆相同的elements中 选择 其中方的 一个 然后在这个选定的中 继续定位
	 * */
	public WebElement getOneElement(By bys, By by, int index) {
		return findElementsBy(bys).get(index).findElement(by);
	}

	/** 获得CSS value */
	public String getCSSValue(WebElement webElement, String key) {

		return webElement.getCssValue(key);
	}

	/**
	 * 获得元素属性的文本
	 * */
	public String getAttributeText(By elementLocator, String attribute) {
		return driver.findElement(elementLocator).getAttribute(attribute)
				.trim();
	}

	/**
	 * 清除操作
	 */
	public void clear(By by) {
		try {
			driver.findElement(by).clear();
		} catch (Exception e) {
			logger.error("清除[" + by + "]上的内容失败");
		}

		logger.info("清除[" + by + "]上的内容成功");
	}

	/**
	 * 输入
	 */
	public void sendkeys(By by, String key) {
		try {
			findElementBy(by).sendKeys(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("输入 [" + key + "] 到 元素[" + by + "]失败");
			Assert.fail("输入 [" + key + "] 到 元素[" + by + "]失败");
		}
		logger.info("输入：[" + key + "] 到 [" + by + "]");
	}

	/**
	 * 包装点击操作
	 * */
	public void click(By byElement) {

		try {
			clickTheClickable(byElement, System.currentTimeMillis(), 2500);
		} catch (StaleElementReferenceException e) {
			logger.error("The element you clicked:[" + byElement + "] is no longer exist!");
			Assert.fail("The element you clicked:[" + byElement + "] is no longer exist!");
		} catch (Exception e) {
			logger.error("Failed to click element [" + byElement + "]");
			Assert.fail("Failed to click element [" + byElement + "]",e);
		}
		logger.info("点击元素 [" + byElement + "]");
	}
	
	/** 不能点击时候重试点击操作 */
	public void clickTheClickable(By byElement, long startTime, int timeOut)
			throws Exception {
		try {
			findElementBy(byElement).click();
		} catch (Exception e) {
			if (System.currentTimeMillis() - startTime > timeOut) {
				logger.warn(byElement + " is unclickable");
				throw new Exception(e);
			} else {
				Thread.sleep(500);
				logger.warn(byElement + " is unclickable, try again");
				clickTheClickable(byElement, startTime, timeOut);
			}
		}
	}

	/**
	 * 按钮可点击后开始点击
	 */
	public void isEnableClick(By by) {
		if (findElementBy(by).isEnabled()) {
			logger.info("[" + by + "]可点击了");
			findElementBy(by).click();
		}
	}

	/**
	 * 模拟键盘操作的,比如Ctrl+A,Ctrl+C等 参数详解：<br>
	 * 1、WebElement element - 要被操作的元素 <br>
	 * 2、Keys key- 键盘上的功能键 比如ctrl ,alt等 <br>
	 * 3、String keyword - 键盘上的字母
	 * */
	public void pressKeysOnKeyboard(WebElement element, Keys key, String keyword) {
		element.sendKeys(Keys.chord(key, keyword));
	}

	public void pressKeysOnKeyboard(WebElement element, Keys key) {

		element.sendKeys(Keys.chord(key));
	}

	/**
	 * 鼠标移动到指定元素
	 * */
	public void mouseMoveToElement(By by) {
		Actions builder = new Actions(driver);
		Actions mouse = builder.moveToElement(findElementBy(by));
		mouse.perform();
	}

	/**
	 * 鼠标移动到指定元素
	 * */
	public void mouseMoveToElement(WebElement element) {
		Actions builder = new Actions(driver);
		Actions mouse = builder.moveToElement(element);
		mouse.perform();
	}

	/**
	 * 鼠标右击
	 * */
	public void mouseRightClick(By by) {
		Actions builder = new Actions(driver);
		Actions mouse = builder.contextClick(findElementBy(by));
		mouse.perform();
	}

	/**
	 * 检查页面是否包含文本
	 */
	public void isContains(String expect, String actual) {
		logger.info("开始检查当前页面是否存在【" + expect + "】");
		try {
			Assert.assertTrue(actual.contains(expect));
		} catch (AssertionError e) {
			logger.error("检查结果\n【" + actual + "】 \n不包含\n 【" + expect + "】");
			Assert.fail("检查结果 \n【" + actual + "】 \n不包含 \n【" + expect + "】");
		}
		logger.info("检查结果\n 【" + actual + "】\n 包含\n 【" + expect + "】");

	}
	
	/**
	 * 判断文本是不是和需求要求的文本一致
	 * **/
	public void isTextCorrect(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (AssertionError e) {
			logger.error("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");
			Assert.fail("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");

		}
		logger.info("找到了期望的文字: [" + expected + "]");

	}

	/**
	 * 获取元素文本
	 */
	public String getText(By by) {
		String text = driver.findElement(by).getText().trim();
		return text;
	}

	/**
	 * 多选框是否选中
	 */
	public void isselected(By by) {
		boolean boo = driver.findElement(by).isSelected();
		if (boo == true) {
			logger.info("元素" + "[" + by + "]" + "已被选中");
		} else {
			logger.info("元素" + "[" + by + "]" + "没有被选中");
		}
	}

	/**
	 * 等待alert出现
	 * */
	public Alert switchToPromptedAlertAfterWait(long waitMillisecondsForAlert)
			throws NoAlertPresentException {
		final int ONE_ROUND_WAIT = 200;
		NoAlertPresentException lastException = null;

		long endTime = System.currentTimeMillis() + waitMillisecondsForAlert;

		for (long i = 0; i < waitMillisecondsForAlert; i += ONE_ROUND_WAIT) {

			try {
				Alert alert = driver.switchTo().alert();
				return alert;
			} catch (NoAlertPresentException e) {
				lastException = e;
			}
			pause(ONE_ROUND_WAIT);

			if (System.currentTimeMillis() > endTime) {
				break;
			}
		}
		throw lastException;
	}

	/**
	 * 切换frame - 根据String类型（frame名字）
	 * */
	public void inFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}

	/**
	 * 切换frame - 根据frame在当前页面中的顺序来定位
	 * */
	public void inFrame(int frameNum) {
		driver.switchTo().frame(frameNum);
	}

	/** 跳出frame */
	public void outFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * 切换frame - 根据页面元素定位
	 * */
	public void switchFrame(WebElement element) {
		try {
			logger.info("正在跳进frame:[" + getLocatorByElement(element, ">") + "]");
			driver.switchTo().frame(element);
		} catch (Exception e) {
			logger.info("跳进frame: [" + getLocatorByElement(element, ">")
					+ "] 失败");
			Assert.fail("跳进frame: [" + getLocatorByElement(element, ">")
					+ "] 失败");
		}
		logger.info("进入frame: [" + getLocatorByElement(element, ">") + "]成功 ");
	}

	/**获取当前页面句柄*/
	public String current_handles() {
		return driver.getWindowHandle();
	}
	
	/**
	 * 跳转到新窗口
	 */
	public void switchToWindow(By by){
		//获取当前窗口句柄
		String source = driver.getWindowHandle();
		//点击元素，打开新窗口
		click(by);
		//获取窗口句柄
	    Set<String> sourceSet =	driver.getWindowHandles();
	    for (String string : sourceSet) {
	    	if (!string.equals(source)) {
	    		logger.info("跳转到新窗口");
	    		driver.switchTo().window(string);
			}
	    }	
	}

	/**获取新窗口Driver*/
	public WebDriver newWindowDriver(String current_handles) {
		//新的窗口打开，获取所有窗口句柄
		Set<String> all_handles = driver.getWindowHandles();
		//循环判断，把当前句柄从所有句柄中移除，剩下的就是你想要的新窗口
		Iterator<String> it = all_handles.iterator();
		while (it.hasNext()) {
			if (current_handles == it.next())
				continue;
			//跳入新窗口,并获得新窗口的driver - Window
			window = driver.switchTo().window(it.next());
		}
		return window;
	}

	/**跳转至当前窗口*/
	public void switchWindow(String current_handles) {
		driver.switchTo().window(current_handles);
	}

	/** 根据元素来获取此元素的定位值 */
	public String getLocatorByElement(WebElement element, String expectText) {
		String text = element.toString();
		String expect = null;
		try {
			expect = text.substring(text.indexOf(expectText) + 1,
					text.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("failed to find the string [" + expectText + "]");

		}

		return expect;

	}

	/** 检查元素是否被勾选 */
	public boolean isSelected(WebElement element) {
		boolean flag = false;
		if (element.isSelected() == true) {
			logger.info("元素: [" + getLocatorByElement(element, ">")
					+ "] 被勾选");
			flag = true;
		} else if (element.isSelected() == false) {
			logger.info("元素: [" + getLocatorByElement(element, ">")
					+ "] 没被勾选");
			flag = false;
		}
		return flag;
	}

	/**
	 * 选择下拉选项 -根据value
	 * */
	public void selectByValue(By by, String value) {
		Select s = new Select(driver.findElement(by));
		s.selectByValue(value);
		logger.info("选择value为" + value + "的选项");
	}

	/**
	 * 选择下拉选项 -根据index角标
	 * */
	public void selectByIndex(By by, int index) {
		Select s = new Select(driver.findElement(by));
		s.selectByIndex(index);
		logger.info("选择索引为" + index + "的选项");
	}

	/**
	 * 选择下拉选项 -根据文本内容
	 * */
	public void selectByText(By by, String text) {
		Select s = new Select(driver.findElement(by));
		s.selectByVisibleText(text);
		logger.info("选择text值为" + text + "的选项");
	}

	/**
	 * 选择下拉选项最后一个值
	 * getoptions获取的是所有带选项的集合
	 */
	public void selectLastValue(By by) {
		Select select = new Select(findElementBy(by));
		List<WebElement> options = select.getOptions();
		select.selectByIndex(options.size() - 1);
	}

	/**
	 * 循环选择select下拉框的值
	 */
	public void selectOneByOne(By by) {
		Select select = new Select(findElementBy(by));
		int num = select.getOptions().size();
		for (int i = 0; i < num; i++) {
			logger.info("开始选择下拉框第" + i + 1 + "个值");
			select.selectByIndex(i);
			pause(2);
		}
	}

	/**
	 * 选择随机值
	 */
	public void selectRandom(By by) {
		Select select = new Select(findElementBy(by));
		int num = select.getOptions().size();
		int random = new Random().nextInt(num);
		logger.info("选择第" + random + "个值");
		select.selectByIndex(random);

	}

	/**
	 * 获得当前select选择的值
	 * */
	public List<WebElement> getCurrentSelectValue(By by) {
		List<WebElement> options = null;
		Select s = new Select(driver.findElement(by));
		options = s.getAllSelectedOptions();
		return options;
	}

	/** 检查checkbox是不是勾选 */
	public boolean doesCheckboxSelected(By by) {
		if (findElementBy(by).isSelected() == true) {
			logger.info("CheckBox: "
					+ getLocatorByElement(findElementBy(by), ">") + " 被勾选");
			return true;
		} else
			logger.info("CheckBox: "
					+ getLocatorByElement(findElementBy(by), ">") + " 没有被勾选");
		return false;

	}

	/**
	 * 先判断多选框是否选中，没选中就点击选中
	 */
	public void checkbox(By by) {
		if (!findElementBy(by).isSelected()) {
			logger.info("选中:[" + by + "]");
			findElementBy(by).click();
		}
	}

	/**
	 * 多选框全部勾选
	 */
	public void checkAllClick(By by) {
		List<WebElement> checkbox = findElementsBy(by);
		for (WebElement check : checkbox) {
			pause(1);
			check.click();
		}
	}

	/**
	 * 下拉菜单选中
	 */
	public void selectbyvalue(By by, String value) {
		Select s = (Select) driver.findElement(by);
		logger.info("选择：" + value);
		s.selectByVisibleText(value);
	}

	/**
	 * li标签下拉菜单选中
	 */
	public void selectbyvaluenew(By by, String tagName, String value) {
		List<WebElement> li = driver.findElements(by);
		for (int i = 0; i < li.size(); i++) {
			WebElement element = li.get(i);
			if (element.findElement(by.tagName(tagName)).getText()
					.equals(value)) {
				logger.info("选中：" + value);
				element.click();
			}

		}

	}

	/**
	* 获得输入框的值 这个方法 是针对某些input输入框 没有value属性，但是又想取得input的 值得方法
	* */
	public String getInputValue(String chose, String choseValue) {
		String value = null;
		switch (chose.toLowerCase()) {
		case "name":
			String jsName = "return document.getElementsByName('" + choseValue
					+ "')[0].value;"; //把JS执行的值 返回出去
			value = (String) ((JavascriptExecutor) driver)
					.executeScript(jsName);
			break;

		case "id":
			String jsId = "return document.getElementById('" + choseValue
					+ "').value;"; //把JS执行的值 返回出去
			value = (String) ((JavascriptExecutor) driver).executeScript(jsId);
			break;

		default:
			logger.error("未定义的chose:" + chose);
			Assert.fail("未定义的chose:" + chose);

		}
		return value;

	}

	/**
	 * 执行JavaScript 方法
	 * */
	public void executeJS(String js) {
		((JavascriptExecutor) driver).executeScript(js);
		logger.info("执行JavaScript语句：[" + js + "]");
	}

	/**
	 * 执行JavaScript 方法和对象
	 * 用法：seleniumUtil.executeJS("arguments[0].click();", seleniumUtil.findElementBy(MyOrdersPage.MOP_TAB_ORDERCLOSE));
	 * */
	public void executeJS(String js, Object... args) {
		((JavascriptExecutor) driver).executeScript(js, args);
		logger.info("执行JavaScript语句：[" + js + "]");
	}

	/**
	 * 添加cookies,做自动登陆的必要方法
	 * */
	public void addCookies(int sleepTime) {
		pause(sleepTime);
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie c : cookies) {
			System.out.println(c.getName() + "->" + c.getValue());
			if (c.getName().equals("logisticSessionid")) {
				Cookie cook = new Cookie(c.getName(), c.getValue());
				driver.manage().addCookie(cook);
				System.out.println(c.getName() + "->" + c.getValue());
				System.out.println("添加成功");
			} else {
				System.out.println("没有找到logisticSessionid");
			}

		}

	}
	
	public void addCookies(String name , String value) {
		Cookie cookie = new Cookie(name, value);
		driver.manage().addCookie(cookie);
		
	}
	

	

	/** 使用testng的assetTrue方法 */
	public void assertTrue(WebElement weElement, String content) {
		String str = weElement.getText();
		Assert.assertTrue(str.contains(content), "字符串数组中不含有：" + content);

	}

	// webdriver中可以设置很多的超时时间
	/** implicitlyWait。识别对象时的超时时间。过了这个时间如果对象还没找到的话就会抛出NoSuchElement异常 */
	public void implicitlyWait(int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	/** setScriptTimeout。异步脚本的超时时间。webdriver可以异步执行脚本，这个是设置异步执行脚本脚本返回结果的超时时间 */
	public void setScriptTimeout(int timeOut) {
		driver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
	}

	/**
	 * @Description 对于windows GUI弹出框，要求输入用户名和密码时，
	 *              seleniumm不能直接操作，需要借助http://modifyusername:modifypassword@yoururl 这种方法
	 * 
	 * */
	public void loginOnWinGUI(String username, String password, String url) {
		driver.get(username + ":" + password + "@" + url);
	}

	/** 获得屏幕的分辨率 - 宽 */
	public static double getScreenWidth() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}

	/**
	 * 滚动条显示到元素位置顶端
	 * @param 
	 */
	public static void onScrollByElement(By by) {
		WebElement element = driver.findElement(by);
		//移动到元素element对象的“顶端”与当前窗口的“顶部”对齐  
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * 制定位置移动
	 * @param px
	 */
	public static void onScrollByPx(long px) {
		//移动到指定的坐标(相对当前的坐标移动)  
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 700)"); 
		//移动到窗口绝对位置坐标，如下移动到纵坐标1600像素位置  
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + px
				+ ")");
	}

	/**
	 * 上传文件，需要点击弹出上传照片的窗口才行
	 * 
	 * @param brower
	 *            使用的浏览器名称
	 * @param file
	 *            需要上传的文件及文件名
	 */
	public void handleUpload(String browser, File file) {
		String filePath = file.getAbsolutePath();
		String executeFile = "res/script/autoit/Upload.exe";
		String cmd = "\"" + executeFile + "\"" + " " + "\"" + browser + "\""
				+ " " + "\"" + filePath + "\"";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * 获取文件路径
 */
	public  String  getFilePath(String resource) {
		URL path =this.getClass().getResource(resource);
		logger.info("文件路径是："+ path);
		return  path.toString().replace("file:/","");
		
		
	}

	/**
	 * 通过判断图片的长宽等大于0来判断图片是否正常显示
	 */
	public boolean isImageVisible(WebElement image) {
		Boolean imageLoaded1 = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(
						"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
						image);
		logger.info("image result:" + imageLoaded1);
		if (!imageLoaded1) {
			return false;
		}
		return true;
	}
}
