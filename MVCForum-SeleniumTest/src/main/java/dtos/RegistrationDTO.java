package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
	private LogInDTO logInDTO;
	private String confirmPassword;
	private String email;

	public RegistrationDTO(LogInDTO logInDTO, String confirmPassword, String email) {
		this.logInDTO = logInDTO;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}
}
