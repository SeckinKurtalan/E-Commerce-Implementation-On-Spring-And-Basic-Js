package project.ecommerce.account.repositories;

import project.ecommerce.account.models.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByEmail(String email);
}
