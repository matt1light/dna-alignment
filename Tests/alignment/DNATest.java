package alignment;

import static org.junit.Assert.*;
import org.junit.Test;

public class DNATest {
	
	@Test
	public void defaultConstructorTest(){
		DNA dna1 = new DNA();
		
		assertEquals("sequence is not set properly", null ,dna1.getSequence());
		assertEquals("sequence is not set properly", 0 ,dna1.getId());
	}
	
	@Test
	public void overloadedConstructorTest(){
		String sequence1 = "GCATACA";
		String sequence2 = "GCAGACAADAG";
		DNA dna1 = new DNA(sequence1);
		DNA dna2 = new DNA(sequence2);
		
		assertEquals("sequence 1 is set properly", " " + sequence1, dna1.getSequence());
		assertEquals("sequence 2 is not set properly", " " + sequence2, dna2.getSequence());
		assertEquals("id is not 1", 1, dna1.getId());
		assertEquals("id is not 2", 2, dna2.getId());
	}

}
