package com.web.webscrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.CommandLineRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RBIScrapper implements CommandLineRunner {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "Z:\\chromedriver-win64\\chromedriver.exe");
    	//System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    	
        // Initialize the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigate to the RBI webpage
        driver.get("https://rbi.org.in/Scripts/BS_PressReleaseDisplay.aspx");

        // Find all hyperlinks on the page
        List<WebElement> hyperlinks = driver.findElements(By.tagName("a"));

        // Loop through each hyperlink and check if it contains the desired text
        for (WebElement link : hyperlinks) {
            if (link.getText().equals("Money Market Operations as on February 12, 2024")) {
                // Click on the hyperlink
                link.click();

                // Get the data from the page that opens after click
                String pageData = driver.getPageSource();

             // Wait for the page to load (you might need to adjust the wait time)
                try {
                    Thread.sleep(5000); // Wait for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Find the element containing the desired information
                List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='tableContent1']"));

                // Initialize a StringBuilder to store the data
                StringBuilder data = new StringBuilder();
                ArrayList<Integer> ar = new ArrayList<>();
                
                // Iterate through each row
                for (WebElement row : rows) {
                    // Find all the columns in the current row
                    List<WebElement> columns = row.findElements(By.tagName("td"));
                    //System.out.println(row.getText());
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
                    for(int i = 8; i<9; i++) {
                    	System.out.println(columns.get(i).getText());
                    }
                    
                    
                    // Append a newline character after each row
                    data.append("\n");
                }

                // Write the data to a text file
                try (FileWriter writer = new FileWriter("webpage.txt")) {
                	writer.write(data.toString());
                    System.out.println("Data has been written to output.txt");
                } catch (IOException e) {
                    System.err.println("Failed to write data to file: " + e.getMessage());
                }


                // No need to search further, break the loop
                break;
            }
        }

        // Close the WebDriver
        driver.quit();
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		main(null);
		
	}
}
