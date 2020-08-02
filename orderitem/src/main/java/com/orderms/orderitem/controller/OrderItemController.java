package com.orderms.orderitem.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.orderms.orderitem.common.ApplicationExceptionHandler;
import com.orderms.orderitem.domain.ServiceResponse;
import com.orderms.orderitem.dto.OrderItemDto;
import com.orderms.orderitem.model.OrderItem;
import com.orderms.orderitem.service.IOrderItemService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/orderitem")
public class OrderItemController {

	@Autowired
	IOrderItemService orderItemService;

	@PostMapping(value = "/createOrderItem", produces = "application/json")
	@ApiOperation(value = "create order Item", response = ResponseEntity.class, produces = "application/json")

	public ResponseEntity<ArrayList<OrderItem>> createOrderItem(@Validated @RequestBody OrderItem orderItem) {
		OrderItem result = orderItemService.saveOrder(orderItem);
		return ResponseEntity.status(HttpStatus.CREATED).body(Lists.newArrayList(result));
	}

	@GetMapping(value = "/getOrderItemById/{id}", produces = "application/json")
	@ApiOperation(value = "Get order item by  ID", produces = "application/json")
	public ResponseEntity<Object> getOrderItem(@PathVariable(required = true) long id) {
		OrderItem orderItem = orderItemService.getOrderItemById(id);

		if (orderItem.getId() == 0) {
			throw new ApplicationExceptionHandler("No Records Found");
		}
		return ResponseEntity.ok(!StringUtils.isEmpty(orderItem) ? orderItem : new ArrayList<>());
	}

	@GetMapping(value = "/getOrderItemByOrderId/{id}", produces = "application/json")
	@ApiOperation(value = "Get order item by order ID", produces = "application/json")
	public @ResponseBody List<OrderItemDto> getOrderItemByOrderId(@PathVariable(required = true) long id) {
		List<OrderItemDto> orderItemList = orderItemService.getOrderItemByOrderId(id);

		if (orderItemList.isEmpty()) {
			throw new ApplicationExceptionHandler("No Records Found");
		}
		return orderItemList;
	}

	@ApiOperation(value = "get Order Item", notes = "get Order Item.")
	@RequestMapping(value = "/getAllOrderItem", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<ServiceResponse> getOrderItem() {
		List<OrderItem> orderItemList = orderItemService.getAllOrderItem();
		if (orderItemList.isEmpty()) {
			throw new ApplicationExceptionHandler("No Records Found");
		}
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setResObject(orderItemList);
		serviceResponse.setResponseMessage("find all Order Item");
		return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.OK);
	}
}
