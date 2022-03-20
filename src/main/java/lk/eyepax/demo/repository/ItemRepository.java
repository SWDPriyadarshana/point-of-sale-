package lk.eyepax.demo.repository;

import lk.eyepax.demo.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
