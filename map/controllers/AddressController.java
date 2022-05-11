package uz.avazbek.map.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.avazbek.map.entity.Address;
import uz.avazbek.map.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    //Get all Address
    @Autowired
   private AddressRepository addressRepository;

    @RequestMapping(value = "/address" , method = RequestMethod.GET)
    public List<Address> getAllAddress(){

        return addressRepository.findAll();
    }

    //get one address
    @RequestMapping(value = "/address/{id}" , method = RequestMethod.GET)
    public Address getAddress(@PathVariable Long id) {
        try {
            Optional<Address> address = addressRepository.findById(id);
            if (address.isPresent())
                return address.get();

            return new Address();
        } catch (Exception e) {
            return new Address();
        }
    }

    // Delete address

    @RequestMapping(value = "/address/{id}" , method = RequestMethod.DELETE)
    public  String deleteAddress(@PathVariable Long id) {

        try {
            addressRepository.deleteById(id);
            return "deleted Address";
        } catch (Exception e) {
            return "Error address deleted";
        }
    }

    // Save address

    @RequestMapping(value = "/address" , method = RequestMethod.POST)
    public String saveAddress(@RequestBody Address address) {
        try {
            Address savedAddress = addressRepository.save(address);
            return "Saved address ";
        } catch (Exception e) {
            return "Error saved address";
        }
    }

    // update address
    @RequestMapping(value = "/address/{id}" , method = RequestMethod.PUT)
    public String updateAddress(@PathVariable Long id , @RequestBody Address address) {

        try {
            Address savedAddress = addressRepository.getById(id);
            if (!address.getHomeNumber().isEmpty()) savedAddress.setHomeNumber(address.getHomeNumber());
            if (!address.getStreetName().isEmpty())savedAddress.setStreetName(address.getStreetName());
             addressRepository.save(savedAddress);
            return "Updated address ";
        } catch (Exception e) {
            return "Error update address";
        }
    }

}
