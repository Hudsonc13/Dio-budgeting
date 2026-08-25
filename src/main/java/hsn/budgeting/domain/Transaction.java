package hsn.budgeting.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;


@Getter
@AllArgsConstructor
public class Transaction {

    private TransactionId id;
    private String description;
    private long amount;
    private Category category;
    private LocalDate date;


    public Transaction(String description, long amount, Category category, Optional<LocalDate> date) {
        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date.get();
    }

}
