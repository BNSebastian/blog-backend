package freevoice.features.videos.videos;

import freevoice.features.videos.videos.models.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByName(String name);
    boolean existsByName(String name);
    @Query(nativeQuery = true, value="SELECT name FROM video")
    List<String> getAllEntryNames();

    @Query(nativeQuery = true, value="SELECT description FROM video WHERE name = :videoName LIMIT 1")
    String getVideoDescriptionByName(String videoName);
}
