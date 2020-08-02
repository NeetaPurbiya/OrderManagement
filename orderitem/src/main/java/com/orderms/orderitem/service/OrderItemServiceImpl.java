package com.orderms.orderitem.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.orderms.orderitem.common.ApplicationExceptionHandler;
import com.orderms.orderitem.dto.OrderItemDto;
import com.orderms.orderitem.model.OrderItem;
import com.orderms.orderitem.repository.OrderItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrderItemServiceImpl implements IOrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItem saveOrder(OrderItem orderItem) {
		try {
			return orderItemRepository.save(orderItem);
		} catch (Exception e) {
			// return null;
			throw new ApplicationExceptionHandler("Order Item Saved Failed");
		}
	}

	@Override
	public List<OrderItem> getAllOrderItem() {
		return orderItemRepository.findAll();

	}

	@Override
	public OrderItem getOrderItemById(long id) {
		Optional<OrderItem> orderItem = orderItemRepository.findById(id);
		if (orderItem.isPresent()) {
			return orderItem.get();
		} else {
			log.error("Order Item Not Found.");
			throw new ApplicationExceptionHandler("Order Item Not Found");
		}
	}

	@Override
	public List<OrderItemDto> getOrderItemByOrderId(long id) {
		try {
			List<OrderItem> orderItem = orderItemRepository.findByOrderId(id);

			if (orderItem.isEmpty()) {
				log.error("Order Item Not Found.");
			}

			return getOrderItemList(orderItem);
		} catch (Exception e) {
			throw new ApplicationExceptionHandler("Order Item Not Found");
		}
	}

	private List<OrderItemDto> getOrderItemList(List<OrderItem> orderItem) {
		List<OrderItemDto> listOrderItemDto = new ArrayList<OrderItemDto>();
		for (OrderItem o : orderItem) {
			OrderItemDto OrderItemDto = new OrderItemDto();
			OrderItemDto.setId(o.getId());
			OrderItemDto.setProductCode(o.getProductCode());
			OrderItemDto.setProductName(o.getProductName());
			OrderItemDto.setQuantity(o.getQuantity());
			OrderItemDto.setOrderId(o.getOrderId());
			listOrderItemDto.add(OrderItemDto);
		}
		return listOrderItemDto;
	}

	private List<OrderItemDto> getOrderItemDTOList(List<Object[]> orderItem) {
		List<OrderItemDto> listOrderItemDto = new ArrayList<OrderItemDto>();

		for (Object[] o : orderItem) {
			listOrderItemDto.add(buildOrderItemDTO(o));
		}
		return listOrderItemDto;
	}

	private OrderItemDto buildOrderItemDTO(Object[] object) {
		OrderItemDto OrderItemDto = new OrderItemDto();
		OrderItemDto.setId(((BigInteger) object[0]).longValue());
		OrderItemDto.setProductCode(StringUtils.isEmpty(object[1]) ? null : (String) object[1]);
		OrderItemDto.setProductName(StringUtils.isEmpty(object[2]) ? null : (String) object[2]);
		OrderItemDto.setQuantity(
				StringUtils.isEmpty(object[3]) ? null : Long.valueOf(((BigInteger) object[3]).longValue()));
		OrderItemDto.setOrderId(((BigInteger) object[4]).longValue());
		return OrderItemDto;
	}

}
