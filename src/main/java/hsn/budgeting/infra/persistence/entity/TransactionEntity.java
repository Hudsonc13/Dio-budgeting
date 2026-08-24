package hsn.budgeting.infra.persistence.entity;

import hsn.budgeting.domain.Category;
import hsn.budgeting.domain.Transaction;
import hsn.budgeting.domain.TransactionId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private long amount;
    private Category category;
    private LocalDate date;


    public static TransactionEntity from(Transaction transaction){

        return new TransactionEntity(transaction.getId().id(), transaction.getDescription(), transaction.getAmount(), transaction.getCategory(), transaction.getDate());

    }

    public Transaction toDomain(){

        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                this.category,
                this.date);

    }

}
