package com.qima.product_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be a positive value")
    private Double price;

    @NotNull(message = "Category is required.")
    private CategoryDTO category;

    private boolean available;

    public ProductDTO() {
    }

}

