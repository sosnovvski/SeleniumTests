package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInAdmin extends LoggedInUser {

	@FindBy(className = "mytoolslink")
	private WebElement myToolslink;

	@FindBy(className = "auto-admin")
	private WebElement adminDropdownElement;

	public AdminPage GoToAdminPage() {
		myToolslink.click();
		adminDropdownElement.click();

		return new AdminPage();
	}
}
