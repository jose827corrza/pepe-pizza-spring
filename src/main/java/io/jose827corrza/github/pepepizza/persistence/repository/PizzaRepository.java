package io.jose827corrza.github.pepepizza.persistence.repository;

import io.jose827corrza.github.pepepizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    /**
     * We use ListCrudRepository instead of CrudRepository because the first one return a List wich is more intuitive
     * for us, on the other hand, CrudRepository return an Iterable, which in my opinion makes it a little bit
     * harder to understand
     */

    // Query methods
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String ingredient);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredient);

    int countByVeganTrue();

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
}
