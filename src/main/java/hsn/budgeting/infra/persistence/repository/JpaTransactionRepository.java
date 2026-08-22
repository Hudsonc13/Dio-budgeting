package hsn.budgeting.infra.persistence.repository;

import hsn.budgeting.domain.Category;
import hsn.budgeting.domain.Transaction;
import hsn.budgeting.domain.TransactionRepository;
import hsn.budgeting.infra.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionEntityRepository entityRepository;

    public JpaTransactionRepository(TransactionEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }


    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return entityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return entityRepository.findAllByCategory(category)
                .stream()
                .map(TransactionEntity::toDomain)
                .toList();
    }
}
