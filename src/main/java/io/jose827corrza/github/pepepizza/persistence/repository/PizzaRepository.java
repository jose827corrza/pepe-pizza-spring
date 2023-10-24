package io.jose827corrza.github.pepepizza.persistence.repository;

import io.jose827corrza.github.pepepizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    /**
     * We use ListCrudRepository instead of CrudRepository because the first one return a List wich is more intuitive
     * for us, on the other hand, CrudRepository return an Iterable, which in my opinion makes it a little bit
     * harder to understand
     */
}
