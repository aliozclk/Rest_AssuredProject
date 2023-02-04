package pojo;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddPlace{
	private List<String> types;
	private String website;
	private String address;
	private String name;
	private int accuracy;
	private Location location;
	private String phoneNumber;
	private String language;
}