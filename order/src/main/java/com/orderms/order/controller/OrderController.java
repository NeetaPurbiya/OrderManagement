package com.orderms.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.orderms.order.domain.ServiceResponse;
import com.orderms.order.model.Order;
import com.orderms.order.model.OrderResponseMap;
import com.orderms.order.service.IOrderService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

	@Autowired
	IOrderService OrderService;

	@PostMapping(value = "/createOrder", produces = "application/json")
	@ApiOperation(value = "create order Item", response = ResponseEntity.class, produces = "application/json")

	public ResponseEntity<ArrayList<Order>> createOrder(@Validated @RequestBody OrderResponseMap orderRequest) {
		log.info("create Order ");
		Order result = OrderService.saveOrder(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(Lists.newArrayList(result));
	}

	@GetMapping(value = "/getOrderById/{id}", produces = "application/json")
	@ApiOperation(value = "Get order item by  ID", produces = "application/json")

	public ResponseEntity getOrder(@PathVariable(required = true) long id) {
		log.info("get Order ");
		OrderResponseMap order = OrderService.getOrderById(id);
		return ResponseEntity.ok(!StringUtils.isEmpty(order) ? order : new ArrayList<>());
	}

	@ApiOperation(value = "get Order Item", notes = "get Order Item.")
	@GetMapping(value = "/getAllOrder", produces = "application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> getOrder() {
		log.info("get all Order ");
		List<OrderResponseMap> OrderList = OrderService.getAllOrder();

		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResObject(OrderList);
		serviceResponse.setResponseMessage("find all Orders");

		return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.OK);
	}

}
