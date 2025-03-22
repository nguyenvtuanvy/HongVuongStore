package com.HongVuongStore.chothuedonu.service.contract;
import com.HongVuongStore.chothuedonu.entity.Contract;
import com.HongVuongStore.chothuedonu.entity.Product;
import com.HongVuongStore.chothuedonu.repository.ContractRepo;
import com.HongVuongStore.chothuedonu.repository.ProductRepo;
import com.HongVuongStore.chothuedonu.response.ContractDetailResponse;
import com.HongVuongStore.chothuedonu.response.ProductDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepo contractRepo;
    private final ProductRepo productRepo;
    public String saveContract(Contract contract, List<Long> productIds) {
        Set<Product> products = new HashSet<>();

        for (Long productId : productIds) {
            Product product = productRepo.findById(productId).orElseThrow(() ->
                    new RuntimeException("Product with ID " + productId + " not found"));
            products.add(product);
        }

        Contract saveContract = Contract.builder()
                .nameCustomer(contract.getNameCustomer())
                .customerCCNumber(contract.getCustomerCCNumber())
                .phoneNumberCustomer(contract.getPhoneNumberCustomer())
                .totalPrice(contract.getTotalPrice())
                .note(contract.getNote())
                .products(products)
                .build();

        contractRepo.save(saveContract);

        return "Contract saved successfully with associated products!";
    }


    public String updateContractStatus(Long contractId, Integer status){
        Contract contract = contractRepo.findById(contractId).orElseThrow(() ->
                new RuntimeException("Contract with ID " + contractId + " not found"));
        contract.setStatus(status);
        contractRepo.save(contract);
        return "Contract status updated successfully!";
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void autoUpdateLaterContract(){
        List<Contract> contracts = contractRepo.findContractsByStatus();

        LocalDate currentDate = LocalDate.now(); // Ngày hiện tại

        for (Contract contract : contracts) {
            LocalDate createdDate = contract.getCreatedDate(); // Ngày tạo hợp đồng
            LocalDate dueDate = createdDate.plusDays(3); // Ngày hết hạn (3 ngày sau ngày tạo)

            // Kiểm tra nếu ngày hiện tại sau ngày hết hạn
            if (currentDate.isAfter(dueDate)) {
                contract.setStatus(2); // Cập nhật trạng thái "chậm trả"
                contractRepo.save(contract); // Lưu thay đổi vào cơ sở dữ liệu
            }
        }
    }

    public ContractDetailResponse findContractById(Long id) {
        Contract contract = contractRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        // Lấy danh sách sản phẩm
        List<ProductDetailResponse> products = contractRepo.findProductsByContractId(id);

        // Tạo và trả về ContractDetailResponse
        return ContractDetailResponse.builder()
                .id(contract.getId())
                .customerCCNumber(contract.getCustomerCCNumber())
                .nameCustomer(contract.getNameCustomer())
                .phoneNumberCustomer(contract.getPhoneNumberCustomer())
                .totalPrice(contract.getTotalPrice())
                .status(contract.getStatus())
                .note(contract.getNote())
                .createdDate(contract.getCreatedDate())
                .products(products)
                .build();
    }

    public Set<Contract> findAllContracts(){
        return new HashSet<>(contractRepo.findAll());
    }

    public double calculateWeeklyRevenue() {
        return contractRepo.calculateRevenueForPeriod(
                LocalDate.now().minusWeeks(1), LocalDate.now()
        );
    }

    public double calculateMonthlyRevenue() {
        return contractRepo.calculateRevenueForPeriod(
                LocalDate.now().minusMonths(1), LocalDate.now()
        );
    }
}
