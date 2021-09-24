package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import platform.model.Code;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    @Query(value = "SELECT * FROM code WHERE id = ?1", nativeQuery = true)
    Optional<Code> findById(String id);

    @Query(value = "SELECT * FROM code WHERE views <= 0 AND time <= 0 AND restricted = false ORDER BY creation_timestamp DESC LIMIT 10", nativeQuery = true)
    List<Code> findLatest();

    @Modifying
    @Query(value = "UPDATE code SET views = ?2, time = ?3 WHERE id = ?1", nativeQuery = true)
    void updateCodeViewsAndTime(UUID id, Integer viewsLeft, Long timeLeft);

    @Modifying
    @Query(value = "DELETE FROM code WHERE id = ?1", nativeQuery = true)
    void deleteById(UUID id);
}
