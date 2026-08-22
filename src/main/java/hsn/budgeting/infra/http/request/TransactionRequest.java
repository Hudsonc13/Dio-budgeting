package hsn.budgeting.infra.http.request;

import hsn.budgeting.application.TransactionInput;
import hsn.budgeting.domain.Category;

public record TransactionRequest(String description, long amount, Category category) {

    public TransactionInput toInput(){
        return new TransactionInput(description, amount, category );
    }
}
