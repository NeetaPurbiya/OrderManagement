package com.orderms.order.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponseMap {
	private long orderId;
	private String customerName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "Asia/Kolkata")
	private Date orderDate;
	private String shippingAddress;
	private long total;
	private List<OrderItemResponse> orderItemResponse;

}
