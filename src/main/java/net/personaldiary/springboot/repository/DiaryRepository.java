package net.personaldiary.springboot.repository;

import java.util.List;

//import java.util.Date;
//import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.personaldiary.springboot.entity.Diary;

/*This repository will provide CRUD operation for "Diary" entity. We are mapping the Diary entity to primary key of type Long*/

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
List<Diary> findBydate(String date);
	
	
	
//	@Query("select d from Diary d where d.date = date")
//	Optional<Diary> findByDate(String date);
//
//	Optional getDiaryByDate(String date);

	
	
//	@Query("select * from Diary d where d.date = {startDate}")
//	Optional findById(@Param("startDate") String startDate);
	//The functions to perform the CRUD operations will be defined in the interface 
    //List<Diary> findAll(); 

//    Company findById(int id); 
//    List<Company> findAll(); 
//    void deleteById(int id); 
}
