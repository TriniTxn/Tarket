package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.InsufficientStockException;
import br.com.devannis.webmarket.exception.OrderNotFoundException;
import br.com.devannis.webmarket.exception.ProductNotFoundException;
import br.com.devannis.webmarket.mapper.OrderItemsMapper;
import br.com.devannis.webmarket.mapper.OrderMapper;
import br.com.devannis.webmarket.model.dto.OrderItemsRequestDTO;
import br.com.devannis.webmarket.model.dto.OrderItemsResponseDTO;
import br.com.devannis.webmarket.model.entity.Order;
import br.com.devannis.webmarket.model.entity.OrderItems;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.repository.OrderItemsRepository;
import br.com.devannis.webmarket.repository.OrderRepository;
import br.com.devannis.webmarket.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Transactional
    public OrderItemsResponseDTO createOrderItem(Long orderId, OrderItemsRequestDTO orderItemsRequestDTO) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("O pedido não foi encontrado!"));
        Product product = productRepository.findById(orderItemsRequestDTO.productId()).orElseThrow(() -> new ProductNotFoundException("O Produto não foi encontrado!"));

        if (product.getStockQuantity() < orderItemsRequestDTO.quantity()) {
            throw new InsufficientStockException("We do not have the amount chosen: (Actual stock of this product: " + product.getStockQuantity() + ")");
        }

        product.setStockQuantity(product.getStockQuantity() - orderItemsRequestDTO.quantity());
        productRepository.save(product);

        OrderItems orderItem = new OrderItems();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemsRequestDTO.quantity());
        orderItem.setPriceAtTimeOfOrder(product.getProductPrice());

        OrderItems savedOrderItems = orderItemsRepository.save(orderItem);

        return OrderItemsMapper.toDto(savedOrderItems);
    }

    public List<OrderItemsResponseDTO> getOrderItemsByOrderId(Long orderId) {
        return orderItemsRepository.findByOrderOrderId(orderId)
                .stream()
                .map(OrderItemsMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemsRepository.deleteById(orderItemId);
    }
}
