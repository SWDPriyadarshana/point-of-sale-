package lk.eyepax.demo.repository;

import lk.eyepax.demo.entity.OrderDetail;
import lk.eyepax.demo.entity.OrderDetail_PK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail_PK> {
}
