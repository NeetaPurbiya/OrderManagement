package com.orderms.order.service;

import java.util.List;

import com.orderms.order.model.Order;
import com.orderms.order.model.OrderResponseMap;

public interface IOrderService {

	public Order saveOrder(OrderResponseMap orderRequest);

	public List<OrderResponseMap> getAllOrder();

	public OrderResponseMap getOrderById(long id);

}
