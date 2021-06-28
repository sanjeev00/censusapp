package censusapp.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends CrudRepository<Member, String> {
    List<Member> findAll();

    Optional<Member> findByMemberId(String id);

    Member save(Member member);
}
