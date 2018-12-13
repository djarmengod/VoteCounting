package com.votecounting.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.votecounting.java.BallotPaper;
import com.votecounting.java.Candidate;
import com.votecounting.java.Vote;

public class BallotPaperTest {
	
	BallotPaper ballot;
	Vote vote;
	List<Vote> votes;
	Candidate candidate;
	
	@Before
	public void setUp() throws Exception {
		votes = new ArrayList<>();
		
		candidate = new Candidate('D', "Museum visit");
		vote= new Vote(candidate,1);
		votes.add(vote);
		candidate = new Candidate('B', "Ten pin bowling");
		vote= new Vote(candidate,2);
		votes.add(vote);
		candidate = new Candidate('C', "Movie night");
		vote= new Vote(candidate,3);
		votes.add(vote);	
		candidate = new Candidate('A', "Winery tour");
		vote= new Vote(candidate,4);
		votes.add(vote);
		
		ballot= new BallotPaper("Team Member 1",votes);
	}

	@Test
	public void BallotPaper() {
		
		
		assertEquals(votes.get(0).getCandidate(),ballot.getVotes().get(0).getCandidate());
		assertEquals(votes.get(1).getCandidate(),ballot.getVotes().get(1).getCandidate());
		assertEquals(votes.get(2).getCandidate(),ballot.getVotes().get(2).getCandidate());
		assertEquals(votes.get(3).getCandidate(),ballot.getVotes().get(3).getCandidate());

	}
	
	@Test
	public void getVoteFirstNextPreference() {
		
		List<Candidate> candidates = new ArrayList<>();
		//candidates.add(votes.get(0).getCandidate());
		candidates.add(votes.get(1).getCandidate());
		candidates.add(votes.get(2).getCandidate());
		candidates.add(votes.get(3).getCandidate());
		
		assertEquals(ballot.getVoteFirstNextPreference(candidates),votes.get(1));

	}

	@Test
	public void getVoteFirstNextPreferenceNull() {
		
		List<Candidate> candidates = new ArrayList<>();
		//candidates.add(votes.get(0).getCandidate());
		//candidates.add(votes.get(1).getCandidate());
		//candidates.add(votes.get(2).getCandidate());
		//candidates.add(votes.get(3).getCandidate());
		
		assertNull(ballot.getVoteFirstNextPreference(candidates));

	}
	@Test
	public void exhaustBallot() {
		
		List<Candidate> candidates = new ArrayList<>();
		//candidates.add(votes.get(0).getCandidate());
		candidates.add(votes.get(1).getCandidate());
		candidates.add(votes.get(2).getCandidate());
		candidates.add(votes.get(3).getCandidate());
		
		ballot.exhaustBallot(candidates);
				
		assertTrue(!ballot.isExhaustedBallot());

	}

}
