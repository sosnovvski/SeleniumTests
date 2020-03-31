package pages;

import dtos.LogInDTO;
import dtos.RegistrationDTO;
import support.Browser;
import support.TestDefaults;

public class MVCForumClient {
	private Browser browser;
	private static final String HOME_PAGE_URL = "http://localhost";

	public MVCForumClient(Browser browser) {
		this.browser = browser;
	}

	public MVCForumClient navigateTo() {
		browser.open(HOME_PAGE_URL);
		return this;
	}

	public LoggedInUser RegisterNewUserAndLogIn() {
		LogInDTO logInDTO = new LogInDTO("losowyUser", "123456789"); //Guid.NewGuid().toString();
		final String E_MAIL = "abc@def.com";

		RegistrationPage registrationPage = new RegistrationPage(browser);
		RegistrationDTO registrationDTO = new RegistrationDTO(logInDTO, logInDTO.getPassword(), E_MAIL);
		registrationPage.register(registrationDTO);

		return new LoggedInUser();
	}

	public LatestDiscussions latestDiscussions() {
		return null;
	}

	public LoggedInAdmin LogInAsAdmin() {
		LogInDTO logInDTO = new LogInDTO(TestDefaults.ADMIN_USER_NAME, TestDefaults.ADMIN_PASSWORD);

		LoginPage loginPage = new LoginPage(browser);
		loginPage.logIn(logInDTO);

		return new LoggedInAdmin();
	}
}
