package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.InsufficientStockException;
import br.com.devannis.webmarket.model.entity.Order;
import br.com.devannis.webmarket.model.entity.OrderItems;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.repository.OrderItemsRepository;
import br.com.devannis.webmarket.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderItems createOrderItem(Order order, Product product, int quantity) {
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException("We do not have the amount chosen: (Actual stock of this product: " + product.getStockQuantity() + ")");
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);

        OrderItems orderItem = new OrderItems();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPriceAtTimeOfOrder(product.getProductPrice());

        return orderItemsRepository.save(orderItem);
    }

    public List<OrderItems> getOrderItemsByOrderId(Long orderId) {
        return orderItemsRepository.findByOrderOrderId(orderId);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemsRepository.deleteById(orderItemId);
    }
}
