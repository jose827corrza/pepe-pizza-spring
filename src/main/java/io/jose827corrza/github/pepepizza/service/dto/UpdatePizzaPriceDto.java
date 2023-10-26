package io.jose827corrza.github.pepepizza.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double pizzaPrice;
}
