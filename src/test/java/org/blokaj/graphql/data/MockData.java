package org.blokaj.graphql.data;

import org.blokaj.graphql.generated.types.PersonInfoInput;
import org.blokaj.graphql.generated.types.WherePerson;

public class MockData {

    public static PersonInfoInput mockPersonInfoInput() {
        return PersonInfoInput.newBuilder()
                .age(35)
                .firstName("Berahim")
                .lastName("Lokaj")
                .email("blokaj@examplce.org")
                .phoneNumber("+383451000001")
                .build();
    }

    public static WherePerson mockWherePerson() {
        return WherePerson.newBuilder()
                .email("blokaj@examplce.org")
                .phoneNumber("+383451000001")
                .build();
    }
}
