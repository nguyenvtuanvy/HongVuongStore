package com.HongVuongStore.chothuedonu.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponse {
    private Long id;
    private String name;
    private double price;
    private String image;
}
