package in.co.najah.najahhr.repository;

import in.co.najah.najahhr.entity.Industries;
import in.co.najah.najahhr.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobsRepository extends JpaRepository<Jobs,Long> {


   @Query("Select SUM(jobs.openings),jobs.division.divisionName,jobs.location from Jobs jobs where jobs.division.industries =:industries group by jobs.division.divisionName,jobs.location")
   List<Object[]> findJobsByIndustry(@Param("industries")Industries industries);

    Optional<Jobs> findByEmailId(String emailId);

    List<Optional<Jobs>> findByDivisionId(long division);

    @Query("Select SUM(jobs.openings),jobs.division.divisionName,jobs.location,jobs.division.industries.name from Jobs jobs  group by jobs.division.divisionName,jobs.location,jobs.division.industries.name")
    List<Object[]> findAllJobs();
}
