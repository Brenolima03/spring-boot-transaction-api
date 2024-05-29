package com.breno.transaction_api.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransactionDTO {
    private float total;
    private LocalDateTime createdAt;
    private String cardNumber;
    private String cvv;
    private String owner;
}
