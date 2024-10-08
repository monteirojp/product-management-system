package com.qima.product_management_system.service;

import com.qima.product_management_system.dto.CategoryDTO;
import com.qima.product_management_system.dto.ProductDTO;
import com.qima.product_management_system.model.Product;
import com.qima.product_management_system.model.Category;
import com.qima.product_management_system.repository.ProductRepository;
import com.qima.product_management_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Convert DTO to Entity
    private Product convertToEntity(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found with id " + productDTO.getCategory().getId()));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        product.setAvailable(productDTO.isAvailable());
        return product;
    }

    // Convert Entity to DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(new CategoryDTO(product.getCategory().getId(), product.getCategory().getName()));
        productDTO.setAvailable(product.isAvailable());
        return productDTO;
    }

    // Add Product using ProductDTO
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public List<ProductDTO> getAllProductsSorted(String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Product> products = productRepository.findAll(sort);
        return products.stream()
                .map(product -> convertToDTO(product))
                .collect(Collectors.toList());
    }

    // Delete Product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Get All Products (paginated)
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::convertToDTO);
    }

    // Get All Products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    // Update Product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDTO.getName());
                    product.setDescription(productDTO.getDescription());
                    product.setPrice(productDTO.getPrice());
                    product.setAvailable(productDTO.isAvailable());
                    Category category = categoryRepository.findById(productDTO.getCategory().getId())
                            .orElseThrow(() -> new RuntimeException("Category not found with id " + productDTO.getCategory().getId()));
                    product.setCategory(category);
                    return convertToDTO(productRepository.save(product));
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // Get Product by ID
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        return convertToDTO(product);
    }
}
