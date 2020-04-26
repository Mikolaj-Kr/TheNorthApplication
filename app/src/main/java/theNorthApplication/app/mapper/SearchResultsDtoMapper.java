package theNorthApplication.app.mapper;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;
import theNorthApplication.app.api.searcherClasses.Results;
import theNorthApplication.app.dto.StoreDto;
import theNorthApplication.app.entity.Store;


@Component
public class SearchResultsDtoMapper {

    public StoreDto mapSearchResultToDto(Results results) {

        StoreDto storeDto = new StoreDto();
        String[] address = results.getAddress().split(",");

        String country = null;
        String street = null;
        String town = null;
        if (address.length>2) {
            country = address[2];
            street = address[0];
            town = getTownFormAddress(address[1]);
        } else {
            country = address[1];
            street = "";
            town = getTownFormAddress(address[0]);
        }
        String lat = results.getGeometry().getLocation().getLat();
        String lng = results.getGeometry().getLocation().getLng();

        storeDto.setId(results.getId());
        storeDto.setName(results.getName());
        storeDto.setCountry(country);
        storeDto.setStreet(street);
        storeDto.setTown(town);
        storeDto.setLat(lat);
        storeDto.setLng(lng);
        return storeDto;
    }

    private String getTownFormAddress(String postalCodeAndTown) {
        String[] separatedPostalCodeAndTown = postalCodeAndTown.stripLeading().split(" ");
        if (separatedPostalCodeAndTown.length>1) {
            return separatedPostalCodeAndTown[1];
        } else {
            return separatedPostalCodeAndTown[0];
        }
    }

    public StoreDto nearByMapToDto(Results results){
        StoreDto storeDto = new StoreDto();
        storeDto.setId(results.getId());
        storeDto.setName(results.getName());
        storeDto.setLat(results.getGeometry().getLocation().getLat());
        storeDto.setLng(results.getGeometry().getLocation().getLng());
        return storeDto;
    }
}
