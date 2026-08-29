package hsn.budgeting.infra.http.request;

import hsn.budgeting.application.TransactionInput;
import hsn.budgeting.domain.Category;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record TransactionRequest(String description, long amount, Category category, Optional<LocalDate> date) {

    public TransactionInput toInput(){
        return new TransactionInput(description, amount, category, date);
    }




}
