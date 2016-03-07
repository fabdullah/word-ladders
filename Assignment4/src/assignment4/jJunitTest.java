package assignment4;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class jJunitTest {
	Assignment4Interface wordLadderSolver = new WordLadderSolver();
	
	@Test
	public void testMain1() throws NoSuchLadderException {
		Assign4Driver.processLinesInFile("A4words.dat.txt");
		List<String> result= wordLadderSolver.computeLadder("ryan", "joe");
		assertEquals(true, wordLadderSolver.validateResult("ryan", "joe", result));
		System.out.println();
	}
	@Test
	public void testMain2() throws NoSuchLadderException{
		List<String> result= wordLadderSolver.computeLadder("abcde", "fghij");
		assertEquals(true, wordLadderSolver.validateResult("abcde", "fghij", result));
		System.out.println();
	}
	
	@Test
	public void testMain3() throws NoSuchLadderException{
		List<String> result= wordLadderSolver.computeLadder("atlas", "zebra");
		assertEquals(true, wordLadderSolver.validateResult("atlas", "zebra", result));
		System.out.println();
	}
	
	@Test
	public void testMain4() throws NoSuchLadderException{
		List<String> result= wordLadderSolver.computeLadder("stone", "money");
		assertEquals(true, wordLadderSolver.validateResult("stone", "money", result));
		System.out.println();
	}

	
}
