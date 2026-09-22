package com.rikkei.orderservice.controller;

import com.rikkei.orderservice.entity.Order;
import com.rikkei.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * API lấy chi tiết đơn hàng:
     * GET /api/v1/orders/{id}
     * - Nếu tồn tại: Trả về HTTP 200 kèm Order JSON.
     * - Nếu KHÔNG tồn tại: Ném ResourceNotFoundException -> GlobalExceptionHandler
     *   bắt lỗi và trả về HTTP 404 kèm ApiResponseError JSON.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        log.info("Nhận yêu cầu GET /api/v1/orders/{}", id);
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order created = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
