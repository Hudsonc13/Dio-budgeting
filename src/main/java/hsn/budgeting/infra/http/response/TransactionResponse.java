package hsn.budgeting.infra.http.response;

import hsn.budgeting.application.TransactionOutput;
import hsn.budgeting.domain.Category;

import java.time.Instant;
import java.time.LocalDate;

public record TransactionResponse(String description, long amount, Category category, LocalDate date) {

    public static TransactionResponse from(TransactionOutput output){
        return new TransactionResponse(output.description(), output.amount(), output.category(), output.date());
    }

}
