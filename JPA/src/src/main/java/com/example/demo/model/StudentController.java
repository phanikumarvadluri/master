package com.example.demo.model;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;


    @Autowired
    StudentService studentService;


    @GetMapping("/student")
    public List<Student> getAll() {
        List<Student> all = studentRepository.findAll();
        return all;
    }

    @PostMapping("/student")
    public Student save(@RequestBody Student student) {
//        Address address = student.getAddress();
//        Address save1 = addressRepository.save(address);
//        student.setAddress(save1);
        Student save = studentRepository.save(student);
        List<Subject> subject = save.getSubject();
        for (Subject sub:subject)
        {
            sub.setStudent(save);
        }
        subjectRepository.saveAll(subject);
        return save;
    }

    @PutMapping("/student/{id}")//whole update
    public Student save(@RequestBody Student student, @PathVariable int id) {
        Optional<Student> byId = studentRepository.findById(id);
        Student student1 = byId.get();
        student1.setEmail(student.getEmail());
        student1.setName(student.getName());
        Student save = studentRepository.save(student1);
        return save;
    }

    @PatchMapping("/student/{id}")// required fileds only
    public Student patch(@RequestBody Student student, @PathVariable int id) {
        Optional<Student> byId = studentRepository.findById(id);
        Student student1 = byId.get();
        if (student.getEmail() != null) {
            student1.setEmail(student.getEmail());
        }
        if (student.getName() != null) {
            student1.setName(student.getName());
        }
        Student save = studentRepository.save(student1);
        return save;
    }

    @GetMapping("/student/{name}")// required fileds only
    public List<Student> patch(@PathVariable String name) {
        List<Student> byName = studentRepository.findByName(name);
        return byName;
    }

    @GetMapping("/student/{name}/{gender}")// required fileds only
    public List<Student> gender(@PathVariable String name,@PathVariable String gender) {
        List<Student> byName = studentRepository.findByNameAndGender(name,gender);
        return byName;
    }

    @GetMapping("/page/{pageNum}/{pagesize}")// required fileds only
    public List<Student> some(@PathVariable int pageNum,@PathVariable int pagesize) {
        List<Student> byName = studentService.getStudentsWithpage(pageNum,pagesize);
        return byName;
    }
}
