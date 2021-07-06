package censusapp.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application,String> {
    Optional<Application> findById(String s);

    Application save(Application application);
}
