package org.blokaj.graphql.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blokaj.graphql.generated.DgsConstants;
import org.blokaj.graphql.generated.types.PersonInfoInput;
import org.blokaj.graphql.generated.types.PersonUnion;
import org.blokaj.graphql.generated.types.PersonsUnion;
import org.blokaj.graphql.generated.types.WherePerson;
import org.blokaj.graphql.services.PersonService;

@Slf4j
@DgsComponent
@RequiredArgsConstructor
public class PersonFetcher {

    @NonNull
    private final PersonService personService;

    @DgsMutation(field = DgsConstants.MUTATION.AddPerson)
    public PersonUnion addPerson(@InputArgument PersonInfoInput input) {
        log.info("Add new person in the list.");

        return personService.addPerson(input);
    }

    @DgsQuery(field = DgsConstants.QUERY.FindPersons)
    public PersonsUnion findPersons(@InputArgument WherePerson where) {
        log.info("Find persons from list.");

        return personService.findPersons(where);
    }
}
