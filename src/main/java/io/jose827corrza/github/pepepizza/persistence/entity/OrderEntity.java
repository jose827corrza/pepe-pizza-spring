package io.jose827corrza.github.pepepizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
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


    @OneToOne(fetch = FetchType.LAZY) // Does not load this relation until it is used
    @JoinColumn(
            name = "id_customer",
            referencedColumnName = "id_customer",
            updatable = false,
            insertable = false
    )
    @JsonIgnore // To avoid retrieve all the customer info, is not necessary and overload it
    private CustomerEntity customer;

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.EAGER // When we tried to fetch the OrderEntity automatically load this info
    ) // Attribute name
    private List<OrderItemEntity> items;
}
