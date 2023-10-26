package io.jose827corrza.github.pepepizza.persistence.repository;

import io.jose827corrza.github.pepepizza.persistence.entity.OrderEntity;
import io.jose827corrza.github.pepepizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    // Query methods
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    /**
     * Create a Query that returns the orders that a specific customer have done
     */
    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true) // Native Query confirm its about SQL.
    List<OrderEntity> getOrdersByCustomerId(@Param("id")String idCustomer);

    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate,  " +
            "   po.total AS orderTotal, STRING_AGG(PI.name, ', ') AS pizzaNames " +
            "FROM pizza_order po " +
            "   INNER JOIN customers cu ON po.id_customer = cu.id_customer " +
            "   INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "   INNER JOIN pizzas PI ON oi.id_pizza = PI.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.id_customer, po.date, po.total",nativeQuery = true)
    OrderSummary findOrderSummary(@Param("orderId") int orderId);
}
