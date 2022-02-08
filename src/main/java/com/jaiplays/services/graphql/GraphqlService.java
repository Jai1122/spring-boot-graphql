package com.jaiplays.services.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class GraphqlService {
    private GraphQL graphQL;

    @Value("schema.graphql")
    private ClassPathResource classPathLoader;

    @Autowired
    private StocksDataFetcher stocksDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        InputStream inputStream = classPathLoader.getInputStream();
        Reader targetReader = new InputStreamReader(inputStream);

        TypeDefinitionRegistry typeDefinitionRegistry   =   new SchemaParser().parse(targetReader);
        RuntimeWiring runtimeWiring =   buildRunTimeWiring();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRunTimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring->typeWiring.dataFetcher("stocks",stocksDataFetcher))
                .build();
    }

    public GraphQL initializeGraphQL(){
        return graphQL;
    }

}
