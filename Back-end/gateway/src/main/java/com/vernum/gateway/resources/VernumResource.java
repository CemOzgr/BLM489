package com.vernum.gateway.resources;

import com.vernum.gateway.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/")
public class VernumResource {

    RestTemplate restTemplate;

    @Autowired
    public VernumResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public int logged() {
        return HttpStatus.NO_CONTENT.value();
    }

    @GetMapping("api/games/sales/{platform}/best-sellers")
    public ResponseModel bestSellers(@PathVariable String platform,
                                     @RequestParam(defaultValue = "global") String region) {
        String uri = "http://games-service/games/sales/{platform}/best-sellers?region={region}";

        return restTemplate.getForObject(uri, ResponseModel.class, platform, region);

    }

    @GetMapping("api/games/sales/{platform}/top-publishers")
    private ResponseModel bestPublishers(@PathVariable String platform) {
        String uri = "http://games-service/games/sales/{platform}/top-publishers";

        return restTemplate.getForObject(uri, ResponseModel.class, platform);
    }

    @GetMapping("api/games/sales/{platform}/years")
    private ResponseModel yearSales(@PathVariable String platform,
                                    @RequestParam(required = false) String genre) {
        String uri = "http://games-service/games/sales/{platform}/years?genre={genre}";

        return restTemplate.getForObject(uri, ResponseModel.class, platform, genre);
    }

    @GetMapping("api/games/sales/all/top-publishers/{publisher}")
    private ResponseModel publisherRevenue(@PathVariable String publisher) {
        String uri = "http://games-service/games/sales/all/top-publishers/{publisher}";

        return restTemplate.getForObject(uri, ResponseModel.class, publisher);
    }

    @GetMapping("api/games/market/{platform}/genres")
    public ResponseModel genres(@PathVariable String platform) {
        String uri = "http://games-service/games/market/{platform}/genres";

        return restTemplate.getForObject(uri, ResponseModel.class, platform);
    }

    @GetMapping("api/churn/customers/genders")
    public ResponseModel customerGenderProfile(@RequestParam(required = false) String city) {

        String uri = "http://churn-service/churn/customers/genders";
        if(city != null) {
            uri = uri.concat("?city={city}");
        }

        return restTemplate.getForObject(uri, ResponseModel.class, city);
    }

    @GetMapping({"api/churn/transactions/years","api/churn/transactions/years/{year}"})
    public ResponseModel transactions(@PathVariable(required = false) String year) {
        String uri = "http://churn-service/churn/transactions/years";
        if(year != null) {
            uri = uri.concat("/{year}");
        }

        return restTemplate.getForObject(uri, ResponseModel.class, year);
    }

    @GetMapping("api/churn/transactions/recent")
    public ResponseModel recentTransactions(@RequestParam String interval,
                                            @RequestParam(defaultValue = "0") String detailed) {
        String uri = "http://churn-service/churn/transactions/recent?" +
                "interval={interval}&detailed={detailed}";
        return restTemplate.getForObject(uri, ResponseModel.class, interval, detailed);
    }

}
