package com.HongVuongStore.chothuedonu.repository;

import com.HongVuongStore.chothuedonu.entity.Contract;
import com.HongVuongStore.chothuedonu.response.ContractDetailResponse;
import com.HongVuongStore.chothuedonu.response.ProductDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ContractRepo extends JpaRepository<Contract, Long> {
    @Query("SELECT new com.HongVuongStore.chothuedonu.response.ProductDetailResponse(" +
            "p.id, p.name, p.price, p.image) " +
            "FROM Product p " +
            "JOIN p.contracts c " +
            "WHERE c.id = :contractId")
    List<ProductDetailResponse> findProductsByContractId(@Param("contractId") Long contractId);

    @Query("select c from Contract c where c.status = 1")
    List<Contract> findContractsByStatus();

    @Query("SELECT SUM(c.totalPrice) FROM Contract c WHERE c.createdDate BETWEEN :startDate AND :endDate")
    Double calculateRevenueForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
