package com.votecounting.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.votecounting.java.Candidate;
import com.votecounting.java.VoteEnter;

public class VoteEnterTest {

	VoteEnter enter;
	String input;
	
	Candidate candidate;
	List<Candidate> candidates;
	
	@Before
	public void setUp() throws Exception {
		enter = new VoteEnter();
		// candidates type stub
		candidates = new ArrayList<>();
		candidate = new Candidate('A', "Winery tour");
		candidates.add(candidate);
		candidate = new Candidate('B', "Ten pin bowling");
		candidates.add(candidate);
		candidate = new Candidate('C', "Movie night");
		candidates.add(candidate);
		candidate = new Candidate('D', "Museum visit");
		candidates.add(candidate);
	}
	
	//Test 1 readVote
	@Test
	public void test_readVote() {

		input = " Ad ab bD";
		enter.readVote(input,candidates);	
		//
		assertEquals(candidates.get(0),enter.getVotes().get(0).getCandidate());
		assertEquals(candidates.get(3),enter.getVotes().get(1).getCandidate());
		assertEquals(candidates.get(1),enter.getVotes().get(2).getCandidate());
	}
	
	//Test 2 voteExists True
	@Test
	public void test_voteExistsTrue() {

		input = " Ad ab bD";
		enter.readVote(input,candidates);	
		//
		assertTrue("voteExists True",enter.voteExists(candidates.get(0)));

	}

	//Test 3 voteExists False
	@Test
	public void test_voteExistsFalse() {

		input = " Ad ab bD";
		enter.readVote(input,candidates);	
		//
		assertTrue("voteExists False",!enter.voteExists(candidates.get(2)));

	}
	
}
