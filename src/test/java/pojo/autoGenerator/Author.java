package pojo.autoGenerator;

import lombok.Data;

@Data
public class Author{
	private String emailAddress;
	private AvatarUrls avatarUrls;
	private String displayName;
	private String name;
	private String self;
	private boolean active;
	private String timeZone;
	private String key;
}