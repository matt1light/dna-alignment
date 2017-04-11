package alignment;
import static org.junit.Assert.*;

import org.junit.Test;

public class IntegrationTests{

	@Test
	public void test1() {
		this.testHelper("GCATGCG", "GATTACA", 1, -1, -10, 0.0);
	}
	@Test
	public void test2(){
		this.testHelper("GCATGCG", "GATTACA", 5, -1, -1, 21.0);
	}
	@Test
	public void test3(){
		this.testHelper("GCATGCG", "GATTACA", 1, -12, -1, -1.0);
	}
	@Test
	public void test4(){
		this.testHelper("GCATGCG", "GATTACA", 15, -1, -1, 71.0);
	}
	@Test
	public void test5(){
		this.testHelper("GCATGCG", "GATTACA", 2, -15, -11, -42.0);
	}
	@Test
	public void test6(){
		this.testHelper("GCATGCGGGGACAGAC", "GGACACGAGCAGATTACA", 1, -1, -1, 1.0);
	}
	@Test
	public void test7(){
		this.testHelper("GCATGCGACAGACA", "ACAGGATTACA", 1, -1, -1, 3.0);
	}
	@Test
	public void test8(){
		this.testHelper("GCATGCAGACAG", "GAGGACAGATTACA", 1, -1, -1, 3.0);		
	}
	
	
	private void testHelper(String sequence1, String sequence2, double matchScore, double gapPenalty, double mismatchPenalty, double expectedScore){
		DnaSequencer junit = new DnaSequencer();
		junit.runTest(sequence1, sequence2, Math.abs(matchScore), Math.abs(gapPenalty), Math.abs(mismatchPenalty));
		double result = junit.getDPM().getLastElement().getScore();
		assertEquals(expectedScore, result, 0.1);
	}

}