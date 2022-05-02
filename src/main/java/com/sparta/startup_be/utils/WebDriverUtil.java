package com.sparta.startup_be.utils;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebDriverUtil {

    private WebDriver driver;
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver"; // Properties 설정
    public static String WEB_DRIVER_PATH = "C:/Users/82103/Desktop/sparta/chromedriver.exe"; // WebDriver 경로

    public WebDriverUtil() {
        chrome();
    }

    private void chrome() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // webDriver 옵션 설정.
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);
        // weDriver 생성.
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void useDriver(String url) throws InterruptedException {
        Actions action = new Actions(driver);
        driver.get(url) ;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.
        log.info("++++++++++++++++++++++===================+++++++++++++ selenium : " + driver.getTitle());
        try{
            driver.findElement(By.className("btn_option")).click();
        }catch(Exception e) {
            e.printStackTrace();
        }
        WebElement item = driver.findElement(By.className("article_box"));
//        System.out.println(item.getText());
//        action.dragAndDropBy(items,960,350).perform();
//        WebElement item =driver.findElement(By.xpath("//*[@id=\"_listContainer\"]/div/div[3]"));
//        action.moveByOffset(960,350).perform();
//        action.moveToElement(items,918,350);
//        action.click().perform();
        int m = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"_listContainer\"]/div/div[1]/a/h3/strong")).getText());
        int j=0;
        while(true) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0, document.body.scrollHeight)", item);
            Thread.sleep(1);
            j++;
            if(j==m) break;
        }
//        System.out.println(item.getText());
//        var stTime =new Date().getTime();
//        System.out.println(stTime);
//        while(new Date().getTime()< stTime+30000) {
//            Thread.sleep(500);
//            action.click().perform();
//            ((JavascriptExecutor) driver).executeScript("argument[0].scrollBy(0,document.body.scrollHeight",item);
//            ((JavascriptExecutor) driver).executeScript("window.onload=function(){\n" +
//                    "    let mySpace = document.getElementByClassName(\"article_box\"); \n" +
//                    "    mySpace.scrollTop = mySpace.scrollHeight;\n" +
//                    "};");


        List<WebElement> webElements = driver.findElements(By.className("item_area"));
//        for(WebElement webElement : webElements){
//            System.out.println(webElement);
//        }

        int i=0;
        for(WebElement webElement : webElements){
            System.out.println(i);
            System.out.println("타입:"+webElement.findElement(By.className("type")).getText());
            System.out.println("매물이름:"+webElement.findElement(By.className("title_place")).getText()+webElement.findElement(By.className("title_building")).getText());
            System.out.println("가격:"+webElement.findElement(By.className("price")).getText());
            System.out.println("정보:"+webElement.findElement(By.className("info")).getText());
            System.out.println("부동산이름:"+webElement.findElement(By.className("type")).getText());
            i++;
        }
        log.info("++++++++++++++++++++++===================+++++++++++++ 끝 : " );

        quitDriver();
    }

    private void quitDriver() {
        driver.quit(); // webDriver 종료
    }

}


