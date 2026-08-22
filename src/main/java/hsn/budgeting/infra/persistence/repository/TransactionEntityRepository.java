package hsn.budgeting.infra.persistence.repository;

import hsn.budgeting.domain.Category;
import hsn.budgeting.infra.persistence.entity.TransactionEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;


public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByCategory(Category category);
}
