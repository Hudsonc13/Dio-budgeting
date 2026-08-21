package hsn.budgeting.application;

import hsn.budgeting.domain.Category;
import hsn.budgeting.domain.Transaction;

public record PersistTransactionOutput(String description, long amount, Category category) {

    public static PersistTransactionOutput from(Transaction transaction){
        return new PersistTransactionOutput(transaction.getDescription(), transaction.getAmount(), transaction.getCategory());
    }



}
