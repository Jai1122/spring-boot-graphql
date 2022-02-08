package com.jaiplays.services.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StocksDataFetcher implements DataFetcher<List<StockDetails>> {
    @Override
    public List<StockDetails> get(DataFetchingEnvironment dataFetchingEnvironment) {
        StockDetails stockDetails = StockDetails.builder()
                                        .ticker("AAPL")
                                        .stockValue("127.14")
                                        .volume("111.6M")
                                        .PE_Ratio("38.76")
                                        .EPS("3.28")
                                        .dividend("0.82 (0.64)")
                                        .marketCap("2.14T")
                                        .previousOpen("128.71")
                                        .previousClose("128.94")
                                        .build();
        List<StockDetails> stockDetailsList = new ArrayList<>();
        stockDetailsList.add(stockDetails);
        return stockDetailsList;
    }
}
