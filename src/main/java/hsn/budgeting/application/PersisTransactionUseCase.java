package hsn.budgeting.application;

import com.ethlo.time.DateTime;
import hsn.budgeting.domain.Transaction;
import hsn.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class PersisTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public PersisTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Tool(name = "persist-transaction", description = "Salva uma nova transação")
    public TransactionOutput execute(TransactionInput input){
        var transaction = transactionRepository
                .save(new Transaction(input.description(), input.amount(), input.category(), input.date()));

        return TransactionOutput.from(transaction);
    }

}
