package com.orderms.orderitem.service;

import java.util.List;

import com.orderms.orderitem.dto.OrderItemDto;
import com.orderms.orderitem.model.OrderItem;

public interface IOrderItemService {

	public OrderItem saveOrder(OrderItem orderItem);

	public List<OrderItem> getAllOrderItem();

	public OrderItem getOrderItemById(long id);

	public List<OrderItemDto> getOrderItemByOrderId(long id);

}
