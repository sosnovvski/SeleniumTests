package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInDTO {
	private String userName;
	private String password;

	public LogInDTO(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
}
