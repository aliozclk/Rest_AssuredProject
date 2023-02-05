package pojo;

import java.util.List;
import lombok.Data;

@Data
public class CreateOrderRequest{
	private List<OrdersItem> orders;
}