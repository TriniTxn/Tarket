package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.ProductRequestDTO;
import br.com.devannis.webmarket.model.dto.ProductResponseDTO;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@RequestBody @Valid ProductRequestDTO product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO getProduct(@PathVariable Long productId) {
        return productService.searchByProductId(productId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAllProducts() {
        return productService.searchAllProducts();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excludeProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
