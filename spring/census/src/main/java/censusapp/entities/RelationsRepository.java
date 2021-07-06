package censusapp.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelationsRepository extends CrudRepository<Relations,String> {
    Relations save(Relations relation);

    @Query("SELECT r FROM Relations r WHERE r.firstMember.memberId =:firstMemberId")
    List<Relations> findAllByFirstMember(@Param("firstMemberId") String firstMemberId);
}
