    package uz.avazbek.map.university.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.avazbek.map.entity.Address;
    import uz.avazbek.map.repository.AddressRepository;
    import uz.avazbek.map.university.entity.Groups;
    import uz.avazbek.map.university.entity.Student;
    import uz.avazbek.map.university.entity.Subject;
    import uz.avazbek.map.university.entity.Teacher;
    import uz.avazbek.map.university.repository.GroupsRepository;
    import uz.avazbek.map.university.repository.StudentRepository;
    import uz.avazbek.map.university.repository.SubjectRepository;
    import uz.avazbek.map.university.repository.TeacherRepository;
    import uz.avazbek.map.university.univerDTO.GroupDTO;
    import uz.avazbek.map.university.univerDTO.StudentDTO;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @RestController

    public class GroupController {

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

        @RequestMapping(value = "/groups" , method = RequestMethod.GET)
        public List<Groups> getGroups(){
            return groupsRepository.findAll() ;
        }


        @RequestMapping(value = "/groups/{id}" , method = RequestMethod.GET)
        public Groups getGroup(@PathVariable Integer id){
            Optional<Groups> optionalGroups = groupsRepository.findById(id);
            if (optionalGroups.isPresent()){
                return optionalGroups.get();
            }
            return new Groups();
        }


        @RequestMapping(value = "/groups/{id}" , method = RequestMethod.DELETE)
        public String deleteGroups(@PathVariable Integer id){
            Optional<Groups> optionalGroups = groupsRepository.findById(id);
            if (optionalGroups.isPresent()){
                groupsRepository.deleteById(id);
                return "Deleted group";
            }
            return "group not found";
        }


        @RequestMapping(value = "/groups" , method = RequestMethod.POST)
        public String saveSubject( @RequestBody GroupDTO groupDTO){
          try{
              Address address = new Address();
              address.setStreetName(groupDTO.getStreetName());
              address.setHomeNumber(groupDTO.getHomeNumber());
              Address saveAddress = addressRepository.save(address);

              Student student = new Student();
              student.setAddress(saveAddress);
              student.setStudentName(groupDTO.getStudentName());
              student.setStudentSurname(groupDTO.getStudentSurname());
              Student saveStudent = studentRepository.save(student);
              List<Student> students = new ArrayList<>();
              students.add(saveStudent);

              Subject subject = new Subject();
              subject.setSubjectName(groupDTO.getSubjectName());
              Subject saveSubject = subjectRepository.save(subject);
              List<Subject> subjects = new ArrayList<>();
              subjects.add(saveSubject);

              Teacher teacher = new Teacher();
              teacher.setTeacherName(groupDTO.getTeacherName());
              teacher.setSpecialization(groupDTO.getSpecialization());
              Teacher saveTeacher = teacherRepository.save(teacher);
              List<Teacher> teachers = new ArrayList<>();
              teachers.add(teacher);

              Groups group = new Groups();
                group.setGroupName(groupDTO.getGroupName());
                group.setSubjects(subjects);
                group.setStudents(students);
                group.setTeachers(teachers);
                groupsRepository.save(group);

              return "Saved Student ";

          }catch (Exception e){

              return " Error save Student";

          }

        }


        @RequestMapping(value = "/groups/{id}" , method = RequestMethod.PUT)
        public String updateSubject(@PathVariable Integer id , @RequestBody Groups group){
            Optional<Groups> optionalGroups = groupsRepository.findById(id);
            if (optionalGroups.isPresent() ){
                Groups groups = optionalGroups.get();
                if (!group.getGroupName().isEmpty())
                    groups.setGroupName(group.getGroupName());
                return "Update group";
            }
            return "Group not found";
        }

    }
