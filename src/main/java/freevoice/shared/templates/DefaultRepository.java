package freevoice.shared.templates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultRepository<T> extends JpaRepository<T, Long> {
}
