package com.ktu.couriers.repositories;

import com.ktu.couriers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // findAllWithClientsWhereRoleIsClient
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.client WHERE u.role = 'CLIENT'")
//    List<User> findAllWithClientsWhereRoleIsClient();
//
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.client WHERE u.id = :id")
//    Optional<User> findByIdWithClient(Long id);
}
