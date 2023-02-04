package pojo.autoGenerator;

import lombok.Data;

@Data
public class Response{
	private String expand;
	private String self;
	private String id;
	private Fields fields;
	private String key;
}