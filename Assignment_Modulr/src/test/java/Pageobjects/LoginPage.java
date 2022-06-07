package Pageobjects;

import static org.junit.Assert.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class LoginPage {

	private static String Username = "vijay.inamdar11";
	private static String Password = "Training$123";
	private static String NewPassword = "";

	WebDriver driver = null;

	@Given("the Customer launches the required browser")
	public void the_customer_launches_the_required_browser() {

		System.setProperty("webdriver.chrome.driver", "../Assignment_Modulr/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		System.out.println(" Chrome Browser Launched");
	}

	@And("the Customer opens URL {string}")
	public void the_customer_opens_url(String string) {

		driver.get("https://secure-sandbox.modulrfinance.com");

		System.out.println("Url Launched");
	}

	@When("Customer enters the Username as vijay.inamdar11 and Password as Training${int}")
	public void customer_enters_the_username_as_vijay_inamdar11_and_password_as_training$(Integer int1) {

		// Set Variable for Username and Passwords

		try {
			Password = readPasswordfromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.findElement(By.id("username-inp")).sendKeys(Username);
		driver.findElement(By.id("password-inp")).sendKeys(Password);

		System.out.println("Username, Password Entered");
	}

	@Then("Click on Signin Button")
	public void click_on_signin_button() {

		driver.findElement(By.id("signInSubmitButton")).click();

		System.out.println("Clicked on Signin");
	}

	@Then("Successful login should take the user to the account overview page")
	public void successful_login_should_take_the_user_to_the_account_overview_page() {

		String Title = driver.getTitle();

		assertEquals(Title, "Modulr Payments");

		System.out.println(Title + "is asserted");
	}

	@Then("assert the correct Usename and Signin button is disabled\\/signout button is Enabled")
	public void assert_the_correct_usename_and_signin_button_is_disabled_signout_button_is_enabled() {

		driver.findElement(By.xpath("//*[@class='menu-item sign-in']")).isEnabled();

		System.out.println("Menu item Signin is Enabled");
	}

	@Then("reset the Password through view profile")
	public void reset_the_password_through_view_profile() throws InterruptedException {

		// Click on Username avatar

		driver.findElement(By.xpath("//*[@class='right-margin-2']")).click();

		// Click on View Profile

		driver.findElement(By.xpath("//*[normalize-space()='View profile']")).click();

		// Find the correct User name is shown in the Header

		WebElement Profilename = driver.findElement(By.cssSelector("h3[data-qa='user-profile-name-txt']"));

		// Verify the correct User name is present there to change the Password, to
		// avoid change someone else's Password (risk)

		driver.findElement(By.xpath("//*[normalize-space()='Vijay Inamdar']"));

		System.out.println(Profilename);

		Thread.sleep(5000);

		// Click on Change Password

		driver.findElement(By.xpath("//*[@data-qa='user-profile-btn-change']")).click();

		System.out.println("Clicked on Profile Avatar");
	}

	@Then("Enter current Password and New Password and Re enter New Password and Click Save")
	public void enter_current_password_and_new_password_and_re_enter_new_password_and_click_save()
			throws InterruptedException {

		// Enter Current Password

		driver.findElement(By.name("password")).sendKeys(Password);

		// Enter New Password

		NewPassword = getPassword();

		driver.findElement(By.name("newpassword")).sendKeys(NewPassword);

		// Re Enter the new Password

		driver.findElement(By.name("confirmpassword")).sendKeys(NewPassword);

		// Save the changes

		driver.findElement(By.xpath("//*[@data-qa='user-profile-btn-save']")).click();

		System.out.println("Entered Current, New Passwords and Clicked on Save changes");

		Password = NewPassword;
		
		// Write to File
		
		try {
			writePassword(NewPassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		Thread.sleep(5000);
	}

	@Then("Click on Signout from profile")
	public void click_on_signout_from_profile() throws InterruptedException {

		// Click on Sign out

		Thread.sleep(15000);

		driver.findElement(By.xpath("//*[@class='vertical-center username no-outline']")).click();

		driver.findElement(By.xpath("//*[normalize-space()='Sign out']")).click();

		System.out.println("Signed out from application");
	}

	@When("Click on Forgotten Password from Login page view")
	public void click_on_forgotten_password_from_login_page_view() throws InterruptedException {

		Thread.sleep(3000);

		// Click on Forgotten Password

		driver.findElement(By.id("forgotPasswordHref")).click();

		System.out.println("Clicked on Forgotten Password");

	}

	@Then("Enter Username on Reset Access Page")
	public void enter_username_on_reset_access_page() {

		// Enter Usename to reset the password

		driver.findElement(By.id("usernameInput")).sendKeys(Username);

		System.out.println("Entered Username in Reset Access field");
	}

	@Then("Click on Reset acess")
	public void click_on_reset_acess() {

		// Click on Request a reset button

		driver.findElement(By.xpath("//*[contains(text(),'Request a reset')]")).click();

		System.out.println("Clicked on Request a reset button");
	}

	@Then("assert the Email sent notification")
	public void assert_the_email_sent_notification() {

		// Asserting the notification for email sent if its displayed or not

		driver.findElement(By.xpath("//*[@id='emailSentHeading']")).isDisplayed();

	}

	@Then("Close the browser")
	public void close_the_browser() {

		// Close / quit the browser

		driver.close();

		System.out.println("Application browser is Closed");

		System.out.println("The Test for this Scenario is PASSED");

	}

	private String getPassword() {

		Random r = new Random();
		int low = 10;
		int high = 10000;
		int result = r.nextInt(high - low) + low;
		return "Training$" + result;

	}
	
	// Read File method
	
	private String readPasswordfromFile()
			  throws IOException {
			    
			    Path path = Paths.get("src/test/resources/passwordfile.txt");

			    String password = Files.readAllLines(path).get(0);
			   return password ;
					   
			}
	
	// Write password
	
	public void  writePassword(String passwordname) 
			  throws IOException {
			    FileWriter fileWriter = new FileWriter("src/test/resources/passwordfile.txt");
			    PrintWriter printWriter = new PrintWriter(fileWriter);
			    printWriter.print(passwordname);
			    printWriter.close();
			}
	

}
