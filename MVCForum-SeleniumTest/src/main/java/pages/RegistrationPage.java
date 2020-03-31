package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dtos.RegistrationDTO;
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

	@FindBy(className = "form-register")
	private WebElement formRegister;

	public RegistrationPage(Browser browser) {
		browser.find(By.className("auto-register")).
				click();
		this.browser = browser;
		PageFactory.initElements(browser.getDriver(), this);
	}

	protected void register(RegistrationDTO registrationDTO) {
		username.sendKeys(registrationDTO.getLogInDTO().getUserName());
		password.sendKeys(registrationDTO.getLogInDTO().getPassword());
		confirmPassword.sendKeys(registrationDTO.getConfirmPassword());
		email.sendKeys(registrationDTO.getEmail());

		formRegister.submit();
	}

}
