package com.votecounting.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.votecounting.java.Candidate;
import com.votecounting.java.Vote;

public class VoteTest {
	
	Vote vote;
	Vote vote2;
	
	@Before
	public void setUp() throws Exception {
		vote= new Vote(new Candidate('A',"Winery Tour"),1);	
		vote2= new Vote(vote.getCandidate(),vote.getOrderPreference());	
	}

	@Test
	public void test_Vote() {
		assertEquals("Compare Votes=>",vote.toString(),vote2.toString());
	}

}
