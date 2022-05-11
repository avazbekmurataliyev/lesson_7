    package uz.avazbek.map.controllers;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import uz.avazbek.map.dto.RegionDTO;
    import uz.avazbek.map.entity.Address;
    import uz.avazbek.map.entity.District;
    import uz.avazbek.map.entity.Region;
    import uz.avazbek.map.repository.AddressRepository;
    import uz.avazbek.map.repository.DistrictRepository;
    import uz.avazbek.map.repository.RegionRepository;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @RestController

    public class RegionController {

        @Autowired
        private RegionRepository regionRepository ;

        @Autowired
        private DistrictRepository districtRepository ;

        @Autowired
        private AddressRepository addressRepository;

        //get all region
    @RequestMapping( value = "/region" , method = RequestMethod.GET)
        public List<Region> getRegions() {

        return regionRepository.findAll() ;

    }
        // get region
        @RequestMapping( value = "/region/{id}" , method = RequestMethod.GET)
        public Region getRegion(@PathVariable Integer id) {

            Optional<Region> optionalRegion = regionRepository.findById(id);
            if (optionalRegion.isPresent())
                return optionalRegion.get();

            return new Region();

        }

        @RequestMapping( value = "/region/{id}" , method = RequestMethod.DELETE)
        public  String deleteRegion(@PathVariable Integer id){

            Optional<Region> optionalRegion = regionRepository.findById(id);
            if (optionalRegion.isPresent())
            {
                regionRepository.deleteById(id);
                return " Success ";
            }

            return "Error delete Region ";
        }


        @RequestMapping( value = "/region" , method = RequestMethod.POST)
        public String savedRegion(@RequestBody RegionDTO regionDTO) {
            try {
                List<Address> addresses = new ArrayList<>();
                List<District> districts = new ArrayList<>();
                Address address = new Address();
                address.setHomeNumber(regionDTO.getHomeNumber());
                address.setStreetName(regionDTO.getStreetName());
                Address savedAddress = addressRepository.save(address);
                addresses.add(savedAddress);
                District district = new District();
                district.setDistrictName(regionDTO.getDistrictName());
                district.setAddresses(addresses);
                District savedDistrict = districtRepository.save(district);
                districts.add(savedDistrict);
                Region region = new Region();
                region.setRegionName(regionDTO.getRegionName());
                region.setDistricts(districts);
                regionRepository.save(region);
                return "Save region ";

            } catch (Exception e) {

                return "Error save region ";
            }
        }

        @RequestMapping( value = "/region/{id}" , method = RequestMethod.POST)
        public String updatedRegion(@PathVariable Integer id ,@RequestBody Region region) {
            try {

                region.setRegionName(region.getRegionName());
                regionRepository.save(region);
                return "update region ";

            } catch (Exception e) {

                return "Error update region ";
            }
        }

    }
