package pojo;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location{
	private Object lng;
	private Object lat;
}