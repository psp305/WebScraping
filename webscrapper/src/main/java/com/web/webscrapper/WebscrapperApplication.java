package com.web.webscrapper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class WebscrapperApplication {
	
	 @Bean
	  public RBIScrapper schedulerRunner() {
	    return new RBIScrapper();
	  }
	 
	public static void main(String[] args) {
		SpringApplication.run(WebscrapperApplication.class, args);
	}

	/**
	@Scheduled(cron = "0 0 9 * * *") // Runs at 9:00 AM IST every day
	public void readWebPage() {
		// Set the path to your ChromeDriver executable
	      System.setProperty("webdriver.chrome.driver", "Z:\\chromedriver-win64\\chromedriver.exe");

	  	
	      // Initialize the Chrome driver
	      //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

	      WebDriver driver = new ChromeDriver();
	      driver.get("https://rbi.org.in/Scripts/BS_PressReleaseDisplay.aspx");

	      // Click on the "Money Market Operations as on February 12, 2024" link
	      WebElement link = driver.findElement(By.xpath("//a[contains(text(),'Money Market Operations as on February 14, 2024')]"));
	      link.click();
	      // Wait for the page to load
	      try {
	          Thread.sleep(5000); // Wait for 5 seconds (you may need to adjust this)
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      }
	      
	      StringBuffer text=new StringBuffer("");
	      int i=0;
	      boolean flag=false;
	      // Read data from <tr> tags with class tableContent1
	      List<WebElement> rows = driver.findElements(By.cssSelector("tr.tableContent1"));
	      for (WebElement row : rows) {
	      	long startTime = System.currentTimeMillis();
	      	System.out.println(row.getText());
	      	
	      	
	      	if(row.getText().replaceAll("[\\d.\\s]+", "").equals("IIOutstandingOperations")) {
	      		flag=true;
	      	}
	      	
	      	
	      	else if(row.getText().contains("3. MSF#")) {
	      		if(row.getText().replaceAll("[\\d.\\s]+", "").equals("MSF#")) {
	      			flag=false;
	      			break;
	      		}
	      	}
	      	
	      	if(flag==true) {
	      		text.append(row.getText());
	      		text.append(System.getProperty("line.separator"));
	      	}
	      	long elapsedTime = System.currentTimeMillis() - startTime;
	          if (elapsedTime < 2000) {
	              try {
	                  Thread.sleep(2000 - elapsedTime); // Sleep for the remaining time to ensure at least 3 seconds
	              } catch (InterruptedException e) {
	                  e.printStackTrace();
	              }
	          }
	      	
	      	
	      		i++;
	          
	      }
	      System.out.println("--------------------------------------------------------------------");
	      System.out.println(text);
	  }  
	  **/
}
