package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dtos.LogInDTO;
import support.Browser;

public class LoginPage {

	@FindBy(id = "UserName")
	private WebElement username;

	@FindBy(id = "Password")
	private WebElement password;

	@FindBy(className = "form-login")
	private WebElement formRegister;

	public LoginPage(Browser browser) {
		PageFactory.initElements(browser.getDriver(), this);
	}

	//	public Class<?> logIn(LogInDTO logInDTO, Class<?> logInAs) {
	//
	//		username.sendKeys(logInDTO.getUserName());
	//		password.sendKeys(logInDTO.getPassword());
	//
	//		formRegister.submit();
	//
	//		ReflectionUtils.newInstance(logInAs);
	//		return logInAs;
	//	}

	public void logIn(LogInDTO logInDTO) {

		username.sendKeys(logInDTO.getUserName());
		password.sendKeys(logInDTO.getPassword());

		formRegister.submit();
	}
}
