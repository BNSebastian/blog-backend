package freevoice.shared.templates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Specify the entity type (e.g., T) to avoid issues with managed types.
@Repository
public interface DefaultRepository<T> extends JpaRepository<T, Long> {
}
