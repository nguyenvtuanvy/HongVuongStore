package com.HongVuongStore.chothuedonu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Contact")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_cc_number", nullable = false)
    private String customerCCNumber;
    @Column(name = "name_customer", nullable = false)
    private String nameCustomer;
    @Column(name = "phone_number_customer", nullable = false)
    private String phoneNumberCustomer;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    @Column(name = "status")
    private Integer status;
    @Column(name = "note", nullable = false)
    private String note;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;

    @ManyToMany
    @JoinTable(
            name = "contract_product",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private Set<Product> products;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = 1;
        }
    }
}
