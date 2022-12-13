package org.blokaj.graphql.mappers;

import org.blokaj.graphql.generated.types.PersonInfoInput;
import org.blokaj.graphql.generated.types.PersonInfoType;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface PersonMapper {

    PersonInfoType mapPersonInfoType(PersonInfoInput personInfoInput);
}
