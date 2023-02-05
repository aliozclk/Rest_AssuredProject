package pojo;

import lombok.*;


@Data
public class LoginResponse{
	private String message;
	private String userId;
	private String token;
}