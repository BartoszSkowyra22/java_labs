package com.pollub.lab.repository.lab5;

import com.pollub.lab.model.lab5.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

    VehicleType findByType(String type);
}

