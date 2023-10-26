package io.jose827corrza.github.pepepizza.persistence.projection;


import java.time.LocalDateTime;

public interface OrderSummary {
    /*
    Used to retrieve specific data that comes from different tables without the need of bring all the row info of each table
     */
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
