package com.app.pojos;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Transaction extends BaseEntity {

    private Double amount;

    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "account_id") // Explicitly specify the foreign key column
    private Account account;

    // Lombok @Data generates getters and setters, toString, equals, and hashCode methods.
}
