package hsn.budgeting.infra.http.response;

import hsn.budgeting.application.TransactionOutput;
import hsn.budgeting.domain.Category;

public record TransactionResponse(String description, long amount, Category category) {

    public static TransactionResponse from(TransactionOutput output){
        return new TransactionResponse(output.description(), output.amount(), output.category());
    }

}
