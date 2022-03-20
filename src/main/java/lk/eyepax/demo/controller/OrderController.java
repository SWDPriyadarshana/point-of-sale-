package lk.eyepax.demo.controller;

import lk.eyepax.demo.dto.OrderDTO;
import lk.eyepax.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/{id}")
    public void saveOrder(@PathVariable("id") String orderId,@RequestBody OrderDTO orderDTO){
        orderService.saveOrder(orderId, orderDTO);
    }

    @GetMapping
    public List<OrderDTO> findAllOrders(){
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO findOrderById(@PathVariable("id") String orderId){
        return orderService.findOrder(orderId);
    }

}
