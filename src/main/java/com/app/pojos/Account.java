package com.app.pojos;

import com.app.enums.AccountType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
public class Account extends BaseEntity {
    
    private String accountNumber;
    
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    
    private Double balance;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Lombok @Data generates getters and setters, toString, equals, and hashCode methods.
}
