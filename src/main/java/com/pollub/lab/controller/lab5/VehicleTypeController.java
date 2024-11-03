package com.pollub.lab.controller.lab5;


import com.pollub.lab.model.lab5.VehicleType;
import com.pollub.lab.service.lab5.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-types")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @PostMapping
    public ResponseEntity<?> addVehicleType(@RequestBody VehicleType vehicleType) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleTypeService.addVehicleType(vehicleType));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<VehicleType> getVehicleType(@PathVariable Long id) {
        return vehicleTypeService.getVehicleType(id);
    }

    @GetMapping
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeService.getAllVehicleTypes();
    }

    @PutMapping("/{id}")
    public VehicleType updateVehicleType(@PathVariable Long id, @RequestBody VehicleType vehicleType) {
        return vehicleTypeService.updateVehicleType(id, vehicleType);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicleType(@PathVariable Long id) {
        vehicleTypeService.deleteVehicleType(id);
    }

    @PatchMapping("/{id}/deactivate")
    public void deactivateVehicleType(@PathVariable Long id) {
        vehicleTypeService.deactivateVehicleType(id);
    }
}
