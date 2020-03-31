package dtos;

import lombok.Getter;

@Getter
public class RoleDTO {
	private String name;

	public RoleDTO(String name) {
		this.name = name;
	}
}
