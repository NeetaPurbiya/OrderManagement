package com.orderms.order.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemResponse {
	@JsonSerialize
	private long id;

	private String productCode;

	private String productName;

	private long quantity;

	private long orderId;
}
