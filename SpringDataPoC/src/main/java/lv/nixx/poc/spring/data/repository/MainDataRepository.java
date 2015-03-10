package lv.nixx.poc.spring.data.repository;

import java.util.List;

import lv.nixx.poc.spring.data.domain.MainData;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainDataRepository extends CrudRepository<MainData, Long> {
	
	// Важно использовать ключевое слово Fetch, в этом случае, additionalData будет 
	// передана в объект MainData
	@Query("SELECT m FROM MainData m left join fetch m.additionalData")
	public List<MainData> findAllMainData();

}
