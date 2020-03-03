import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import org.openqa.selenium.JavascriptExecutor;

//Then set something
    @Then("^set something$")
    public void set_something() throws Exception {
// CLICK AREA
Driver.findElement(By.xpath("//*[@id='pane-4']/div/div/div[1]/div[1]/div[2]/div/div/div/div[2]/div/div/input")).click();       
Thread.sleep(2000);


/**
* STARTING OF RELATIVE XPATH ARER
*/
int m = 1; //LIST VARIABLE
int n = 1; //DIV VARIABLE
String getText;
String path_new;
String path_new2;
String getText_new;
String path1 = "/html/body/div";
String path2 = path1 + "["+n+"]/div[1]/div[1]/ul/li" + "["+m+"]";
getText = "nothing found"; // arbitrary string

for(n=3; n<6; n++){
    m=1;
    if(!"some string".equals(getText)){
        try{
        
            path_new = path1 + "["+n+"]/div[1]/div[1]/ul/li" + "["+m+"]";
            
            System.out.println("n="+n); // TO KEEP TRACK OF N
            getText_new = Driver.findElement(By.xpath(path_new)).getText(); // ERROR PRONE
            getText = getText_new;
            System.out.println(getText);
            
            Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // TO REDUCE THE ERROR EXCEPTION TIME

            while(!"some string".equals(getText)){
                m++;
                path_new = path1 + "["+n+"]/div[1]/div[1]/ul/li" + "["+m+"]";
                System.out.println("m="+m); // TO KEEP TRACK OF M
                getText_new = Driver.findElement(By.xpath(path_new)).getText();
                getText = getText_new;
                System.out.println(getText); // TO KEEP TRACK OF THE FILTERS
                if("some string".equals(getText)){                            
                    String path4 = path1 + "["+n+"]/div[1]/div[1]/ul/li" + "["+m+"]";
                    System.out.println("FOUND! path:" + path4);
                    Driver.findElement(By.xpath(path4)).click();                            
                }
                                        
            }
		Driver.findElement(By.xpath(path_new)).click();
        }            
        catch(org.openqa.selenium.NoSuchElementException ex){
            getText = "nothing found";
            System.out.println("catching outer loop");
        }
    }            
}
/**
 * ENDING OF RELATIVE XPATH AREA
 */

Thread.sleep(2000);
System.out.println("out of loop");



//------------------------------------get id attribute and moving mouse---------------------------------------------------------------------

Driver.findElement(By.xpath("//*[@id='pane-basicTab']/div/div[4]/div[2]/table/tbody/tr/td[2]/div/span/button")).click();       
        Thread.sleep(2000);
        
        // GET INFO ABOUT RELATIVE ID
        String id = Driver.findElement(By.xpath("//div[@class='el-popover el-popper']")).getAttribute("id");        
        System.out.println(id);
        Thread.sleep(2000);
        
        // CLICK to popover area        
        Actions action = new Actions(Driver);
        String path = "//*[@id='" + id + "']/div[2]/span/button";
        System.out.println(path);
        Thread.sleep(2000);
        action.moveToElement(Driver.findElement(By.xpath(path))).perform();





        



        // another solution for TOOLTIP POPUP
        Actions builder = new Actions(Driver);
       WebElement tooltip = Driver.findElement(By.xpath("//*[@id='pane-1']/div/div/div[1]/div[2]/div[1]/i"));
       builder.moveToElement(tooltip).perform();
       String tooltip_msg = tooltip.getText();
       System.out.println(tooltip_msg);




       // ANOTHER
       List<WebElement> list = Driver.findElements(By.xpath(".//*[@class='el-popover el-popper']"));
        for(WebElement el : list) {
            System.out.println(el.getAttribute("id"));
            String id = el.getAttribute("id");
            String path = "//*[@id='" + id + "']/div[2]/span/button/span/span[1]";
            Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            try{
                    System.out.println(Driver.findElement(By.xpath(path)).getText());
            }
            catch(org.openqa.selenium.NoSuchElementException ex){
                System.out.println("Error");
            }
        

        }




















        //-----------------------------------javascript-------------------------------------------------------------------
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        // CLICK dropdown list 
        Driver.findElement(By.xpath("//*[@id='pane-1']/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div[2]/div/div/input")).click();       
        Thread.sleep(2000);
        int m = 1;
        String path1 = "//div[contains(@class, 'el-select-dropdown el-popper')]/div[1]/div[1]/ul/li";



        String path2 = path1 + "["+m+"]/span";
        String getText = Driver.findElement(By.xpath(path2)).getText();
        while(!"some string".equals(getText)) {
            m++;
            String path_new = path1 + "["+m+"]/span";
            String getText_new = Driver.findElement(By.xpath(path_new)).getText();
            getText = getText_new;
            System.out.println(getText);
        }
        String path3 = path1 + "["+m+"]/span";

        WebElement cc = Driver.findElement(By.xpath(path3));
        js.executeScript("arguments[0].click();", cc);

        

        //ANOTHER ONE
        Actions action = new Actions(Driver);
		JavascriptExecutor js = (JavascriptExecutor) Driver;
		action.moveToElement(Driver.findElement(By.xpath("//*[@id='app']/div[2]/div[2]/div[2]/div/div[3]/div[1]/div/div[3]/table/tbody/tr[3]/td"))).perform();
		Thread.sleep(2500);
		WebElement edit = Driver.findElement(By.className("NAME OF CLASS"));
		js.executeScript("arguments[0].click();", edit);







        // -----------------------------------------------jsoup --------------------------------------------------------------------------

    //search by something
    Driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Abir'])[1]/following::input[1]")).click();
    Driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Abir'])[1]/following::input[1]")).clear();
    Driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Abir'])[1]/following::input[1]")).sendKeys("abir");
    Driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Abir'])[1]/following::i[1]")).click();
    Thread.sleep(2000);
    //click Show All Task
    Driver.findElement(By.xpath("//*[@id='table']/div[2]/div/button[4]/span")).click();
    Thread.sleep(2000);
    //from source verify there is no "77"
    String source = Driver.getPageSource();
    Document doc = Jsoup.parse(source);
    Elements content = doc.getElementsByTag("tbody");
    String gettext = content.text();
    System.out.println(gettext);
    if (!gettext.contains("abir"))
        {
            System.out.println("Verified abir deletion!");
        }
    Thread.sleep(2000);

















    //----------------------------------------------CSSVALUE------------------------------------------------------
    
    String gettext = Driver.findElement(By.xpath("//*[@id='pane-basicTab']/div/div[4]/div[2]/table/tbody/tr/td[1]/div/span")).getCssValue("background-color");
    Thread.sleep(1000);
    
    if (gettext.contains("rgba(255, 255, 0, 1)")){
        System.out.println("Status Color : yellow");
    }
    else if(gettext.contains("rgba(0, 128, 0, 1)")){
        System.out.println("Status Color : green");
    }
    else if(gettext.contains("rgba(255, 0, 0, 1)")){
        System.out.println("Status Color : red");
    }
    else{
        System.out.println("NO HARD CODED color info available. RGB: " + gettext);
    }














    //---------------------------------------------LIST--------------------------------------------------
    
    List rows = Driver.findElements(By.xpath("//*[@id='pane-basicTab']/div/div[4]/div[2]/table/tbody/tr"));
        //System.out.println(rows.size());	
        // AGAIN VERIFY FIRST
        for (int n = 1; n <= rows.size(); n++) {

			String path1 = "//*[@id='pane-basicTab']/div/div[4]/div[2]/table/tbody/tr";
			String path = path1 + "["+n+"]/td[5]/div";
			String getText = Driver.findElement(By.xpath(path)).getText();
			if ("SOME STRING".equals(getText)) {
                System.out.println("Found Test SOME STRING");
                // CLICK EDIT
                Driver.findElement(By.xpath("//*[@id='pane-basicTab']/div/div[4]/div[2]/table/tbody/tr/td[2]/div/button[2]")).click();
               
            }
            else{
                System.out.println("Not Exist Test SOME STRING");
            }
        }
        
        
















    // --------------------------------- UPLOAD ONE OR MANY FILES -------------------------------------------------//
    // import java.awt.AWTException;
    // import java.awt.Robot;
    // import java.awt.event.KeyEvent;
    // import java.awt.Toolkit;
    // import java.awt.datatransfer.StringSelection;

    
    // And upload many file
    @And("^upload many files$")
    public void upload_many_files() throws InterruptedException, AWTException {       
        
        List rows = Driver.findElements(By.xpath("/div[4]/div/div[2]/table/tbody/tr"));
        System.out.println("Number of rows before uploading many files: " + rows.size());
        Thread.sleep(2000);
        // CLICK UPLOAD AREA
        Driver.findElement(By.xpath("/div[4]/div/div[2]/following::div[7]")).click();
        Thread.sleep(2000);

        String path1 = "C:\\Users\\your username\\Desktop\\name\\newfolder\\name.zip"; //LOCATION OF THE FILE TO BE UPLOADED
        
        // CREATING OS CLICPBOARD ENVIRONMENT
        StringSelection stringselection = new StringSelection(path1);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
        
        // KEYBOARD COMMAND USING ROBOT CLASS
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);

        // UPLOAD 2nd FILE
        Driver.findElement(By.xpath("/div[4]/div/div[2]/following::div[7]")).click();
        Thread.sleep(2000);
        
        String path_2nd = "C:\\Users\\your username\\Desktop\\name\\newfolder\\name.jpg";//LOCATION OF THE 2ND FILE TO BE UPLOADED
        
        // CREATING OS CLICPBOARD ENVIRONMENT FOR 2ND FILE
        StringSelection stringselection_2 = new StringSelection(path_2nd);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection_2, null);  

        // KEYBOARD COMMAND USING ROBOT CLASS      
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);




        // CLICK UPLOAD
        Driver.findElement(By.xpath("/div[4]/div/div[2]/div/button")).click();
        Thread.sleep(20000);
        
        
    }
    

}














// ------------------------------------------------- RELATIVE POPOVER ID ---------------------------------------------------------//
//And SOMETHING
    @And("^SOMETHING$")
    public void SOMETHING() throws InterruptedException, AWTException {
        // CLICK MORE
        Driver.findElement(By.xpath("//*[@id='pane-basicTab']/div/div[4]/div[2]/table/tbody/tr/td[2]/div/span/button/i")).click();
        Thread.sleep(3000);
        

        Actions action = new Actions(Driver);
        String path;
        String path2;
        String id;
        String popover_id;
        List<WebElement> list = Driver.findElements(By.xpath(".//*[@class='el-popover el-popper']"));
        for(WebElement el : list) {
            //System.out.println(el.getAttribute("id")); // USE FOR DEBUG
            id = el.getAttribute("id");
            path = "//*[@id='" + id + "']/div[2]/span/button/span/span[1]";
            //path2 = "//*[@id='" + id + "']/div[1]/div[2]/button/span/span"; //NOT USED IN THIS LOOP
            //System.out.println(path); // USE FOR DEBUG
            Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            try{
                popover_id = Driver.findElement(By.xpath(path)).getText();
                

                if("mYTEXT".equals(popover_id)){
                    System.out.println("\n\n\n\n\n\n\n\n\nFound mYTEXT");
                    Thread.sleep(2000);
                    action.moveToElement(Driver.findElement(By.xpath(path))).perform();
                    Thread.sleep(3000);
                    break;

                }
            }
            catch(org.openqa.selenium.NoSuchElementException ex){
                System.out.println("I encounterd Error"); // USE FOR DEBUG
            }
            
        }


        // CLICKING B2B LINK
        for(WebElement el : list) {
            //System.out.println(el.getAttribute("id")); // USE FOR DEBUG
            id = el.getAttribute("id");            
            path2 = "//*[@id='" + id + "']/div[1]/div[2]/button/span/span";
            //System.out.println(path); // FOR DEBUG
            Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            try{
                popover_id = Driver.findElement(By.xpath(path2)).getText();
                

                if("my Link".equals(popover_id)){
                    System.out.println("\n\n\n\n\n\nFound my Link");
                    Thread.sleep(2000);
                    Driver.findElement(By.xpath(path2)).click();
                    Thread.sleep(3000);                    
                    break;

                }
            }
            catch(org.openqa.selenium.NoSuchElementException ex){
                System.out.println("I encounterd Error"); // USE FOR DEBUG
            }
            
        }
   

    }



















// ------------------------------------- TO SCROLL DOWN THE WEB PAGE BY THE VISIBILITY OF THE ELEMENT ----------------------------------------------//
// #1
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ScrollByVisibleElement {

    WebDriver driver;
    @Test
    public void ByVisibleElement() {
        System.setProperty("webdriver.chrome.driver", "G://chromedriver.exe");
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Launch the application		
        driver.get("http://demo.guru99.com/test/guru99home/");

        //Find element by link text and store in variable "Element"        		
        WebElement Element = driver.findElement(By.linkText("Linux"));

        //This will scroll the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }
}
// Script Description: In the above code, we first launch the given url in Chrome browser. Next, scroll the page until the mentioned element is visible on the current page. Javascript method scrollIntoView() scrolls the page until the mentioned element is in full view :

        // js.executeScript("arguments[0].scrollIntoView();",Element );	
        // "arguments[0]" means first index of page starting at 0.
        
        // Where an " Element " is the locator on the web page.
        
        // Output analysis : Here is the output when you execute the above script .










// ------------------------------------- TO SCROLL DOWN THE WEB PAGE AT THE BOTTOM OF THE PAGE ----------------------------------------------//



Selenium Script

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ScrollByPage {

    WebDriver driver;
    @Test
    public void ByPage() {
        System.setProperty("webdriver.chrome.driver", "E://Selenium//Selenium_Jars//chromedriver.exe");
        driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Launch the application		
        driver.get("http://demo.guru99.com/test/guru99home/");

        //This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
//Script Description : In the above code, we first launch the given url in Chrome browser. Next, scroll till the bottom of the page. Javascript method scrollTo() scroll the till the end of the page .

//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");		
//"document.body.scrollHeight" returns the complete height of the body i.e web page.

//Output analysis: Here is the output when you execute the above script.




// ------------------------------------- WAIT FOR AN ELEMENT TO APPEAR ----------------------------------------------//
// 
@And("^waitlogo$")
    public WebElement waitlogo() {
        WebDriverWait wait = new WebDriverWait(Driver, 10);
		Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement element = wait.until(ExpectedConditions.visibilityOf
               (Driver.findElement(By.id("Signup"))));			   
        return element;
    }



