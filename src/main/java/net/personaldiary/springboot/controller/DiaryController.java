package net.personaldiary.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.personaldiary.springboot.entity.Diary;
import net.personaldiary.springboot.repository.DiaryRepository;

@CrossOrigin(origins = "http://localhost:8080/diary")
@RestController
@RequestMapping("/api")
public class DiaryController {

	//Following are the RestAPI's needed:
	
	//retrieve all diary entries for a date range --RETRIEVE
	//update a diary entry --UPDATE
	//delete a diary entry -- DELETE
	//add a diary entry-- CREATE
	
	    @Autowired
	    private DiaryRepository diaryRepo;
	    
	    @GetMapping("/diary")
		public List getAllDiaryEntries() {
			System.out.println("Get all diary entries...");

			List list = new ArrayList<>();
			Iterable entries = diaryRepo.findAll();

			entries.forEach(list::add);
			return list;
		}
	       
	    @PostMapping("/diary/create")
	    public Diary createDiary(@RequestBody Diary diary) {
	    	System.out.println("Create Diary: " + diary.getDate() + "..." + diary.getEntry());
	       return this.diaryRepo.save(diary);
	    }
	    
	    @GetMapping("/diary/{id}")
		public ResponseEntity getdiary(@PathVariable("id") Long id) {
			System.out.println("Get diary by id...");

			Optional diaryData = diaryRepo.findById(id);
			if (diaryData.isPresent()) {
				return new ResponseEntity<>(diaryData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	    
	    @PutMapping("/diary/{id}")
		public ResponseEntity updateBook(@PathVariable("id") Long id, @RequestBody Diary diary) {
			System.out.println("Update diary with ID = " + id + "...");

			Optional diaryData = diaryRepo.findById(id);
			if (diaryData.isPresent()) {
				Diary savedDiary = (Diary) diaryData.get();
				savedDiary.setDate(diary.getDate());
				savedDiary.setEntry(diary.getEntry());
				savedDiary.setId(diary.getId());

				Diary updatedDiary = diaryRepo.save(savedDiary);
				return new ResponseEntity<>(updatedDiary, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	    
	    @DeleteMapping("/diary/{id}")
		public ResponseEntity deleteDiary(@PathVariable("id") Long id) {
			System.out.println("Delete Diary with ID = " + id + "...");

			try {
				diaryRepo.deleteById(id);
			} catch (Exception e) {
				return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
			}

			return new ResponseEntity<>("Diary has been deleted!", HttpStatus.OK);
		}
}
