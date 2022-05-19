package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final studentRepository studentRepository;
    @Autowired
    public StudentService(studentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public List<Student> getStudents(){
       return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student>  studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            try {
                throw new IllegalAccessException("Email Taken");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
       studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);
       if(!exists){
           try {
               throw new IllegalAccessException("student with id " + studentId + "does not exists");
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }
       }
       studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId).orElseThrow(() ->new IllegalStateException("" +
                "student with id" + studentId + "does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);

        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail()
                , email)){
            student.setEmail(email);

        }
    }
}
