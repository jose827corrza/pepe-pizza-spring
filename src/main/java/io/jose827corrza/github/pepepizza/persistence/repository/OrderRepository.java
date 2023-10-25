package io.jose827corrza.github.pepepizza.persistence.repository;

import io.jose827corrza.github.pepepizza.persistence.entity.OrderEntity;
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
}
