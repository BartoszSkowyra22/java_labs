package com.pollub.lab.service.lab5;

import com.pollub.lab.model.lab5.Customer;
import com.pollub.lab.model.lab5.Rental;
import com.pollub.lab.model.lab5.VehicleType;
import com.pollub.lab.repository.lab5.RentalRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RentalServiceTest {

    private final RentalRepository rentalRepository = mock(RentalRepository.class);
    private final RentalService rentalService = new RentalService(rentalRepository);

    @Test
    void testAddRental() {
        Rental rental = new Rental(1L,new VehicleType(), new Customer(), LocalDate.now(), LocalDate.now().plusDays(5),   true);
        when(rentalRepository.save(rental)).thenReturn(rental);

        Rental result = rentalService.addRental(rental);

        assertThat(result).isEqualTo(rental);
        verify(rentalRepository, times(1)).save(rental);
    }

    @Test
    void testGetRental() {
        Rental rental = new Rental(1L,new VehicleType(), new Customer(), LocalDate.now(), LocalDate.now().plusDays(5),   true);
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        Optional<Rental> result = rentalService.getRental(1L);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(rental);
        verify(rentalRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateRental() {
        Rental existingRental = new Rental(1L,new VehicleType(), new Customer(), LocalDate.now(), LocalDate.now().plusDays(5),   true);
        Rental updatedRental = new Rental(1L,new VehicleType(), new Customer(), LocalDate.now(), LocalDate.now().plusDays(10),   true);

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(existingRental));
        when(rentalRepository.save(existingRental)).thenReturn(updatedRental);

        Rental result = rentalService.updateRental(1L, updatedRental);

        assertThat(result.getReturnDate()).isEqualTo(LocalDate.now().plusDays(10));
        verify(rentalRepository, times(1)).findById(1L);
        verify(rentalRepository, times(1)).save(existingRental);
    }

    @Test
    void testDeleteRental() {
        doNothing().when(rentalRepository).deleteById(1L);

        rentalService.deleteRental(1L);

        verify(rentalRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeactivateRental() {
        Rental rental = new Rental(1L,new VehicleType(), new Customer(), LocalDate.now(), LocalDate.now().plusDays(5),   true);

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));
        when(rentalRepository.save(rental)).thenReturn(rental);

        rentalService.deactivateRental(1L);

        assertThat(rental.getActive()).isFalse();
        verify(rentalRepository, times(1)).findById(1L);
        verify(rentalRepository, times(1)).save(rental);
    }


}
