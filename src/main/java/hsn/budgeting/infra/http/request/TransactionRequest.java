package hsn.budgeting.infra.http.request;

import hsn.budgeting.application.TransactionInput;
import hsn.budgeting.domain.Category;

import java.time.Instant;
import java.time.LocalDate;

public record TransactionRequest(String description, long amount, Category category, LocalDate date) {

    public TransactionInput toInput(){
        return new TransactionInput(description, amount, category, date);
    }
}
