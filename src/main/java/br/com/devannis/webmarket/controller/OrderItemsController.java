package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.OrderItemsRequestDTO;
import br.com.devannis.webmarket.model.dto.OrderItemsResponseDTO;
import br.com.devannis.webmarket.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;

    @PostMapping("/order/{orderId}")
    public OrderItemsResponseDTO createOrderItem(@PathVariable Long orderId, @RequestBody OrderItemsRequestDTO orderItemsRequestDTO) {
        return orderItemsService.createOrderItem(orderId, orderItemsRequestDTO);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItemsResponseDTO> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return orderItemsService.getOrderItemsByOrderId(orderId);
    }

    public void deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemsService.deleteOrderItem(orderItemId);
    }
}
