package hsn.budgeting.application;

import hsn.budgeting.domain.Category;
import hsn.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;

@Service
public class ListTransactionsByCategoryUseCase {

    private final TransactionRepository transactionRepository;

    public ListTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Tool(name = "list-transaction", description = "Retorna as transações existentes por categoria")
    public List<TransactionOutput> execute(@ToolParam(description = "Categoria de uma transação") Category category){

        var transactions = transactionRepository.findAllByCategory(category);

        return transactions.stream().map(TransactionOutput::from).toList();
    }


}
