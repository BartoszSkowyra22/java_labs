package com.pollub.lab.service.lab5;

import com.pollub.lab.model.lab5.VehicleType;
import com.pollub.lab.repository.lab5.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleType addVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public Optional<VehicleType> getVehicleType(Long id) {
        return vehicleTypeRepository.findById(id);
    }

    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    public VehicleType updateVehicleType(Long id, VehicleType updatedVehicleType) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow();
        vehicleType.setType(updatedVehicleType.getType());
        vehicleType.setDescription(updatedVehicleType.getDescription());
        vehicleType.setDailyRate(updatedVehicleType.getDailyRate());
        return vehicleTypeRepository.save(vehicleType);
    }

    public void deleteVehicleType(Long id) {
        vehicleTypeRepository.deleteById(id);
    }

    public void deactivateVehicleType(Long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow();
        vehicleType.setActive(false);
        vehicleTypeRepository.save(vehicleType);
    }
}

