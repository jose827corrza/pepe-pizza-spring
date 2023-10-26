package io.jose827corrza.github.pepepizza.service;

import io.jose827corrza.github.pepepizza.persistence.entity.PizzaEntity;
import io.jose827corrza.github.pepepizza.persistence.repository.PizzaPageSortRepository;
import io.jose827corrza.github.pepepizza.persistence.repository.PizzaRepository;
import io.jose827corrza.github.pepepizza.service.dto.UpdatePizzaPriceDto;
import io.jose827corrza.github.pepepizza.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPageSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPageSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageable);
    }

    public PizzaEntity getById(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza); // True if  exists
    }

    //  Using query method
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageable);
    }

    public PizzaEntity getAvailableAndName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("The pizza does not exist"));
    }

    public List<PizzaEntity> getPizzasWithIngredient(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getPizzasWithOutIngredient(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getTop3CheapestAvailablePizzas(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    @Transactional(noRollbackFor = EmailApiException.class) // Will keep the changes even if an Exception was thrown
    public void updatePizzaPrice(UpdatePizzaPriceDto dto) {
        /*
         * is Very important the knowledge of ACID
         * A Atomicity
         * C Consistency
         * I Isolation
         * D Durability
         */
        this.pizzaRepository.updatePizzaPrice(dto);
        this.sendEmailNotification(); // Without specifying to ignore this, would make a rollback to all the transactions made in this function
    }

    private void sendEmailNotification() {
        throw new EmailApiException();
    }
}
