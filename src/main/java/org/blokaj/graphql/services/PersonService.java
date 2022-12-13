package org.blokaj.graphql.services;

import org.blokaj.graphql.generated.types.PersonInfoInput;
import org.blokaj.graphql.generated.types.PersonUnion;
import org.blokaj.graphql.generated.types.PersonsUnion;
import org.blokaj.graphql.generated.types.WherePerson;

public interface PersonService {

    PersonUnion addPerson(PersonInfoInput input);

    PersonsUnion findPersons(WherePerson where);
}
