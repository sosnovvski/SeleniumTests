package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import dtos.RoleDTO;

public class AdminPage {

	@FindBy(className = "auto-permissions")
	private WebElement permissionsMenuItem;

	@FindBy(className = "auto-menagePermission")
	private WebElement menagePermissionSubMenuItem;

	@FindBy(className = "auto-roleButton")
	private List<WebElement> roleButtons;

	public AdminPage() {
	}

	public RolePermissionsPage GetPermissionsFor(RoleDTO roleDTO) {
		permissionsMenuItem.click();
		menagePermissionSubMenuItem.click();

		WebElement roleButton = fetchRoleButton(roleDTO.getName());
		roleButton.click();
		return new RolePermissionsPage();
	}

	private WebElement fetchRoleButton(String roleName) {
		return roleButtons.stream().filter(webElement -> webElement.getText().equals(roleName)).findFirst()
				.orElseThrow(() -> new IllegalStateException("Role button not found"));
	}
}
