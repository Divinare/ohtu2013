package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
//        WebDriver driver = new HtmlUnitDriver();
//
//        driver.get("http://localhost:8080");
//        System.out.println(driver.getPageSource());
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//
//        System.out.println("==");
//
//        System.out.println(driver.getPageSource());
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element = driver.findElement(By.name("login"));
//        element.submit();
//
//        System.out.println("==");
//        System.out.println(driver.getPageSource());


        System.out.println("");
        System.out.println("epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana");
        System.out.println("");
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        System.out.println(driver.getPageSource());
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println("==");

        System.out.println(driver.getPageSource());
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaara");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println("==");
        System.out.println(driver.getPageSource());


        System.out.println("");
        System.out.println("epäonnistunut kirjautuminen: eiolemassaoleva käyttäjätunnus");
        System.out.println("");
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println("==");

        System.out.println(driver.getPageSource());
        element = driver.findElement(By.name("username"));
        element.sendKeys("vaara");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println("==");
        System.out.println(driver.getPageSource());




        System.out.println("");
        System.out.println("uuden käyttäjätunnuksen luominen");
        System.out.println("");

        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println("==");

        System.out.println(driver.getPageSource());
        element = driver.findElement(By.name("username"));
        element.sendKeys("divinar3");
        element = driver.findElement(By.name("password"));
        element.sendKeys("1qwertyui");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("1qwertyui");
        element = driver.findElement(By.name("add"));
        element.submit();

        System.out.println("==");
        System.out.println(driver.getPageSource());










        System.out.println("");
        System.out.println("uuden käyttäjätunnuksen luominen ja luodulla käyttäjätunnuksella kirjautuminen");
        System.out.println("");


        System.out.println("");
        System.out.println("uuden käyttäjätunnuksen luominen");
        System.out.println("");

        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println("==");

        System.out.println(driver.getPageSource());
        element = driver.findElement(By.name("username"));
        element.sendKeys("divinar4");
        element = driver.findElement(By.name("password"));
        element.sendKeys("1qwertyui");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("1qwertyui");
        element = driver.findElement(By.name("add"));
        element.submit();

        System.out.println("==");
        System.out.println(driver.getPageSource());
        System.out.println("==");

        driver.get("http://localhost:8080");
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println("==");

        System.out.println(driver.getPageSource());
        element = driver.findElement(By.name("username"));
        element.sendKeys("divinar4");
        element = driver.findElement(By.name("password"));
        element.sendKeys("1qwertyui");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println("==");
        System.out.println(driver.getPageSource());

    }
}
