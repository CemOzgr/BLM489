package com.vernum.churnService.resources;

import com.vernum.churnService.dao.ChurnDataAccess;
import com.vernum.churnService.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/churn")
public class ChurnResource {

    private final ChurnDataAccess churnDataAccess;

    @Autowired
    public ChurnResource(@Qualifier("postgres") ChurnDataAccess churnDataAccess) {
        this.churnDataAccess = churnDataAccess;
    }

    @GetMapping("/customers/genders")
    public ResponseModel customerGenderProfile(@RequestParam(required = false) String city) {
        return churnDataAccess.customerGenderProfile(city);
    }

    @GetMapping({"/transactions/years","/transactions/years/{year}"})
    public ResponseModel transactions(@PathVariable(required = false) String year) {
        return churnDataAccess.transactions(year);
    }

    @GetMapping("/transactions/recent")
    public ResponseModel recentTransactions(@RequestParam String interval,
                                            @RequestParam(defaultValue = "0") String detailed) {
        return churnDataAccess.recentTransactions(interval, detailed);
    }
}
