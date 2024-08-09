package com.app.pojos;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Customer extends BaseEntity {

    private String name;
    
    private String city;
    
    private String email;
    
    private String phoneNumber;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;

    // Lombok @Data generates getters and setters, toString, equals, and hashCode methods.
}
