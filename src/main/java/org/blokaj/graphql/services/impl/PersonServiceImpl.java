package org.blokaj.graphql.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.blokaj.graphql.data.PersonData;
import org.blokaj.graphql.generated.types.*;
import org.blokaj.graphql.mappers.PersonMapper;
import org.blokaj.graphql.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @NonNull
    private final PersonMapper personMapper;

    @Override
    public PersonUnion addPerson(PersonInfoInput input) {
        try {
            PersonInfoType personInfoType = personMapper.mapPersonInfoType(input);
            personInfoType.setId(UUID.randomUUID().toString());

            Optional<PersonInfoType> optionalPersonInfoType = PersonData.personInfoTypes.stream()
                    .filter(p -> p.getEmail().equals(input.getEmail()) && p.getPhoneNumber().equals(input.getPhoneNumber()))
                    .findFirst();
            if(optionalPersonInfoType.isPresent()) {
                throw new RuntimeException("Person exist!");
            }

            PersonData.personInfoTypes.add(personInfoType);

            return personInfoType;
        } catch (Exception e) {
            return getGeneralError(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public PersonsUnion findPersons(WherePerson where) {
        try {
            Stream<PersonInfoType> personInfoTypeStream = PersonData.personInfoTypes.stream();
            Stream<PersonInfoType> personInfoTypeStreamFiltered = personInfoTypeStream.filter(person -> {
                if(!ObjectUtils.isEmpty(where.getAge()) && !where.getAge().equals(person.getAge())) {
                    return false;
                }
                if(!ObjectUtils.isEmpty(where.getId()) && !where.getId().equals(person.getId())) {
                    return false;
                }
                if(!ObjectUtils.isEmpty(where.getEmail()) && !where.getEmail().equals(person.getEmail())){
                    return false;
                }
                if(!ObjectUtils.isEmpty(where.getLastName()) && !where.getLastName().equals(person.getLastName())) {
                    return false;
                }
                if(!ObjectUtils.isEmpty(where.getFirstName()) && !where.getFirstName().equals(person.getFirstName())) {
                    return false;
                }
                if(!ObjectUtils.isEmpty(where.getPhoneNumber()) && !where.getPhoneNumber().equals(person.getPhoneNumber())) {
                    return false;
                }

                return true;
            });

            return InformationOfPersonsResponse.newBuilder()
                    .persons(personInfoTypeStreamFiltered.toList())
                    .build();
        } catch (Exception e) {
            return getGeneralError(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }


    private GeneralError getGeneralError(HttpStatus httpStatus, Exception e) {
        return GeneralError.newBuilder()
                .code(httpStatus.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }
}
