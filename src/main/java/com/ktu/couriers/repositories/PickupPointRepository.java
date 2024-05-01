package com.ktu.couriers.repositories;

import com.ktu.couriers.models.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, Long>, JpaSpecificationExecutor<PickupPoint> {
}
