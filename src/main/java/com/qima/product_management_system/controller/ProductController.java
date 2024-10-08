package com.qima.product_management_system.controller;

import com.qima.product_management_system.dto.ProductDTO;
import com.qima.product_management_system.dto.CategoryDTO;
import com.qima.product_management_system.service.CategoryService;
import com.qima.product_management_system.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String listProductsForHTML(
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order,
            Model model) {

        List<ProductDTO> products = productService.getAllProductsSorted(sort, order);
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("order", order);

        return "product-list";
    }

    @PostMapping
    public String addProductForHTML(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CategoryDTO> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("products", productService.getAllProducts());
            return "product-list";
        }
        productService.addProduct(productDTO);
        return "redirect:/products/list";
    }

    @GetMapping("/edit/{id}")
    public String editProductForHTML(@PathVariable Long id, Model model) {
        ProductDTO product = productService.getProductById(id);
        List<ProductDTO> products = productService.getAllProducts();
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "product-list";
    }

    @PostMapping("/edit/{id}")
    public String updateProductForHTML(@PathVariable Long id, @Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CategoryDTO> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("products", productService.getAllProducts());
            return "product-list";
        }
        productService.updateProduct(id, productDTO);
        return "redirect:/products/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductForHTML(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/list";
    }
}
