package io.jose827corrza.github.pepepizza.persistence.repository;

import io.jose827corrza.github.pepepizza.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPageSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {

    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
