package net.personaldiary.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.personaldiary.springboot.entity.Diary;
import net.personaldiary.springboot.repository.DiaryRepository;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/diary")
public  class DiaryController{

	    @Autowired
	    private DiaryRepository diaryRepo;
	    
	 // Home Page 
//	    @GetMapping("/") 
//	    public String welcome() 
//	    { 
//	        return "<html><body>"
//	            + "<h1>WELCOME</h1>"
//	            + "</body></html>"; 
//	    } 
	    
	    //get all diary entries
//	    @GetMapping("/diary")
	    
	    @GetMapping
		public List<Diary> getAllDiaryEntries() {
			System.out.println("Get all diary entries...");
			
			List list = new ArrayList<>();
			Iterable entries = diaryRepo.findAll();

			entries.forEach(list::add);
			return list;
			//return diaryRepo.findAll();
		}
	       
	    @PostMapping
	    public Diary createDiary(@RequestBody Diary diary) {
	    	System.out.println("Create Diary: " + diary.getDate() + "..." + diary.getEntry());
		    System.out.println(diary);
	       return this.diaryRepo.save(diary);
	    }
	    
	    //original method we have
//	    @GetMapping("/{startDate}")
//		public ResponseEntity getdiary(@PathVariable("startDate") Long id) {
//			System.out.println("Get diary by id..." + id);
//
//			Optional diaryData = diaryRepo.findById(id);
//			if (diaryData.isPresent()) {
//				return new ResponseEntity<>(diaryData.get(), HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//		}
	    
	    //method to return all data with user specified date.
	    //According to Spring documentation
	    //The below ResponseEntity extends HttpEntity that adds an HttpStatus status code. Used in RestTemplate as well as in @Controller methods.
	    //This can also be used in Spring MVC as the return value from an @Controller method:
	    //Also remember Optional is a raw type meaning general type and can be used for all types.
	    @GetMapping("/{startDate}")
		public ResponseEntity getdiary(@PathVariable("startDate") String date) {
			System.out.println("Get diary by id..." + date);
			//Long temp = Long.parseLong(String.format(id, null));
			//System.out.println(temp);
			
			List<Diary> diaryData = diaryRepo.findBydate(date);
			if (diaryData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			} else {
				return new ResponseEntity<>(diaryData.stream(), HttpStatus.OK);
			}
		}
	    
//	    @GetMapping("/{id}")
//			public String dateTimeApiV1(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date ) {
//	    	System.out.println(date);
//			return "DONE";
//		}
	    
	    @PutMapping("{id}")
		public ResponseEntity updateDiary(@PathVariable("id") Long id, @RequestBody Diary diary) {
			System.out.println("Update diary with ID = " + id + "...");

			  Optional<Diary> diaryData = diaryRepo.findById(id);
			if (diaryData.isPresent()) {
				Diary savedDiary = (Diary) diaryData.get();
				savedDiary.setDate(diary.getDate());
				savedDiary.setEntry(diary.getEntry());
			//	savedDiary.setId(diary.getId());

				Diary updatedDiary = diaryRepo.save(savedDiary);
				return new ResponseEntity<>(updatedDiary, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	    
	    @DeleteMapping("{id}")
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
