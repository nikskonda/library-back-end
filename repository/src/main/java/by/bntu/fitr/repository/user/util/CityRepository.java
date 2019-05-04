package by.bntu.fitr.repository.user.util;

import by.bntu.fitr.model.user.util.City;
import by.bntu.fitr.model.user.util.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findCitiesByStateOrderByNameAsc(State state);

    List<City> findCitiesByStateIdOrderByNameAsc(Long stateId);

}
