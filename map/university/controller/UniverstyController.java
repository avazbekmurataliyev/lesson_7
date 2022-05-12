    package uz.avazbek.map.university.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.avazbek.map.entity.Address;
    import uz.avazbek.map.repository.AddressRepository;
    import uz.avazbek.map.university.entity.*;
    import uz.avazbek.map.university.repository.*;
    import uz.avazbek.map.university.univerDTO.GroupDTO;
    import uz.avazbek.map.university.univerDTO.UniversityDTO;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @RestController

    public class UniverstyController {

        @Autowired
        private GroupsRepository groupsRepository ;
        @Autowired
        private StudentRepository studentRepository ;
        @Autowired
        private AddressRepository addressRepository ;
        @Autowired
        private SubjectRepository subjectRepository ;
        @Autowired
        private TeacherRepository teacherRepository;
        @Autowired
        private UniversityRepository universityRepository;
        @Autowired
        private FacultityRepository facultityRepository ;

        @RequestMapping(value = "/university" , method = RequestMethod.GET)
        public List<Groups> getUniversty(){
            return groupsRepository.findAll() ;
        }


        @RequestMapping(value = "/university/{id}" , method = RequestMethod.GET)
        public Universty getUniversty(@PathVariable Integer id){
            Optional<Universty> optionalUniversty = universityRepository.findById(id);
            if (optionalUniversty.isPresent()){
                return optionalUniversty.get();
            }
            return new Universty();
        }


        @RequestMapping(value = "/university/{id}" , method = RequestMethod.DELETE)
        public String deleteGroups(@PathVariable Integer id){
            Optional<Universty> optionalUniversty = universityRepository.findById(id);
            if (optionalUniversty.isPresent()){
                universityRepository.deleteById(id);
                return "Deleted university";
            }
            return "university not found";
        }


        @RequestMapping(value = "/university" , method = RequestMethod.POST)
        public String saveSubject( @RequestBody UniversityDTO universityDTO){
          try{
              Address address = new Address();
              address.setStreetName(universityDTO.getStreetName());
              address.setHomeNumber(universityDTO.getHomeNumber());
              Address saveAddress = addressRepository.save(address);

              Student student = new Student();
              student.setAddress(saveAddress);
              student.setStudentName(universityDTO.getStudentName());
              student.setStudentSurname(universityDTO.getStudentSurname());
              Student saveStudent = studentRepository.save(student);
              List<Student> students = new ArrayList<>();
              students.add(saveStudent);

              Subject subject = new Subject();
              subject.setSubjectName(universityDTO.getSubjectName());
              Subject saveSubject = subjectRepository.save(subject);
              List<Subject> subjects = new ArrayList<>();
              subjects.add(saveSubject);

              Teacher teacher = new Teacher();
              teacher.setTeacherName(universityDTO.getTeacherName());
              teacher.setSpecialization(universityDTO.getSpecialization());
              Teacher saveTeacher = teacherRepository.save(teacher);
              List<Teacher> teachers = new ArrayList<>();
              teachers.add(teacher);

              Groups group = new Groups();
                group.setGroupName(universityDTO.getGroupName());
                group.setSubjects(subjects);
                group.setStudents(students);
                group.setTeachers(teachers);
              Groups saveGroup = groupsRepository.save(group);
              List<Groups> groups = new ArrayList<>();
              groups.add(group);
              Facultity facultity = new Facultity();
              facultity.setFacultityName(universityDTO.getFacultityName());
                facultity.setGroups(groups);
              Facultity save = facultityRepository.save(facultity);
              List<Facultity> facultities = new ArrayList<>();
              facultities.add(facultity);
              Universty universty = new Universty();
                universty.setAddress(saveAddress);
                universty.setFacultities(facultities);
                universty.setUniversityName(universityDTO.getUniversityName());
                universityRepository.save(universty);

              return "Saved Universty ";

          }catch (Exception e){

              return " Error save university";

          }

        }


        @RequestMapping(value = "/university/{id}" , method = RequestMethod.PUT)
        public String updateSubject(@PathVariable Integer id , @RequestBody Universty universty){
            Optional<Universty> optionalUniversty = universityRepository.findById(id);
            if (optionalUniversty.isPresent() ){
                Universty universty1 = optionalUniversty.get();
                if (!universty.getUniversityName().isEmpty())
                    universty1.setUniversityName(universty.getUniversityName());
                return "Update university";
            }
            return "university not found";
        }

    }
