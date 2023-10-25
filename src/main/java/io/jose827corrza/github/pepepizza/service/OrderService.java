package io.jose827corrza.github.pepepizza.service;

import io.jose827corrza.github.pepepizza.persistence.entity.OrderEntity;
import io.jose827corrza.github.pepepizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    public static final String DELIVERY = "D";
    public static final String CARRYOUT = "C";
    public static final String ON_SITE = "O";
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        return this.orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> outsideMethods = Arrays.asList(DELIVERY, CARRYOUT); // Defined above
        return this.orderRepository.findAllByMethodIn(outsideMethods);
    }

    public List<OrderEntity> getOrdersByCustomerId(String idCustomer) {
        return this.orderRepository.getOrdersByCustomerId(idCustomer);
    }
}
