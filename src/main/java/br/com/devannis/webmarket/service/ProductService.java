package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.ProductNotFoundException;
import br.com.devannis.webmarket.model.dto.ProductRequestDTO;
import br.com.devannis.webmarket.model.dto.ProductResponseDTO;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();

        BeanUtils.copyProperties(productRequestDTO, product);

        Product savedProduct = productRepository.save(product);

        return new ProductResponseDTO(savedProduct);
    }

    public ProductResponseDTO searchByProductId(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return new ProductResponseDTO(product);
    }

    public List<ProductResponseDTO> searchAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public ProductResponseDTO updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Product updatedProduct = productRepository.save(product);
        return new ProductResponseDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        productRepository.delete(product);
    }
}
