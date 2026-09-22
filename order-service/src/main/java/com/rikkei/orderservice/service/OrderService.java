package com.rikkei.orderservice.service;

import com.rikkei.orderservice.entity.Order;
import com.rikkei.orderservice.exception.ResourceNotFoundException;
import com.rikkei.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * Tìm kiếm đơn hàng theo ID.
     * Nếu không tìm thấy, ném ngoại lệ ResourceNotFoundException
     * để GlobalExceptionHandler bắt và chuyển thành ApiResponseError chuẩn 404.
     */
    public Order getOrderById(Long id) {
        log.info("[ORDER-SERVICE] Tìm kiếm đơn hàng ID: {} trong PostgreSQL", id);
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng có ID: " + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
