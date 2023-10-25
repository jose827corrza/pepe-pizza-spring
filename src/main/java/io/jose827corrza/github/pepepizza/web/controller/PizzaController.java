package io.jose827corrza.github.pepepizza.web.controller;

import io.jose827corrza.github.pepepizza.persistence.entity.PizzaEntity;
import io.jose827corrza.github.pepepizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int elements) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int idPizza) {
        return ResponseEntity.ok(this.pizzaService.getById(idPizza));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza) {
        if(pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza) {
        if(!this.pizzaService.exists(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // Using query methods
    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int elements,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getAvailableAndName(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaService.getAvailableAndName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getAvailableAndWithIngredient(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getPizzasWithIngredient(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getAvailableAndWithOutIngredient(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.getPizzasWithOutIngredient(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable double price) {
        return ResponseEntity.ok(this.pizzaService.getTop3CheapestAvailablePizzas(price));
    }
}
