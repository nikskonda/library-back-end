package by.bntu.fitr.repository.user.util;

import by.bntu.fitr.model.user.util.Country;
import by.bntu.fitr.model.user.util.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Set<State> findStatesByCountry(Country country);

    Set<State> findStatesByCountryId(Long countryId);

}
