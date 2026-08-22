package hsn.budgeting.application;

import hsn.budgeting.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record TransactionInput(@ToolParam(description = "Descrição da transação") String description,
                               @ToolParam(description = "Valor em centavos") long amount,
                               @ToolParam(description = "Categoria") Category category) {


}
