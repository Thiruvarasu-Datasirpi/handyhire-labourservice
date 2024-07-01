package handyhire.labourservice.repository;

import handyhire.labourservice.enitiy.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillsRepository extends JpaRepository<Skills,Integer> {

}
