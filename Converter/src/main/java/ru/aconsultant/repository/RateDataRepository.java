package ru.aconsultant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.aconsultant.entity.RateData;

public interface RateDataRepository extends JpaRepository<RateData, String> {

	/// !!!
	@Query(value = "SELECT \r\n" + 
			"c.id AS currency_id, \r\n" +
			"c.charcode || ' (' || c.name || ')' AS name, \r\n" + 
			"r.value, \r\n" + 
			"r.date \r\n" + 
			"FROM Currency c LEFT JOIN Rate r\r\n" + 
			"ON c.id = r.currency_id\r\n" + 
			"AND r.date = date '2020-08-03'", 
			nativeQuery = true)
    List<RateData> findDataOnDate();
}
