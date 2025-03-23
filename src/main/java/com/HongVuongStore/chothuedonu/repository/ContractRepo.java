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

    @Query("SELECT COALESCE(SUM(c.totalPrice), 0.0) FROM Contract c WHERE c.createdDate BETWEEN :startDate AND :endDate")
    Double calculateRevenueForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT MONTH(c.createdDate) as month, SUM(c.totalPrice) as revenue " +
            "FROM Contract c " +
            "WHERE YEAR(c.createdDate) = :year " +
            "GROUP BY MONTH(c.createdDate)")
    List<Object[]> findMonthlyRevenueByYear(@Param("year") int year);

    // Truy vấn doanh thu theo tuần trong tháng
    @Query("SELECT WEEK(c.createdDate) as week, SUM(c.totalPrice) as revenue " +
            "FROM Contract c " +
            "WHERE MONTH(c.createdDate) = :month AND YEAR(c.createdDate) = :year " +
            "GROUP BY WEEK(c.createdDate)")
    List<Object[]> findWeeklyRevenueByMonth(@Param("month") int month, @Param("year") int year);
}
