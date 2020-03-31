package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
	private String userName;
	private String password;
	private String confirmPassword;
	private String email;

	public RegistrationDTO(String userName, String password, String confirmPassword, String email) {
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}
}
