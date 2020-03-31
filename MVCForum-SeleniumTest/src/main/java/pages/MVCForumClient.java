package pages;

import dto.RegistrationDTO;
import support.Browser;
import support.LoggedInUser;

public class MVCForumClient extends RegistrationPage {
	private Browser browser;
	private static final String HOME_PAGE_URL = "http://localhost";

	public MVCForumClient(Browser browser) {
		super(browser);
		this.browser = browser;
	}

	public MVCForumClient navigateTo() {
		browser.open(HOME_PAGE_URL);
		return this;
	}

	public LoggedInUser RegisterNewUserAndLogIn() {
		var username = "losowyUser"; //Guid.NewGuid().toString();
		final String PASSWORD = "123456789";
		final String E_MAIL = "abc@def.com";

		RegistrationPage registrationPage = GoToRegistrationPage();
		RegistrationDTO registrationDTO = new RegistrationDTO(username, PASSWORD, PASSWORD, E_MAIL);
		registrationPage.register(registrationDTO);

		return new LoggedInUser();
	}

	public LatestDiscussions latestDiscussions() {
		return null;
	}
}
