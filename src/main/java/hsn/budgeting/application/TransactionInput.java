package hsn.budgeting.application;

import hsn.budgeting.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.LocalDate;
import java.util.Optional;

public record TransactionInput(@ToolParam(description = "Descrição da transação (onde o dinheiro foi gasto)") String description,
                               @ToolParam(description = "Valor da transação") long amount,
                               @ToolParam(description = "Categoria da trasação") Category category,
                               @ToolParam(description = "Data da transação", required = false) Optional<LocalDate> date) {


}
