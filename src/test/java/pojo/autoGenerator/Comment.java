package pojo.autoGenerator;

import java.util.List;
import lombok.Data;

@Data
public class Comment{
	private int total;
	private List<CommentsItem> comments;
	private int maxResults;
	private int startAt;
}