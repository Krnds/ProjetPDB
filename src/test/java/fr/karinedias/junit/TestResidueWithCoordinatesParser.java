package test.fr.karinedias.junit;

import org.junit.jupiter.api.Test;

import main.fr.karinedias.utils.ResidueWithCoordinatesParser;

public class TestResidueWithCoordinatesParser {

	@Test
    public void testRequestLoan() throws Throwable
    {
        // Given
		
        ResidueWithCoordinatesParser residue = new ResidueWithCoordinatesParser(contentOfFile)

        // When
        LoanResponse result = underTest.requestLoan(1000f, 200f, 250f);

        // Then
        assertNotNull(result);
        assertTrue(result.isApproved());
        assertNull(result.getMessage());
    }
	
}
