package com.pollub.lab.controller.lab5;


import com.pollub.lab.model.lab5.Rental;
import com.pollub.lab.service.lab5.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<?> addRental(@RequestBody Rental rental) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.addRental(rental));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<Rental> getRental(@PathVariable Long id) {
        return rentalService.getRental(id);
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable Long id, @RequestBody Rental rental) {
        return rentalService.updateRental(id, rental);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
    }

    @PatchMapping("/{id}/deactivate")
    public void deactivateRental(@PathVariable Long id) {
        rentalService.deactivateRental(id);
    }
}
