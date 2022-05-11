package uz.maktab.maktab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.maktab.maktab.model.Address;
import uz.maktab.maktab.model.School;
import uz.maktab.maktab.payload.SchoolPayload;
import uz.maktab.maktab.repository.AddressRepository;
import uz.maktab.maktab.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/main")
public class SchollController {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private AddressRepository addressRepository ;

    // Get all schools
    @RequestMapping(value = "/school" , method = RequestMethod.GET)
    public List<School> getSchools(){
        List<School> schoolList = schoolRepository.findAll();
        return schoolList ;
    }
    // Save school and adress
    @RequestMapping(value = "/school" ,method = RequestMethod.POST)
    public String saveSchool(@RequestBody SchoolPayload schoolPayload){
    try {
        Address address = new Address();
        address.setCity(schoolPayload.getCity());
        address.setDistrict(schoolPayload.getDistrict());
        address.setStreet(schoolPayload.getStreet());
        Address savedAddress = addressRepository.save(address);

        School school = new School();

        school.setName(schoolPayload.getName());
        school.setAddress(savedAddress);
        schoolRepository.save(school);
        return "Saved" ;

    }catch (Exception e)
    {
        e.printStackTrace();
        return "Error";
    }

    }
    // Update school
    @RequestMapping(value = "/school/{id}",method = RequestMethod.PUT)
    public String updateSchool(@PathVariable Long id , @RequestBody SchoolPayload schoolPayload) {
        try {
            School school = schoolRepository.getById(id);
            school.setName(schoolPayload.getName());
            schoolRepository.save(school);
            return "Updated ";
        } catch (Exception e) {
            return "Error update ";
        }
    }

    // Get one school
    @RequestMapping(value = "/school/{id}" , method = RequestMethod.GET)
    public School getSchool(@PathVariable Long id){
        try {
            Optional<School> school = schoolRepository.findById(id);
            if (school.isPresent()){
                return school.get();
            }
            return new School();
        }catch (Exception e){
            return new School();
        }

    }

    // Delete school
    @RequestMapping(value = "/school/{id}",method = RequestMethod.DELETE)
    public String deleteSchool(@PathVariable Long id){
        try {
            schoolRepository.deleteById(id);

            return " Deleted ";
        }catch (Exception e) {
            return "Error deleted";
        }
    }


}
