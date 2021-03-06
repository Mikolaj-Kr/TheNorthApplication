package theNorthApplication.app.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theNorthApplication.app.dto.StoreDto;
import theNorthApplication.app.mapper.SearchResultsDtoMapper;
import theNorthApplication.app.parser.ShopsSearcherParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearcherService {

    private final ShopsSearcherParser shopsSearcherParser;
    private final SearchResultsDtoMapper searchResultsDtoMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SearcherService(ShopsSearcherParser shopsSearcherParser, SearchResultsDtoMapper searchResultsDtoMapper) {
        this.shopsSearcherParser = shopsSearcherParser;
        this.searchResultsDtoMapper = searchResultsDtoMapper;
    }


    public List<StoreDto> getStoreDtoList(String shop, String town) throws IOException, UnirestException, InterruptedException {
        List<StoreDto> storesDto = new ArrayList<>();

        shopsSearcherParser.parseSearch(shop, town).getResultsList().forEach(results -> {
            storesDto.add(searchResultsDtoMapper.mapSearchResultToDto(results));
        });

        logger.info("map search results to StoreDtoList");
        return storesDto;
    }

    public List<StoreDto> getStoresByCoordinatesAndRadius(String lat, String lng, String radius) throws InterruptedException, UnirestException, IOException {
        List<StoreDto> storeDtos = new ArrayList<>();
        shopsSearcherParser.parseSearchByCoordinatesAndRadiusByM(lat, lng, radius).getResultsList().forEach(results ->
                storeDtos.add(searchResultsDtoMapper.nearByMapToDto(results)));
        return storeDtos;
    }
}
