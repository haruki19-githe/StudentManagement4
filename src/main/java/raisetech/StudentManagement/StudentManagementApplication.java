package raisetech.StudentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import raisetech.StudentManagement.Repository.StudentRepository;


@SpringBootApplication
public class StudentManagementApplication {

    @Autowired
    private StudentRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }


}