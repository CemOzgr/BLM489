package com.vernum.gamesService.resources;

import com.vernum.gamesService.dao.GamesDataAccess;
import com.vernum.gamesService.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/games")
public class GamesResource {

    private final GamesDataAccess gamesDataAccess;

    @Autowired
    public GamesResource(@Qualifier("postgres") GamesDataAccess gamesDataAccess) {
        this.gamesDataAccess = gamesDataAccess;
    }

    @GetMapping("/sales/{platform}/best-sellers")
    public ResponseModel bestSellers(@PathVariable String platform,
                                     @RequestParam(defaultValue = "global") String region) {
        return gamesDataAccess.bestSellers(platform, region);
    }

    @GetMapping("/sales/{platform}/top-publishers")
    private ResponseModel bestPublishers(@PathVariable String platform) {
        return gamesDataAccess.bestPublishers(platform);
    }

    @GetMapping("/sales/{platform}/years")
    private ResponseModel yearSales(@PathVariable String platform,
                                   @RequestParam(required = false) String genre) {
        return gamesDataAccess.yearSales(platform, genre);
    }

    @GetMapping("/sales/all/top-publishers/{publisher}")
    private ResponseModel publisherRevenue(@PathVariable String publisher) {
        return gamesDataAccess.publisherRevenue(publisher);
    }

    @GetMapping("/market/{platform}/genres")
    public ResponseModel genres(@PathVariable String platform) {
        return gamesDataAccess.genres(platform);
    }

}
