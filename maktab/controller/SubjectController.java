    package uz.maktab.maktab.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.maktab.maktab.model.Subject;
    import uz.maktab.maktab.repository.SubjectRepository;

    import java.util.List;
    import java.util.Optional;

    @RestController
    public class SubjectController {

        @Autowired
        private SubjectRepository subjectRepository ;

        @RequestMapping(value = "/subject" , method = RequestMethod.GET)
        public List<Subject> getSubjects(){
            return subjectRepository.findAll();
        }

        @RequestMapping(value = "/subject/{id}" , method = RequestMethod.GET)
        public Subject getSubject(@PathVariable Long id){
            Optional<Subject> subject = subjectRepository.findById(id);
            if (subject.isPresent())
            {
               return subject.get();
            }
            return new Subject();
        }

        @RequestMapping(value = "/subject" , method = RequestMethod.POST)
        public String saveSubject(@RequestBody Subject subject) {
            try {
                if (!subject.getName().isEmpty())
                    subjectRepository.save(subject);
                return "Saved Subject";
            } catch (Exception e) {
                return "Error Saved Subject";
            }
        }

        @RequestMapping(value = "/subject/{id}" , method = RequestMethod.PUT)
        public String updateSubject(@PathVariable Long id , @RequestBody Subject subject){
            try {
                Optional<Subject> subjectOptional = subjectRepository.findById(id);
                if (subjectOptional.isPresent()) {
                    Subject subject1 = subjectOptional.get();
                    if (!subject.getName().isEmpty())
                        subject1.setName(subject.getName());
                }
                    return "Updated";

            }catch (Exception e) {
                return "Error ";
            }
        }

        @RequestMapping(value = "/subject/{id}" , method = RequestMethod.DELETE)
        public String deleteSubject(@PathVariable Long id){
            try {
                subjectRepository.deleteById(id);
                return "Deleted Subject ";
            }catch (Exception e) {
                return "Error Deleted  ";
            }
        }

    }
