package com.vernum.gateway.resources;

import com.vernum.gateway.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class VernumResource {

    RestTemplate restTemplate;

    @Autowired
    public VernumResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("games/sales/{platform}/best-sellers")
    public ResponseModel bestSellers(@PathVariable String platform,
                                     @RequestParam(defaultValue = "global") String region) {
        String uri = "http://games-service/games/sales/{platform}/best-sellers?region={region}";

        return restTemplate.getForObject(uri, ResponseModel.class, platform, region);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("games/sales/{platform}/top-publishers")
    private ResponseModel bestPublishers(@PathVariable String platform) {
        String uri = "http://games-service/games/sales/{platform}/top-publishers";

        return restTemplate.getForObject(uri, ResponseModel.class, platform);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("games/sales/{platform}/years")
    private ResponseModel yearSales(@PathVariable String platform,
                                    @RequestParam(required = false) String genre) {
        String uri = "http://games-service/games/sales/{platform}/years?genre={genre}";

        return restTemplate.getForObject(uri, ResponseModel.class, platform, genre);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("games/sales/all/top-publishers/{publisher}")
    private ResponseModel publisherRevenue(@PathVariable String publisher) {
        String uri = "http://games-service/games/sales/top-publisher/{publisher}";

        return restTemplate.getForObject(uri, ResponseModel.class, publisher);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("games/market/{platform}/genres")
    public ResponseModel genres(@PathVariable String platform) {
        String uri = "http://games-service/games/sales/{platform}/genres";

        return restTemplate.getForObject(uri, ResponseModel.class, platform);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("churn/customers/genders")
    public ResponseModel customerGenderProfile(@RequestParam(required = false) String city) {

        String uri = "http://churn-service/churn/customers/genders";
        if(city != null) {
            uri = uri.concat("?city={city}");
        }

        return restTemplate.getForObject(uri, ResponseModel.class, city);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping({"churn/transactions/years","churn/transactions/years/{year}"})
    public ResponseModel transactions(@PathVariable(required = false) String year) {
        String uri = "http://churn-service/churn/transactions/years";
        if(year != null) {
            uri = uri.concat("/{year}");
        }

        return restTemplate.getForObject(uri, ResponseModel.class, year);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("churn/transactions/recent")
    public ResponseModel recentTransactions(@RequestParam String interval,
                                            @RequestParam(defaultValue = "0") String detailed) {
        String uri = "http://churn-service/churn/transactions/recent?" +
                "interval={interval}&detailed={detailed}";
        return restTemplate.getForObject(uri, ResponseModel.class, interval, detailed);
    }

}
