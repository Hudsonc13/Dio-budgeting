package hsn.budgeting.application;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateTransactionUseCase {

    @Tool(name = "create-transaction",
            description = "Cria uma lista com 1 ou mais transactions",
            returnDirect = true)
    public List<TransactionOutput> execute(List<TransactionInput> inputs){

        return inputs
                .stream()
                .map(TransactionOutput::fromInput)
                .toList();

    }


}
