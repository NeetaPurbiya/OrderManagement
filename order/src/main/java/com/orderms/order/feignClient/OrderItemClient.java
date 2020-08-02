package com.orderms.order.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orderms.order.model.OrderItemResponse;

@FeignClient(value = "orderItem", url = "http://localhost:8082/api/orderitem")
public interface OrderItemClient {

	@GetMapping(value = "/getOrderItemByOrderId/{id}", produces = "application/json")
	public List<OrderItemResponse> getOrderItem(@PathVariable(required = true) long id);

	@RequestMapping(method = RequestMethod.POST, value = "/createOrderItem")
	public void createOrderItem(@RequestBody OrderItemResponse orderItem);

}
