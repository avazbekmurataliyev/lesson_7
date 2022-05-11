    package uz.maktab.maktab.controller;

    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.maktab.maktab.model.*;
    import uz.maktab.maktab.payload.GroupPayload;
    import uz.maktab.maktab.repository.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @RestController

    public class GroupController {

        @Autowired
        private GroupsRepository groupsRepository ;

        @Autowired
        private AddressRepository addressRepository ;

        @Autowired
        private TeacherRepository teacherRepository ;

        @Autowired
        private MarkRepository markRepository ;

        @Autowired
        private StudentRepository studentRepository ;
        //Get all Group
        @RequestMapping(value = "/group" , method = RequestMethod.GET)
        public List<Groups> getGroups(){
            return groupsRepository.findAll();
        }

        // Get group

        @RequestMapping(value = "/group/{id}" , method = RequestMethod.GET)
        public Groups getGroup(@PathVariable Long id){
            Optional<Groups> group = groupsRepository.findById(id);
            if (group.isPresent()){
                return group.get();
            }
            return new Groups();
        }


        //Deleted group
        @RequestMapping(value = "/group/{id}" , method = RequestMethod.DELETE)
        public String deleteGroup(@PathVariable Long id ){
            try {
                groupsRepository.deleteById(id);
                return "Deleted succesfull";

            }catch (Exception e){
                return "Error";
            }
        }

        // Save group
        @RequestMapping(value = "/group" , method = RequestMethod.POST)
        public String saveGroup(@RequestBody GroupPayload groupPayload){
            try{
                Address address = new Address();
                address.setCity(groupPayload.getCity());
                address.setDistrict(groupPayload.getDistrict());
                address.setStreet(groupPayload.getStreet());
                Address savedAddress = addressRepository.save(address);
                Teacher teacher = new Teacher();
                teacher.setFan(groupPayload.getFan());
                teacher.setFullName(groupPayload.getFullName());
                teacher.setPhoneNumber(groupPayload.getPhoneNumber());
                Teacher savedTeacher = teacherRepository.save(teacher);
                List<Teacher> teacherList =new ArrayList<>();
                teacherList.add(savedTeacher);
                Mark mark = new Mark();
                mark.setStudentMark(groupPayload.getStudentMark());
                Mark savedMark = markRepository.save(mark);
                Student student = new Student();
                student.setAddress(savedAddress);
                student.setFirstName(groupPayload.getFirstName());
                student.setLastName(groupPayload.getLastName());
                student.setMark(savedMark);
                Student savedStudent = studentRepository.save(student);
                List<Student> studentList = new ArrayList<>();
                studentList.add(savedStudent);
                Groups group = new Groups();
                group.setStudents(studentList);
                group.setTeacherList(teacherList);
                group.setName(groupPayload.getName());
                return "Save saccessful ";
            }catch (Exception e){
                return "Error group";
            }

        }

    }
