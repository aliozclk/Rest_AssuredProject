package pojo;

import lombok.*;


@Data
public class LoginRequest{
	private String userPassword;
	private String userEmail;
}