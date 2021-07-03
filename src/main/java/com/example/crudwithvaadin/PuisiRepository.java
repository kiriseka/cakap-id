package com.example.crudwithvaadin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PuisiRepository extends JpaRepository<Puisi, Long> {

	@Query("select c from Puisi c where upper(c.judulPuisi) like upper(concat(?1, '%'))")
	List<Puisi> findByIsiPuisiStartsWithIgnoreCase(String juduPuisi);
}
