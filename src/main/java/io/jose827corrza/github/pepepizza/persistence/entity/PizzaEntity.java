package io.jose827corrza.github.pepepizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "pizzas")
@EntityListeners(AuditingEntityListener.class)// Enable auditing for the entity
@Getter
@Setter
@NoArgsConstructor // Avoid using @Data, bad practice to use in entities
public class PizzaEntity extends AuditableEntity{ // extends ang gets the Column of that class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Here we could select uuid instead of a single integer
    @Column(nullable = false, name = "id_pizza")
    private Integer idPizza;

    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @Column(nullable = false, length = 175)
    private String description;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;

    @Column(columnDefinition = "BOOLEAN")// TINYINT for MySQL
    private Boolean vegetarian;

    @Column(columnDefinition = "BOOLEAN")// TINYINT for MySQL
    private Boolean vegan;

    @Column(columnDefinition = "BOOLEAN", nullable = false)// TINYINT for MySQL
    private Boolean available;

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", available=" + available +
                '}';
    }


}
