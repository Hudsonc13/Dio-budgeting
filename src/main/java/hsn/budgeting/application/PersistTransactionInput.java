package hsn.budgeting.application;

import hsn.budgeting.domain.Category;

public record PersistTransactionInput(String description, long amount, Category category) {
}
