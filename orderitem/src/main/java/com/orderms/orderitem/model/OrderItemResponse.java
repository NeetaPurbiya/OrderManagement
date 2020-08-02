package com.orderms.orderitem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemResponse {
	private String productCode;
	private String productName;
	private long quantity;
	private long orderId;
}
