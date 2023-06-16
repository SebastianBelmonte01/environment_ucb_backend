package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
