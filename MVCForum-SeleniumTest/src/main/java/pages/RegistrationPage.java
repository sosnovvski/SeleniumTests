package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dto.RegistrationDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import support.Browser;

class RegistrationPage {
	private Browser browser;
	private RegistrationDTO registrationDTO;

	@FindBy(id = "UserName")
	private WebElement username;

	@FindBy(id = "Password")
	private WebElement password;

	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPassword;

	@FindBy(id = "Email")
	private WebElement email;

	public RegistrationPage(Browser browser) {
		this.browser = browser;
		PageFactory.initElements(browser.getDriver(), this);
	}

	protected RegistrationPage GoToRegistrationPage() {
		browser.find(By.className("auto-register")).
				click();
		return new RegistrationPage(browser);
	}

	protected void register(RegistrationDTO registrationDTO) {
		username.sendKeys(registrationDTO.getUserName());
		password.sendKeys(registrationDTO.getPassword());
		confirmPassword.sendKeys(registrationDTO.getConfirmPassword());
		email.sendKeys(registrationDTO.getEmail());
	}

}
