package pojo.autoGenerator;

import lombok.Data;

@Data
public class CommentsItem{
	private Visibility visibility;
	private Author author;
	private String created;
	private UpdateAuthor updateAuthor;
	private String self;
	private String id;
	private String body;
	private String updated;
}