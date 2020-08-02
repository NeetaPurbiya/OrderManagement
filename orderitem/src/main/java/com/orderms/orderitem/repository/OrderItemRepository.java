package com.orderms.orderitem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderms.orderitem.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	 public List<OrderItem> findByOrderId(long id);

}
