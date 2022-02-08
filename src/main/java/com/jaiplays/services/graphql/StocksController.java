package com.jaiplays.services.graphql;

import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class StocksController {

    @Autowired
    private GraphqlService  graphqlService;

    @PostMapping("/stockdata")
    public String getStockDetails(@RequestBody String query){
        ExecutionResult executionResult = graphqlService.initializeGraphQL().execute(query);
        Map<String,String> obj = executionResult.getData();
        return new JSONObject(obj).toString();
    }
}
