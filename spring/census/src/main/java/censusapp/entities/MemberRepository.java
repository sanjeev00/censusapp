package censusapp.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends CrudRepository<Member, String> {
    List<Member> findAll();

    @Query("SELECT m FROM Member m WHERE m.application.applicationId =:applicationId")
    List<Member> findByApplication(@Param("applicationId") String applicationId);

    Optional<Member> findByMemberId(String id);

    Member save(Member member);

    void deleteById(String s);
}
