package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.ClientNotFoundException;
import br.com.devannis.webmarket.exception.OrderNotFoundException;
import br.com.devannis.webmarket.exception.ProductNotFoundException;
import br.com.devannis.webmarket.mapper.OrderMapper;
import br.com.devannis.webmarket.model.dto.OrderRequestDTO;
import br.com.devannis.webmarket.model.dto.OrderResponseDTO;
import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.model.entity.Order;
import br.com.devannis.webmarket.model.entity.OrderItems;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.repository.ClientRepository;
import br.com.devannis.webmarket.repository.OrderRepository;
import br.com.devannis.webmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO, Long clientId) {
        Client client = clientRepository
                .findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Order order = new Order();
        order.setClient(client);
        order.setStatus("PENDING");

        List<OrderItems> orderItems = orderRequestDTO
                .items()
                .stream()
                .map(itemDTO -> {
                    Product product = productRepository
                            .findById(itemDTO.productId()).orElseThrow(() -> new ProductNotFoundException("Product not found"));

                    if (product.getStockQuantity() < itemDTO.quantity()){
                        throw new RuntimeException("We do not have the amount chosen: (Actual stock of this product: " + product.getStockQuantity() + ")");
                    }
                    product.setStockQuantity(product.getStockQuantity() - itemDTO.quantity());
                    productRepository.save(product);

                    return new OrderItems(order, product, itemDTO.quantity());
                }).collect(Collectors.toList());

                order.setOrderItems(orderItems);
                order.setTotalValue(order.calculateTotalValue());
                orderRepository.save(order);

                return OrderMapper.toOrderResponseDTO(order);
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));

        return OrderMapper.toOrderResponseDTO(order);
    }

    public List<OrderResponseDTO> getOrdersByClient(Long clientId) {
        List<Order> orders = orderRepository.findByClientClientId(clientId);

        return orders.stream().map(OrderMapper::toOrderResponseDTO).collect(Collectors.toList());
    }
}
