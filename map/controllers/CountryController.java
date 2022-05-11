package uz.avazbek.map.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.avazbek.map.dto.CountryDTO;
import uz.avazbek.map.entity.Address;
import uz.avazbek.map.entity.Country;
import uz.avazbek.map.entity.District;
import uz.avazbek.map.entity.Region;
import uz.avazbek.map.repository.AddressRepository;
import uz.avazbek.map.repository.CountryRepository;
import uz.avazbek.map.repository.DistrictRepository;
import uz.avazbek.map.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class CountryController {

    @Autowired
    private CountryRepository countryRepository ;

    @Autowired
    private AddressRepository addressRepository ;

    @Autowired
    private DistrictRepository districtRepository ;

    @Autowired
    private RegionRepository regionRepository ;

    @RequestMapping( value = "/country" , method = RequestMethod.GET)
    public List<Country> getCountrys(){

        return countryRepository.findAll();

    }

    @RequestMapping( value = "/country/{id}" , method = RequestMethod.GET)
    public Country getCountrys(@PathVariable Integer id){

        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()){
            return optionalCountry.get() ;
        }

        return new Country();

    }

    @RequestMapping( value = "/country/{id}" , method = RequestMethod.DELETE)
    public String deleteCountry(@PathVariable Integer id){
        try {
            countryRepository.deleteById(id);
            return "Success delete ";
        }catch (Exception e){
            return "Error deleted";
        }

    }

    @RequestMapping( value = "/country" , method = RequestMethod.POST)
    public String savedCountry(@RequestBody CountryDTO countryDTO) {
        try {
            List<Address> addresses = new ArrayList<>();
            List<District> districts = new ArrayList<>();
            List<Region> regions = new ArrayList<>();
            Address address = new Address();
            address.setHomeNumber(countryDTO.getHomeNumber());
            address.setStreetName(countryDTO.getStreetName());
            Address savedAddress = addressRepository.save(address);
            addresses.add(savedAddress);
            District district = new District();
            district.setDistrictName(countryDTO.getDistrictName());
            district.setAddresses(addresses);
            District savedDistrict = districtRepository.save(district);
            districts.add(savedDistrict);
            Region region = new Region();
            region.setRegionName(countryDTO.getRegionName());
            region.setDistricts(districts);
            Region region1 = regionRepository.save(region);
            regions.add(region1);
            Country country = new Country();
            country.setCountryName(countryDTO.getCountryName());
            country.setRegions(regions);
            countryRepository.save(country);
            return "Save Country ";

        } catch (Exception e) {

            return "Error save Country ";
        }
    }

    @RequestMapping( value = "/country/{id}" , method = RequestMethod.PUT)
    public String updateCountry(@PathVariable Integer id , @RequestBody Country country) {
        try {
            Country country1 = countryRepository.getById(id);
            country1.setCountryName(country.getCountryName());
            countryRepository.save(country1);
            return "update country ";

        } catch (Exception e) {

            return "Error update country ";
        }
    }

}
