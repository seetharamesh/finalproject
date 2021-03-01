package net.personaldiary.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.personaldiary.springboot.entity.Diary;

@Component
public class DatabaseLoader implements CommandLineRunner { 

	private final DiaryRepository repository;

	@Autowired
	public DatabaseLoader(DiaryRepository repository) {
		this.repository = repository;
	}

//	The run() method is invoked with command line arguments, loading up your data.
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inside DatabaseLoader Testing Testing Testing");
		//this.repository.save(new Diary(new java.util.Date(),"First day of springboot-- tested so many times"));		
	}
}