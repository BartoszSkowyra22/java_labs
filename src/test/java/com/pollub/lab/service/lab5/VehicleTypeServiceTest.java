package com.pollub.lab.service.lab5;

import com.pollub.lab.model.lab5.VehicleType;
import com.pollub.lab.repository.lab5.VehicleTypeRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VehicleTypeServiceTest {

    private final VehicleTypeRepository vehicleTypeRepository = mock(VehicleTypeRepository.class);
    private final VehicleTypeService vehicleTypeService = new VehicleTypeService(vehicleTypeRepository);

    @Test
    void testAddVehicleType() {
        VehicleType vehicleType = new VehicleType(1L, "SUV", "Large off-road vehicle", 120.00, true);
        when(vehicleTypeRepository.save(vehicleType)).thenReturn(vehicleType);
        VehicleType result = vehicleTypeService.addVehicleType(vehicleType);
        assertThat(result).isEqualTo(vehicleType);
        verify(vehicleTypeRepository, times(1)).save(vehicleType);
    }

    @Test
    void testGetVehicleType() {
        VehicleType vehicleType = new VehicleType(1L, "SUV", "Large off-road vehicle", 120.00, true);
        when(vehicleTypeRepository.findById(1L)).thenReturn(Optional.of(vehicleType));
        Optional<VehicleType> result = vehicleTypeService.getVehicleType(1L);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(vehicleType);
        verify(vehicleTypeRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateVehicleType() {
        VehicleType existingVehicleType = new VehicleType(1L, "SUV", "Large off-road vehicle", 120.00, true);
        VehicleType updatedVehicleType = new VehicleType(1L, "Truck", "Heavy-duty vehicle", 150.00, true);
        when(vehicleTypeRepository.findById(1L)).thenReturn(Optional.of(existingVehicleType));
        when(vehicleTypeRepository.save(existingVehicleType)).thenReturn(updatedVehicleType);
        VehicleType result = vehicleTypeService.updateVehicleType(1L, updatedVehicleType);
        assertThat(result.getType()).isEqualTo("Truck");
        assertThat(result.getDailyRate()).isEqualTo(150.00);
        verify(vehicleTypeRepository, times(1)).findById(1L);
        verify(vehicleTypeRepository, times(1)).save(existingVehicleType);
    }

    @Test
    void testDeleteVehicleType() {
        doNothing().when(vehicleTypeRepository).deleteById(1L);
        vehicleTypeService.deleteVehicleType(1L);
        verify(vehicleTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeactivateVehicleType() {
        VehicleType vehicleType = new VehicleType(1L, "SUV", "Large off-road vehicle", 120.00, true);
        when(vehicleTypeRepository.findById(1L)).thenReturn(Optional.of(vehicleType));
        when(vehicleTypeRepository.save(vehicleType)).thenReturn(vehicleType);
        vehicleTypeService.deactivateVehicleType(1L);
        assertThat(vehicleType.getActive()).isFalse();
        verify(vehicleTypeRepository, times(1)).findById(1L);
        verify(vehicleTypeRepository, times(1)).save(vehicleType);
    }
}
