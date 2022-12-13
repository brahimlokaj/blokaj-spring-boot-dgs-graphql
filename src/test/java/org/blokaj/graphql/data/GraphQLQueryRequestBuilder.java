package org.blokaj.graphql.data;

import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.blokaj.graphql.generated.client.AddPersonGraphQLQuery;
import org.blokaj.graphql.generated.client.AddPersonProjectionRoot;
import org.blokaj.graphql.generated.client.FindPersonsGraphQLQuery;
import org.blokaj.graphql.generated.client.FindPersonsProjectionRoot;
import org.blokaj.graphql.generated.types.PersonInfoInput;
import org.blokaj.graphql.generated.types.WherePerson;

public class GraphQLQueryRequestBuilder {

    public static GraphQLQueryRequest addPersonGraphQLQueryRequest(PersonInfoInput input) {
        return new GraphQLQueryRequest(
                AddPersonGraphQLQuery.newRequest()
                        .input(input)
                        .build(),
                new AddPersonProjectionRoot()
                        .onPersonInfoType()
                        .id()
                        .age()
                        .firstName()
                        .lastName()
                        .email()
                        .phoneNumber().getParent()

                        .onGeneralError()
                        .code()
                        .message()
        );
    }

    public static GraphQLQueryRequest findPersonsGraphQLQueryRequest(WherePerson wherePerson) {
        return new GraphQLQueryRequest(
                FindPersonsGraphQLQuery.newRequest()
                        .where(wherePerson)
                        .build(),
                new FindPersonsProjectionRoot()
                        .onInformationOfPersonsResponse()
                        .persons()
                        .id()
                        .age()
                        .firstName()
                        .lastName()
                        .email()
                        .phoneNumber().getRoot()

                        .onGeneralError()
                        .code()
                        .message()
        );
    }
}
