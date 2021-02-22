package net.personaldiary.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

 //implements CommandLineRunner
@SpringBootApplication
public class SpringbootCrudRestfulWebservicesApplication  {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudRestfulWebservicesApplication.class, args);
	}
//	@Override
//	public void run(String... args) throws Exception {
//        String sql = "INSERT INTO Diary (date, entry) VALUES ("
//                + "new java.util.Date(), ' springboot')";
//         
//        int rows = jdbcTemplate.update(sql);
//        if (rows > 0) {
//            System.out.println("A new row has been inserted.");
//        }
//    }
//	    
}
