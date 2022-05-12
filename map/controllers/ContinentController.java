    package uz.avazbek.map.controllers;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.avazbek.map.dto.ContinentDTO;
    import uz.avazbek.map.entity.*;
    import uz.avazbek.map.repository.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @RestController
    public class ContinentController {

        @Autowired
        private ContinentRepository continentRepository;
        @Autowired
        private CountryRepository countryRepository ;

        @Autowired
        private AddressRepository addressRepository ;

        @Autowired
        private DistrictRepository districtRepository ;

        @Autowired
        private RegionRepository regionRepository ;

        @RequestMapping( value = "/continent" , method = RequestMethod.GET)
        public List<Continent> getContinents(){

            return continentRepository.findAll();

        }

        @RequestMapping( value = "/continent/{id}" , method = RequestMethod.GET)
        public Continent getContinent(@PathVariable Integer id){

            Optional<Continent> optionalContinent = continentRepository.findById(id);
            if (optionalContinent.isPresent()){
                return optionalContinent.get() ;
            }

            return new Continent();

        }

        @RequestMapping( value = "/continent/{id}" , method = RequestMethod.DELETE)
        public String deleteContinent(@PathVariable Integer id){
            try {
                continentRepository.deleteById(id);
                return "Success delete ";
            }catch (Exception e){
                return "Error deleted";
            }

        }

        @RequestMapping( value = "/continent" , method = RequestMethod.POST)
        public String savedContinent(@RequestBody ContinentDTO continentDTO) {
            try {
                List<Address> addresses = new ArrayList<>();
                List<District> districts = new ArrayList<>();
                List<Region> regions = new ArrayList<>();
                List<Country> countrys = new ArrayList<>();
                Address address = new Address();
                address.setHomeNumber(continentDTO.getHomeNumber());
                address.setStreetName(continentDTO.getStreetName());
                Address savedAddress = addressRepository.save(address);
                addresses.add(savedAddress);
                District district = new District();
                district.setDistrictName(continentDTO.getDistrictName());
                district.setAddresses(addresses);
                District savedDistrict = districtRepository.save(district);
                districts.add(savedDistrict);
                Region region = new Region();
                region.setRegionName(continentDTO.getRegionName());
                region.setDistricts(districts);
                Region region1 = regionRepository.save(region);
                regions.add(region1);
                Country country = new Country();
                country.setCountryName(continentDTO.getCountryName());
                country.setRegions(regions);
                Country country1 = countryRepository.save(country);
                countrys.add(country1);
                Continent continent = new Continent();
                continent.setName(continentDTO.getContinentName());
                continent.setCountries(countrys);
                return "Save Continent ";

            } catch (Exception e) {

                return "Error save Continent ";
            }
        }

        @RequestMapping( value = "/country/{id}" , method = RequestMethod.PUT)
        public String updateCountry(@PathVariable Integer id , @RequestBody ContinentDTO continentDTO) {
            try {
                Continent continent = continentRepository.getById(id);
                continent.setName(continentDTO.getContinentName());
                continentRepository.save(continent);
                return "update continent ";

            } catch (Exception e) {

                return "Error update continent ";
            }
        }










    }
