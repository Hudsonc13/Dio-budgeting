package hsn.budgeting.application;

import hsn.budgeting.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.LocalDate;
import java.util.Optional;

public record TransactionInput(@ToolParam(description = "Descrição da transação") String description,
                               @ToolParam(description = "Valor em centavos") long amount,
                               @ToolParam(description = "Categoria") Category category,
                               @ToolParam(description = "Data da transação", required = false) Optional<LocalDate> date) {


}
