package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.Industries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndustriesRepository extends JpaRepository<Industries,Long> {
    Optional<Industries> findByNameOrUrl(String industryName, String url);

    Optional<Industries> findByUrl(String identifier);
}
