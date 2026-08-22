package hsn.budgeting.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionRepository {

    Transaction save(Transaction transaction);

    List<Transaction> findAllByCategory(Category category);


}
