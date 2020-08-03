package ru.aconsultant.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ru.aconsultant.entity.Rate;

public interface RateRepository extends JpaRepository<Rate, Integer> {

	List<Rate> findAllByDate(Calendar calendar);
}
