package hsn.budgeting.application;

import hsn.budgeting.domain.Transaction;
import hsn.budgeting.domain.TransactionRepository;

public class PersisTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public PersisTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public PersistTransactionOutput execute(PersistTransactionInput input){
        var transaction = transactionRepository
                .save(new Transaction(input.description(), input.amount(), input.category()));

        return PersistTransactionOutput.from(transaction);
    }

}
