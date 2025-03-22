package com.HongVuongStore.chothuedonu.request;

import com.HongVuongStore.chothuedonu.entity.Contract;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContractRequest {
    private Contract contract;
    private List<Long> productIds;
}
