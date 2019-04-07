package by.bntu.fitr.repository.user.util;

import by.bntu.fitr.model.user.util.City;
import by.bntu.fitr.model.user.util.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Set<City> findCitiesByState(State state);

    Set<City> findCitiesByStateId(Long stateId);

}
