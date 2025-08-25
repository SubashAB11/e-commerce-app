package com.abs.e_commerce.kafka.order;

import java.math.BigDecimal;

public record Product(
                Long id,
                String name,
                String description,
                double quantity,
                BigDecimal price) {

}
