package lk.eyepax.demo.repository;

import lk.eyepax.demo.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {

//    @Query("SELECT c FROM Customer c WHERE c.address LIKE ?1%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(String address);
//    @Query("SELECT c FROM Customer c WHERE c.address LIKE :address%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(@Param("address") String address);

//    @Query("SELECT c FROM Customer c WHERE c.address LIKE ?#{[1]}%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(String name,String address);

//    @Query("SELECT c FROM Customer c WHERE c.address LIKE :#{#address}%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(@Param("address") String address);

    List<Customer> mataOniAddressMeaAkurenPatanGanna(String address);


}
