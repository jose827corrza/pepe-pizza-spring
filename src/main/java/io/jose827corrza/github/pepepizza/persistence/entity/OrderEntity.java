package io.jose827corrza.github.pepepizza.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_order")
    private Integer idOrder;

    @Column(nullable = false, length = 15, name = "id_customer")
    private String idCustomer;

    @Column(columnDefinition = "TIMESTAMP") // DATETIME for MySQL
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)", name = "total")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(length = 200, name = "additional_notes")
    private String additionalNotes;

    // Relations


    @OneToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", updatable = false, insertable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order") // Attribute name
    private List<OrderItemEntity> items;
}
