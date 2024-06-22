package com.breno.transaction_api.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "transactions_db")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;
    private float total;
    private LocalDateTime createdAt;
    private String cardNumber;
    private String cvv;
    private String owner;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public static boolean isCardNumberValid(String cardNumber) {
        // It can only contain numbers, dots and/or blank spaces. 
        if (!cardNumber.matches("[\\d. ]+")) {
            return false;
        }

        if (cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }

        return luhnCheck(cardNumber);
    }

    protected static boolean luhnCheck(String cardNumber) {
        int digits = cardNumber.length();
        int oddOrEven = digits & 1;
        long sum = 0;

        for (int count = 0; count < digits; count++) {
            int digit = 0;
            try {
                digit = Integer.parseInt(
                    cardNumber.substring(count, count + 1)
                );
            } catch (NumberFormatException e) {
                return false;
            }

            if (((count & 1) ^ oddOrEven) == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return sum % 10 == 0;
    }
}
