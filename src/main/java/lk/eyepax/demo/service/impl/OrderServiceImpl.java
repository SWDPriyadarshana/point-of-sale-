package lk.eyepax.demo.service.impl;

import lk.eyepax.demo.dto.CustomerDTO;
import lk.eyepax.demo.dto.OrderDTO;
import lk.eyepax.demo.dto.OrderDetailDTO;
import lk.eyepax.demo.entity.Customer;
import lk.eyepax.demo.entity.Item;
import lk.eyepax.demo.entity.Order;
import lk.eyepax.demo.entity.OrderDetail;
import lk.eyepax.demo.repository.ItemRepository;
import lk.eyepax.demo.repository.OrderRepository;
import lk.eyepax.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void saveOrder(String orderId, OrderDTO orderDTO) {

        if (!orderId.equals(orderDTO.getId())){
            throw new RuntimeException("Order Ids are mismatched");
        }

        CustomerDTO customerDTO = orderDTO.getCustomer();
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
        Order order = new Order(orderDTO.getId(), orderDTO.getDate(), customer);

        List<OrderDetailDTO> orderDetailDTOLists = orderDTO.getOrderDetails();
        ArrayList<OrderDetail> orderDetailsList = new ArrayList<>();

        for (OrderDetailDTO orderDetailDTOList : orderDetailDTOLists) {
            if (!orderDetailDTOList.getOrderId().equals(order.getId())){
                throw new RuntimeException("Order Detail's order id and order id are mismatched");
            }

            Item item = itemRepository.findById(orderDetailDTOList.getItemCode()).get();
            int currentQty = item.getQtyOnHand();
            currentQty -= orderDetailDTOList.getQtyOnHand();
            item.setQtyOnHand(currentQty);
//            itemRepository.save(item);

            OrderDetail orderDetail = new OrderDetail(orderDetailDTOList.getOrderId(), orderDetailDTOList.getItemCode(), orderDetailDTOList.getQtyOnHand(), orderDetailDTOList.getUnitPrice());
            orderDetailsList.add(orderDetail);

        }



        order.setOrderDetailList(orderDetailsList);

        orderRepository.save(order);
    }

    @Override
    public OrderDTO findOrder(String orderId) {

        Order order = orderRepository.findById(orderId).get();
        Customer customer = order.getCustomer();
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
       ArrayList<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetailDTOList.add(new OrderDetailDTO(orderDetail.getOrder().getId(), orderDetail.getItem().getCode(),orderDetail.getUnitPrice(), orderDetail.getQty()));
        }


        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getDate(), customerDTO);
        orderDTO.setOrderDetails(orderDetailDTOList);

        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAllOrders() {

        List<Order> orderList = orderRepository.findAll();
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();

        for (Order order : orderList) {

            Customer customer = order.getCustomer();
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());

            List<OrderDetail> orderDetailList = order.getOrderDetailList();
            ArrayList<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

            for (OrderDetail orderDetail : orderDetailList) {
                orderDetailDTOList.add(new OrderDetailDTO(orderDetail.getOrder().getId(), orderDetail.getItem().getCode(),orderDetail.getUnitPrice(), orderDetail.getQty()));
            }


            OrderDTO orderDTO = new OrderDTO(order.getId(), order.getDate(), customerDTO);
            orderDTO.setOrderDetails(orderDetailDTOList);

            orderDTOList.add(orderDTO);

        }


        return orderDTOList;

    }
}
