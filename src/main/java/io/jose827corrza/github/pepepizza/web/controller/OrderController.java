package io.jose827corrza.github.pepepizza.web.controller;

import io.jose827corrza.github.pepepizza.persistence.entity.OrderEntity;
import io.jose827corrza.github.pepepizza.persistence.projection.OrderSummary;
import io.jose827corrza.github.pepepizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside-orders")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id_customer}")
    public ResponseEntity<List<OrderEntity>> getOrdersByCustomerId(@PathVariable String id_customer) {
        return ResponseEntity.ok(this.orderService.getOrdersByCustomerId(id_customer));
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int orderId) {
        return ResponseEntity.ok(this.orderService.getOrderSummary(orderId));
    }
}
