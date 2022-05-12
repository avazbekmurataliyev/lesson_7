    package uz.avazbek.map.university.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.avazbek.map.university.entity.Subject;
    import uz.avazbek.map.university.repository.SubjectRepository;

    import java.util.List;
    import java.util.Optional;

    @RestController

    public class SubjectController {

        @Autowired
        private SubjectRepository subjectRepository ;

        //get All Subject
        @RequestMapping(value = "/subject" , method = RequestMethod.GET)
        public List<Subject> getSubjects(){
            return subjectRepository.findAll() ;
        }

        //get Subject
        @RequestMapping(value = "/subject/{id}" , method = RequestMethod.GET)
        public Subject getSubject(@PathVariable Integer id){
            Optional<Subject> optionalSubject = subjectRepository.findById(id);
            if (optionalSubject.isPresent()){
                return optionalSubject.get();
            }
            return new Subject();
        }

        //delete Subject
        @RequestMapping(value = "/subject/{id}" , method = RequestMethod.DELETE)
        public String deleteSubject(@PathVariable Integer id){
            Optional<Subject> optionalSubject = subjectRepository.findById(id);
            if (optionalSubject.isPresent()){
                subjectRepository.deleteById(id);
                return "Deleted subject";
            }
            return "Subject not found";
        }

        //save Subject
        @RequestMapping(value = "/subject" , method = RequestMethod.POST)
        public String saveSubject( @RequestBody Subject subject){
          try{
              subjectRepository.save(subject);
              return "Saved subject ";
          }catch (Exception e){
              return " Error save subject";
          }

        }

        //Update Subject
        @RequestMapping(value = "/subject/{id}" , method = RequestMethod.PUT)
        public String updateSubject(@PathVariable Integer id , @RequestBody Subject subject){
            Optional<Subject> optionalSubject = subjectRepository.findById(id);
            if (optionalSubject.isPresent() && !subject.getSubjectName().isEmpty()){
                Subject subject1 = optionalSubject.get();
                subject1.setSubjectName(subject.getSubjectName());
                return "Deleted subject";
            }
            return "Subject not found";
        }

    }
