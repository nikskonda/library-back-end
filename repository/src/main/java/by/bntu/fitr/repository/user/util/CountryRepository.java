package by.bntu.fitr.repository.user.util;

import by.bntu.fitr.model.user.util.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findCountriesByOrderByNameAsc();

}
