    package uz.avazbek.map.university.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.avazbek.map.entity.Address;
    import uz.avazbek.map.repository.AddressRepository;
    import uz.avazbek.map.university.entity.Student;
    import uz.avazbek.map.university.entity.Subject;
    import uz.avazbek.map.university.repository.StudentRepository;
    import uz.avazbek.map.university.repository.SubjectRepository;
    import uz.avazbek.map.university.univerDTO.StudentDTO;

    import java.util.List;
    import java.util.Optional;

    @RestController

    public class StudentController {

        @Autowired
        private StudentRepository studentRepository ;
        @Autowired
        private AddressRepository addressRepository ;

        //get All Student
        @RequestMapping(value = "/student" , method = RequestMethod.GET)
        public List<Student> getStudents(){
            return studentRepository.findAll() ;
        }

        //get Student
        @RequestMapping(value = "/student/{id}" , method = RequestMethod.GET)
        public Student getStudent(@PathVariable Integer id){
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()){
                return optionalStudent.get();
            }
            return new Student();
        }

        //delete Student
        @RequestMapping(value = "/student/{id}" , method = RequestMethod.DELETE)
        public String deleteStudent(@PathVariable Integer id){
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()){
                studentRepository.deleteById(id);
                return "Deleted Student";
            }
            return "Student not found";
        }

        //save Student
        @RequestMapping(value = "/student" , method = RequestMethod.POST)
        public String saveSubject( @RequestBody StudentDTO studentDTO){
          try{
              Address address = new Address();
              address.setStreetName(studentDTO.getStreetName());
              address.setHomeNumber(studentDTO.getHomeNumber());
              Address saveAddress = addressRepository.save(address);
              Student student = new Student();
              student.setAddress(saveAddress);
              student.setStudentName(studentDTO.getStudentName());
              student.setStudentSurname(studentDTO.getStudentSurname());
              studentRepository.save(student);
              return "Saved Student ";
          }catch (Exception e){
              return " Error save Student";
          }

        }

        //Update Subject
        @RequestMapping(value = "/subject/{id}" , method = RequestMethod.PUT)
        public String updateSubject(@PathVariable Integer id , @RequestBody Student student){
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent() ){
                Student student1 = optionalStudent.get();
                if (!student.getStudentName().isEmpty())
                student1.setStudentName(student.getStudentName());
                if (!student.getStudentSurname().isEmpty())
                    student1.setStudentSurname(student.getStudentSurname());
                return "Update student";
            }
            return "Student not found";
        }

    }
