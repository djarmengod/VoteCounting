package com.votecounting.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.votecounting.java.Candidate;

public class CandidateTest {

	Candidate candidate;
	Candidate candidate2;
	
	@Before
	public void setUp() throws Exception {
		candidate= new Candidate('A',"Winery Tour");	
		candidate2= new Candidate(candidate.getLetter(),candidate.getName());	
	}

	@Test
	public void test_Candidate() {
		assertEquals("Compare candidates=>",candidate.toString(),candidate2.toString());
	}

}
