package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.OrderRequestDTO;
import br.com.devannis.webmarket.model.dto.OrderResponseDTO;
import br.com.devannis.webmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/client/{clientId}")
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO, @PathVariable Long clientId) {
        return orderService.createOrder(orderRequestDTO, clientId);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/client/{clientId}")
    public List<OrderResponseDTO> getOrdersByClientId(@PathVariable Long clientId) {
        return orderService.getOrdersByClient(clientId);
    }
}
