package com.HongVuongStore.chothuedonu.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDetailResponse {
    private Long id;
    private String customerCCNumber;
    private String nameCustomer;
    private String phoneNumberCustomer;
    private Double totalPrice;
    private Integer status;
    private String note;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;
    private List<ProductDetailResponse> products;
}
