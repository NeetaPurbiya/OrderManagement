package com.orderms.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderms.order.common.ApplicationExceptionHandler;
import com.orderms.order.feignClient.OrderItemClient;
import com.orderms.order.model.Order;
import com.orderms.order.model.OrderItemResponse;
import com.orderms.order.model.OrderResponseMap;
import com.orderms.order.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository OrderRepository;

	@Autowired
	OrderItemClient orderItemClient;

	@Override
	public Order saveOrder(OrderResponseMap orderRequest) {
		try {
			Order order = new Order();
			BeanUtils.copyProperties(orderRequest, order);
			Order orderDetail = OrderRepository.save(order);
			List<OrderItemResponse> orderItemResponse = orderRequest.getOrderItemResponse();
			for (OrderItemResponse itemRequest : orderItemResponse) {
				itemRequest.setOrderId(orderDetail.getId());
				orderItemClient.createOrderItem(itemRequest);
			}

			return orderDetail;
		} catch (Exception e) {
			throw new ApplicationExceptionHandler("Order Saved Failed");
		}
	}

	@Override
	public List<OrderResponseMap> getAllOrder() {
		List<Order> orders = (List<Order>) OrderRepository.findAll();
		List<OrderResponseMap> orderRequest = new ArrayList<OrderResponseMap>();
		for (Order order : orders) {
			List<OrderItemResponse> orderItem = orderItemClient.getOrderItem(order.getId());
			OrderResponseMap orderDataRequest = new OrderResponseMap();
			orderDataRequest.setCustomerName(order.getCustomerName());
			orderDataRequest.setOrderDate(order.getOrderDate());
			orderDataRequest.setOrderId(order.getId());
			orderDataRequest.setOrderItemResponse(orderItem);
			orderDataRequest.setShippingAddress(order.getShippingAddress());
			orderDataRequest.setTotal(order.getTotal());
			orderRequest.add(orderDataRequest);
		}

		return orderRequest;

	}

	@Override
	public OrderResponseMap getOrderById(long id) {
		Optional<Order> Order = OrderRepository.findById(id);
		if (Order.isPresent()) {
			Order order = Order.get();
			List<OrderItemResponse> orderItem = orderItemClient.getOrderItem(id);
			OrderResponseMap orderResponseMap = new OrderResponseMap();
			orderResponseMap.setCustomerName(order.getCustomerName());
			orderResponseMap.setOrderDate(order.getOrderDate());
			orderResponseMap.setOrderId(order.getId());
			orderResponseMap.setOrderItemResponse(orderItem);
			orderResponseMap.setShippingAddress(order.getShippingAddress());
			orderResponseMap.setTotal(order.getTotal());
			return orderResponseMap;
		} else {
			log.error("Order Not Found.");
			throw new ApplicationExceptionHandler("Order Not Found");
		}
	}

}
