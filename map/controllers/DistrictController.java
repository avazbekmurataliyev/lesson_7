    package uz.avazbek.map.controllers;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.avazbek.map.dto.DistrictDTO;
    import uz.avazbek.map.entity.Address;
    import uz.avazbek.map.entity.District;
    import uz.avazbek.map.repository.AddressRepository;
    import uz.avazbek.map.repository.DistrictRepository;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @RestController
    public class DistrictController {

        @Autowired
        private DistrictRepository districtRepository;

        @Autowired
        private AddressRepository addressRepository;
        // get all district
        @RequestMapping(value = "/district" , method = RequestMethod.GET)
        public List<District> getDistricts(){
            return districtRepository.findAll();
        }

        //get district
        @RequestMapping(value = "/district/{id}" , method = RequestMethod.GET)
        public District getDistrict(@PathVariable Integer id){
            Optional<District> optionalDistrict = districtRepository.findById(id);
            if (optionalDistrict.isPresent())
                return optionalDistrict.get();

            return new District();
        }

        //delete district
        @RequestMapping(value = "/district/{id}" , method = RequestMethod.DELETE)
        public String deleteDistrict(@PathVariable Integer id){
            Optional<District> optionalDistrict = districtRepository.findById(id);
            if (optionalDistrict.isPresent()){
                districtRepository.deleteById(id);
                return "Success deleted";
            }


            return "Error deleted";
        }


        @RequestMapping(value = "/district" , method = RequestMethod.POST)
        public String saveDistrict(@RequestBody DistrictDTO districtDTO){

            try{
                Address address = new Address();
                address.setStreetName(districtDTO.getStreetName());
                address.setHomeNumber(districtDTO.getHomeNumber());
                Address savedAddress = addressRepository.save(address);
                List<Address> addresses = new ArrayList<>();
                addresses.add(savedAddress);
                District district = new District();
                district.setDistrictName(districtDTO.getDistrictName());
                district.setAddresses(addresses);
                districtRepository.save(district);
                return "Success ";
            }catch (Exception e){
                return "Error save";
            }

        }

        @RequestMapping(value = "/district/{id}" , method = RequestMethod.DELETE)
        public String updateDistrict(@PathVariable Integer id , @RequestBody District district){
            Optional<District> optionalDistrict = districtRepository.findById(id);
            if (optionalDistrict.isPresent()){
                District district1 = optionalDistrict.get();
                district1.setDistrictName(district.getDistrictName());
                districtRepository.save(district1);
                return "Success update";
            }


            return "Error update";
        }


    }
