package com.votecounting.java;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BallotPaper {

	private String teamMember;
	private List<Vote> votes;
	private boolean exhaustedBallot;

	public BallotPaper(String teamMember, List<Vote> votes) {
		this.teamMember = teamMember;
		this.votes = votes;
		this.exhaustedBallot = false;
	}
	
	public String getTeamMember() {
		return teamMember;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public boolean isExhaustedBallot() {
		return exhaustedBallot;
	}

	public void setExhaustedBallot(boolean exhaustedBallot) {
		this.exhaustedBallot = exhaustedBallot;
	}
	
	public Vote getVoteFirstNextPreference(List<Candidate> candidates) {
		
		for (Vote vote : votes.stream().sorted(Comparator.comparing(Vote::getOrderPreference))
				.collect(Collectors.toList())) {
			if (candidates.contains(vote.getCandidate())) {
				// Allocate it to the first next preference
				return vote;
			}			
		}
		return null;		

	}
	
	public boolean exhaustBallot(List<Candidate> candidates) {
		
		for (Vote vote : votes) {
			if (candidates.contains(vote.getCandidate())) {
				// Allocate it to the first next preference
				return false;
			}			
		}
		setExhaustedBallot(true);
		return true;		

	}
	
}
