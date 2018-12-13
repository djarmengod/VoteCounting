/**
 * 
 */
package com.votecounting.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.votecounting.java.VoteCount;

/**
 * @author David Jaramillo
 *
 */
public class VoteCountTest {

	/**
	 * @throws java.lang.Exception
	 */
	VoteCount voteCount;
	List<Candidate> candidates;
	List<CandidateGroup> candidateGroups;
	List<Vote> votes;
	List<BallotPaper> ballotPapers;

	@Before
	public void setUp() throws Exception {
		// candidates type stub
		candidates = new ArrayList<>();
		candidates.add(new Candidate('A', "Winery tour"));
		candidates.add(new Candidate('B', "Ten pin bowling"));
		candidates.add(new Candidate('C', "Movie night"));
		candidates.add(new Candidate('D', "Museum visit"));

		// Ballots type stub
		ballotPapers = new ArrayList<>();
		votes = new ArrayList<>();
		votes.add(new Vote(candidates.get(0), 1));
		votes.add(new Vote(candidates.get(1), 2));
		votes.add(new Vote(candidates.get(2), 4));
		votes.add(new Vote(candidates.get(3), 3));
		ballotPapers.add(new BallotPaper("Team Member 1", votes));

		votes = new ArrayList<>();
		votes.add(new Vote(candidates.get(0), 2));
		votes.add(new Vote(candidates.get(1), 1));
		// votes.add(new Vote(candidates.get(2),null));
		votes.add(new Vote(candidates.get(3), 3));
		ballotPapers.add(new BallotPaper("Team Member 2", votes));

		votes = new ArrayList<>();
		votes.add(new Vote(candidates.get(0), 2));
		votes.add(new Vote(candidates.get(1), 3));
		votes.add(new Vote(candidates.get(2), 1));
		votes.add(new Vote(candidates.get(3), 4));
		ballotPapers.add(new BallotPaper("Team Member 3", votes));

		votes = new ArrayList<>();
		votes.add(new Vote(candidates.get(0), 3));
		votes.add(new Vote(candidates.get(1), 4));
		votes.add(new Vote(candidates.get(2), 1));
		votes.add(new Vote(candidates.get(3), 2));
		ballotPapers.add(new BallotPaper("Team Member 4", votes));

		votes = new ArrayList<>();
		votes.add(new Vote(candidates.get(0), 2));
		// votes.add(new Vote(candidates.get(1),null));
		// votes.add(new Vote(candidates.get(2),null));
		votes.add(new Vote(candidates.get(3), 1));
		ballotPapers.add(new BallotPaper("Team Member 5", votes));

		votes = new ArrayList<>();
		// votes.add(new Vote(candidates.get(0),null));
		votes.add(new Vote(candidates.get(1), 2));
		// votes.add(new Vote(candidates.get(2),null));
		votes.add(new Vote(candidates.get(3), 1));
		ballotPapers.add(new BallotPaper("Team Member 6", votes));

		votes = new ArrayList<>();
		votes.add(new Vote(candidates.get(0), 2));
		votes.add(new Vote(candidates.get(1), 1));
		votes.add(new Vote(candidates.get(2), 3));
		// votes.add(new Vote(candidates.get(3),null));
		ballotPapers.add(new BallotPaper("Team Member 7", votes));

		votes = new ArrayList<>();
		votes.add(new Vote(candidates.get(0), 3));
		votes.add(new Vote(candidates.get(1), 2));
		votes.add(new Vote(candidates.get(2), 1));
		votes.add(new Vote(candidates.get(3), 4));
		ballotPapers.add(new BallotPaper("Team Member 8", votes));

		voteCount = new VoteCount();

	}
	// Test 1=>VoteCount getCandidateGroup Null
	@Test
	public void test_getCandidateGroup() {
		
		assertNull("Compare Created CandidateGroups 1=>", voteCount.getCandidateGroup(candidates.get(0)));

	}


	// Test 2=>VoteCount createCandidateGroups
	@Test
	public void test_createCandidateGroups() {

		voteCount.createCandidateGroups(candidates);

		assertEquals("Compare Created CandidateGroups 1=>", voteCount.getCandidateGroups().get(0).getCandidate(),
				candidates.get(0));
		assertEquals("Compare Created CandidateGroups 2=>", voteCount.getCandidateGroups().get(1).getCandidate(),
				candidates.get(1));
		assertEquals("Compare Created CandidateGroups 3=>", voteCount.getCandidateGroups().get(2).getCandidate(),
				candidates.get(2));
		assertEquals("Compare Created CandidateGroups 4=>", voteCount.getCandidateGroups().get(3).getCandidate(),
				candidates.get(3));

	}

	// Test 3=>VoteCount allocateBallots
	@Test
	public void test_allocateBallots() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));

		voteCount.allocateBallots(candidates,ballotPapers);
		
		assertEquals("1.Winery tour=>", voteCount.getCandidateGroups().get(0).getBallotPapers().get(0),
				ballotPapers.get(0));
		assertEquals("Ten pin bowling=>", voteCount.getCandidateGroups().get(1).getBallotPapers().get(0),
				ballotPapers.get(1));
		assertEquals("2.Ten pin bowling=>", voteCount.getCandidateGroups().get(1).getBallotPapers().get(1),
				ballotPapers.get(6));
		assertEquals("3.Movie night=>", voteCount.getCandidateGroups().get(2).getBallotPapers().get(0),
				ballotPapers.get(2));
		assertEquals("4.Movie night=>", voteCount.getCandidateGroups().get(2).getBallotPapers().get(1),
				ballotPapers.get(3));
		assertEquals("5.Movie night=>", voteCount.getCandidateGroups().get(2).getBallotPapers().get(2),
				ballotPapers.get(7));
		assertEquals("6.Museum visit=>", voteCount.getCandidateGroups().get(3).getBallotPapers().get(0),
				ballotPapers.get(4));
		assertEquals("7.Museum visit=>", voteCount.getCandidateGroups().get(3).getBallotPapers().get(1),
				ballotPapers.get(5));
	}

	// Test 4=>VoteCount getExhaustedBallots
	@Test
	public void test_getExhaustedBallots() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));
		
		//Set 3 Exhausted Ballots
		ballotPapers.get(0).setExhaustedBallot(true);
		ballotPapers.get(1).setExhaustedBallot(true);
		ballotPapers.get(2).setExhaustedBallot(true);

		assertEquals("Total Votes=>", voteCount.getTotalExhaustedBallots(), 3);

	}

	// Test 4=>VoteCount getNonExhaustedBallot
	@Test
	public void test_getNonExhaustedBallots() {

		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));
		
		//Set 3 Exhausted Ballots
		ballotPapers.get(0).setExhaustedBallot(true);
		ballotPapers.get(1).setExhaustedBallot(true);
		ballotPapers.get(2).setExhaustedBallot(true);

		assertEquals("Total Votes=>", voteCount.getTotalNonExhaustedBallots(), 5);

	}

	// Test 5=>VoteCount calculateQuota
	@Test
	public void test_calculateQuota() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));

		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));

		int NonExhaustedBallots = 8;

		assertEquals("Quota=>", voteCount.calculateQuota(NonExhaustedBallots), 5);

	}

	// Test 6=>VoteCount candidateGroupWinner only 1
	@Test
	public void test_candidateWinnerNotNull() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));

		int quota = 4;

		assertNotNull(voteCount.candidateWinner(quota));
		assertEquals("Only 1 winner candidateGroupWinner=>", voteCount.candidateWinner(quota),
				voteCount.getCandidateGroups().get(2));
	}

	// Test 6=>VoteCount candidateGroupWinner only 1
	@Test
	public void test_candidateWinnerNullMoreThan1() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));

		int quota = 2;

		assertNull("More than 1 winner =>", voteCount.candidateWinner(quota));

	}

	// Test 7=>VoteCount candidateGroupWinner only 1
	@Test
	public void test_candidateWinnerNullNoWinner() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));

		int quota = 3;

		assertNull("No winners =>", voteCount.candidateWinner(quota));

	}

	// Test 6=>VoteCount candidateGroupWinner only 1
	@Test
	public void test_candidateRandomWinner_NotNull() {

		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));

		assertNotNull("Tie random winner candidateRandomWinner=>", voteCount.candidateRandomWinner());

	}
	
	// Test 7=>VoteCount test_candidateGroupleastVotes
	@Test
	public void test_candidateGroupleastVotesNull() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));
		
		//Eliminate the one with more votes so there is a triple tie
		voteCount.getCandidateGroups().get(1).setEliminated(true);
		
		CandidateGroup candidateGroupLeast;
		candidateGroupLeast = voteCount.candidateLeastVotes();
		assertNull("No least Candidadate =>", candidateGroupLeast);
	}
	
	// Test 8=>VoteCount test_candidateGroupleastVotes
	@Test
	public void test_candidateGroupleastVotesNotNull() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));

		CandidateGroup candidateGroupLeast;
		candidateGroupLeast = voteCount.candidateLeastVotes();
		assertNotNull("least Candidadate =>", candidateGroupLeast);
	}

	// Test 9=>VoteCount reAllocateBallots
	@Test
	public void test_reAllocateBallots() {
		
		//Create CandidateGroups
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		
		//Allocate Ballots
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));

		voteCount.reAllocateBallots(voteCount.getCandidateGroups().get(0));

		assertEquals("reAllocate Ballot 1 from Winery Tour to Ten Pin Bowling=>",
				voteCount.getCandidateGroups().get(1).getBallotPapers().get(2), ballotPapers.get(0));

		/*
		 * voteCount.getCandidateGroups().forEach( (candidate)->{
		 * System.out.println("Group: "+candidate.getCandidate().getName());
		 * candidate.getBallotPapers().forEach( (ballot)->{
		 * System.out.println(ballot.getTeamMember());
		 * ballot.getVotes().forEach((vote)->System.out.println(vote.toString())); });
		 * });
		 */

	}

	// Test 10=>VoteCount start Given example
	@Test
	public void test_start() {

		// Create CandidateGroup for each candidate
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));

		// Allocate the ballots to their first preference
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));

		voteCount.start();

	}
	
	// Test 11=>VoteCount start Tie
	@Test
	public void test_start_Tie() {

		// Create CandidateGroup for each candidate
		voteCount.addCandidateGroup(candidates.get(0));
		voteCount.addCandidateGroup(candidates.get(1));
		voteCount.addCandidateGroup(candidates.get(2));
		voteCount.addCandidateGroup(candidates.get(3));

		// Allocate the ballots to their first preference
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(0));
		voteCount.getCandidateGroups().get(0).addBallotPaper(ballotPapers.get(1));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(6));
		voteCount.getCandidateGroups().get(1).addBallotPaper(ballotPapers.get(2));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(3));
		voteCount.getCandidateGroups().get(2).addBallotPaper(ballotPapers.get(7));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(4));
		voteCount.getCandidateGroups().get(3).addBallotPaper(ballotPapers.get(5));
	
		voteCount.start();

	}

}
