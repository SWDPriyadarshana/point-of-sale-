package lk.eyepax.demo.service;



import lk.eyepax.demo.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void saveOrder(String orderId, OrderDTO orderDTO);

    OrderDTO findOrder(String orderId);

    List<OrderDTO> findAllOrders();


}
