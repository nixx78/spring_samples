package lv.nixx.poc.spring.data.repository;

import lv.nixx.poc.spring.data.domain.GenericPerson;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<GenericPerson, Long> {
}
