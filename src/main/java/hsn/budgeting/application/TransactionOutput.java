package hsn.budgeting.application;

import hsn.budgeting.domain.Category;
import hsn.budgeting.domain.Transaction;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record TransactionOutput(String description, long amount, Category category, LocalDate date) {

    public static TransactionOutput from(Transaction transaction){
        return new TransactionOutput(transaction.getDescription(), transaction.getAmount(), transaction.getCategory(), transaction.getDate());
    }


}
