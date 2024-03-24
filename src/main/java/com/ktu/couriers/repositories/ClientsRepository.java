package com.ktu.couriers.repositories;

import com.ktu.couriers.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
}

