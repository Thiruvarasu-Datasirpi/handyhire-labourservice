package handyhire.labourservice.repository;

import handyhire.labourservice.enitiy.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Jobs,Integer> {
}
