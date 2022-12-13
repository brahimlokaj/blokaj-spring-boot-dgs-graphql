package org.blokaj.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.blokaj.graphql.data.GraphQLQueryRequestBuilder;
import org.blokaj.graphql.data.MockData;
import org.blokaj.graphql.generated.types.PersonInfoInput;
import org.blokaj.graphql.generated.types.WherePerson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class PersonFetcherTest {

	@Autowired
	DgsQueryExecutor dgsQueryExecutor;

	private static WherePerson wherePerson;
	private static PersonInfoInput personInfoInput;

	@BeforeAll
	static void setUp() {
		wherePerson = MockData.mockWherePerson();
		personInfoInput = MockData.mockPersonInfoInput();
	}

	@Test
	void addPerson_Success() {
		String typeName = dgsQueryExecutor.executeAndExtractJsonPath(GraphQLQueryRequestBuilder.addPersonGraphQLQueryRequest(personInfoInput).serialize(), "data.AddPerson.__typename");

		assertEquals("PersonInfoType", typeName);
	}

	@Test
	void addPerson_GeneralError() {
		String typeName = dgsQueryExecutor.executeAndExtractJsonPath(GraphQLQueryRequestBuilder.addPersonGraphQLQueryRequest(personInfoInput).serialize(), "data.AddPerson.__typename");

		assertEquals("GeneralError", typeName);
	}

	@Test
	void findPersons_Test() {
		String typeName = dgsQueryExecutor.executeAndExtractJsonPath(GraphQLQueryRequestBuilder.findPersonsGraphQLQueryRequest(wherePerson).serialize(), "data.FindPersons.__typename");

		assertEquals("InformationOfPersonsResponse", typeName);
	}

}
