package ru.aconsultant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.aconsultant.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	
}
