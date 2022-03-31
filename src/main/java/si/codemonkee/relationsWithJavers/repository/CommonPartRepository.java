package si.codemonkee.relationsWithJavers.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.codemonkee.relationsWithJavers.model.CommonPart;

@Repository
@JaversSpringDataAuditable
public interface CommonPartRepository extends JpaRepository<CommonPart, Long> {
}
