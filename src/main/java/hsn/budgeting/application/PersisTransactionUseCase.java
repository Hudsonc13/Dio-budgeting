package hsn.budgeting.application;

import hsn.budgeting.domain.Transaction;
import hsn.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersisTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public PersisTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Tool(name = "persist-transaction", description = "Salva uma ou mais transações")
    public List<TransactionOutput> execute(@ToolParam(description = "Lista de transações") List<TransactionInput> inputs){

        List<Transaction> transactions = new ArrayList<>();

        for(TransactionInput i : inputs) {
            transactions.add(transactionRepository
                    .save(new Transaction(i.description(), i.amount(), i.category(), i.date())));
        }
        return transactions.stream().map(TransactionOutput::from).toList();
    }

}
