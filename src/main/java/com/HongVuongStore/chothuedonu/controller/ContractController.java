package com.HongVuongStore.chothuedonu.controller;

import com.HongVuongStore.chothuedonu.entity.Contract;
import com.HongVuongStore.chothuedonu.request.ContractRequest;
import com.HongVuongStore.chothuedonu.response.ContractDetailResponse;
import com.HongVuongStore.chothuedonu.service.contract.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/contracts")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @PostMapping("/save")
    public ResponseEntity<?> saveContract(@RequestBody ContractRequest contractRequest) {
        String message = contractService.saveContract(contractRequest.getContract(), contractRequest.getProductIds());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContract(@PathVariable Long id) {
        String message = contractService.updateContractStatus(id, 0);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findContractById(@PathVariable Long id) {
        ContractDetailResponse contract = contractService.findContractById(id);
        if (contract != null) {
            return new ResponseEntity<>(contract, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllContracts() {
        Set<Contract> contracts = contractService.findAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/revenue/weekly")
    public ResponseEntity<Double> getWeeklyRevenue() {
        double weeklyRevenue = contractService.calculateWeeklyRevenue();
        return new ResponseEntity<>(weeklyRevenue, HttpStatus.OK);
    }

    @GetMapping("/revenue/monthly")
    public ResponseEntity<Double> getMonthlyRevenue() {
        double monthlyRevenue = contractService.calculateMonthlyRevenue();
        return new ResponseEntity<>(monthlyRevenue, HttpStatus.OK);
    }
}
