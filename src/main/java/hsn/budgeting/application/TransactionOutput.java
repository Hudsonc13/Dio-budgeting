package hsn.budgeting.application;

import hsn.budgeting.domain.Category;
import hsn.budgeting.domain.Transaction;

public record TransactionOutput(String id, String description, long amount, Category category) {

    public static TransactionOutput from(Transaction transaction){
        return new TransactionOutput(transaction.getId().id().toString(), transaction.getDescription(), transaction.getAmount(), transaction.getCategory());
    }



}
