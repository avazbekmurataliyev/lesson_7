package uz.maktab.maktab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.maktab.maktab.model.Address;
import uz.maktab.maktab.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository ;
// get All address
    @RequestMapping(value = "/address" ,method = RequestMethod.GET)
    public List<Address> getAddresses(){

        return addressRepository.findAll();
    }

    @RequestMapping(value = "/address/{id}" ,method = RequestMethod.GET)
    public Address getAddress(@PathVariable Long id){
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()){
            return address.get();
        }
        return new Address();
    }

    @RequestMapping(value = "/address" ,method = RequestMethod.POST)
    public String saveAddress(@RequestBody Address address){
        try {
            if (!address.equals(null)) {
                addressRepository.save(address);
            }
                return "save Address";

        }catch (Exception e) {
            return "Error save Address";
        }
    }

    @RequestMapping(value = "/address/{id}" ,method = RequestMethod.PUT)
    public String updateAddress(@PathVariable Long id , @RequestBody Address address){

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            Address address1 = optionalAddress.get();
           if(!address.getCity().equals(null))address1.setCity(address.getCity());
            if(!address.getDistrict().equals(null))address1.setDistrict(address.getDistrict());
            if(!address.getStreet().equals(null))address1.setStreet(address.getStreet());
            addressRepository.save(address1);
            return "Updated ";
        }
        return "Error update address";
    }

    @RequestMapping(value = "/address/{id}" ,method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable Long id){
        try {
            addressRepository.deleteById(id);
            return "Deleted Address";
        }catch (Exception e) {

            return "Error deleted Address";
        }
    }

}
