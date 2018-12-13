package com.votecounting.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class VoteCount {

	private List<CandidateGroup> candidateGroups;

	public VoteCount() {
		candidateGroups = new ArrayList<>();
	}

	public void addCandidateGroup(Candidate candidate) {
		candidateGroups.add(new CandidateGroup(candidate));
	}

	public void allocateBallots(List<Candidate> candidates, List<BallotPaper> ballotPapers) {

		// Add ballot to the CandidateGroup of their firstNextpreference
		for (BallotPaper ballotPaper : ballotPapers) {
			Candidate candidate = ballotPaper.getVoteFirstNextPreference(candidates).getCandidate();
			getCandidateGroup(candidate).addBallotPaper(ballotPaper);
		}

	}

	public int calculateQuota(int totalNonExhaustedBallots) {

		return (totalNonExhaustedBallots / 2) + 1;
	}

	public CandidateGroup candidateLeastVotes() {

		CandidateGroup candidateGroupLeast = getCandidateGroup(getNonEliminatedCandidates().get(0));
		int leastCounter=0;
		for (Candidate candidate : getNonEliminatedCandidates()) {
			CandidateGroup candidateGroup = getCandidateGroup(candidate);
			if (candidateGroup.getNonExhaustedBallots() < candidateGroupLeast.getNonExhaustedBallots()) {
				candidateGroupLeast = candidateGroup;
				leastCounter=1;
			} else if (candidateGroup.getNonExhaustedBallots() == candidateGroupLeast.getNonExhaustedBallots()) {
				leastCounter+=1;
			}
		}
		if (leastCounter==getNonEliminatedCandidates().size()) {
			candidateGroupLeast=null;
		}
		return candidateGroupLeast;
	}

	public CandidateGroup candidateRandomWinner() {
		
			int min = 0;
			int max = getNonEliminatedCandidates().size() - 1;
			Random r = new Random();

			int randomNumber = r.nextInt((max - min) + 1) + min;
			return getCandidateGroup(getNonEliminatedCandidates().get(randomNumber));
	}

	public CandidateGroup candidateWinner(int quota) {

		boolean foundWinner = false;
		CandidateGroup candidateGroupWinner = null;

		// Search for only 1 Candidate winner with votes >= quota
		for (CandidateGroup candidateGroup : candidateGroups) {
			if (candidateGroup.getNonExhaustedBallots() >= quota) {
				if (!foundWinner) {
					candidateGroupWinner = candidateGroup;
					foundWinner = true;
				} else {
					candidateGroupWinner = null;
					foundWinner = false;
					break;
				}
			}
		}
		return candidateGroupWinner;
	}

	public void createCandidateGroups(List<Candidate> candidates) {
		// Create CandidateGroup for each candidate
		for (Candidate candidate : candidates) {
			addCandidateGroup(candidate);
		}
	}

	public CandidateGroup getCandidateGroup(Candidate candidate) {

		for (CandidateGroup candidateGroup : candidateGroups) {
			if (candidateGroup.getCandidate().equals(candidate)) {
				return candidateGroup;
			}
		}
		return null;
	}

	public List<CandidateGroup> getCandidateGroups() {
		return candidateGroups;
	}

	public List<Candidate> getNonEliminatedCandidates() {

		List<Candidate> candidates = new ArrayList<>();

		for (CandidateGroup candidateGroup : candidateGroups) {
			if (!candidateGroup.isEliminated()) {
				candidates.add(candidateGroup.getCandidate());
			}
		}
		return candidates;
	}

	public int getTotalExhaustedBallots() {

		int totalExhaustedBallots = 0;
		for (CandidateGroup candidateGroup : candidateGroups) {
			totalExhaustedBallots += candidateGroup.getExhaustedBallots();
		}
		return totalExhaustedBallots;
	}

	public int getTotalNonExhaustedBallots() {

		int totalNonExhaustedBallots = 0;
		for (CandidateGroup candidateGroup : candidateGroups) {
			totalNonExhaustedBallots += candidateGroup.getNonExhaustedBallots();
		}
		return totalNonExhaustedBallots;
	}

	public void reAllocateBallots(CandidateGroup candidateGroupLeastVotes) {

		// Set candidateGroupLeastVotes Eliminated
		candidateGroupLeastVotes.setEliminated(true);

		Iterator<BallotPaper> iterator = candidateGroupLeastVotes.getBallotPapers().iterator();
		while (iterator.hasNext()) {
			BallotPaper ballotPaper = (BallotPaper) iterator.next();
			
			//Exhaust Ballot or Reallocate
			if (!ballotPaper.exhaustBallot(getNonEliminatedCandidates())) {
				
				//Add ballot to first next preference
				Vote voteNextPreference = ballotPaper.getVoteFirstNextPreference(getNonEliminatedCandidates());
				getCandidateGroup(voteNextPreference.getCandidate()).addBallotPaper(ballotPaper);
				
				//Remove Ballot from candidateGroupleastVotes
				iterator.remove();
				System.out.println(ballotPaper.getTeamMember() + " Ballot Moved => " + " from: "
						+ candidateGroupLeastVotes.getCandidate().getName() + " to "
						+ getCandidateGroup(voteNextPreference.getCandidate()).getCandidate().getName());
			}
		}
	}

	public void start() {

		int round = 1;
		// Start rounds
		while (true) {

			int totalExhaustedBallots = getTotalExhaustedBallots();
			int totalNonExhaustedBallots = getTotalNonExhaustedBallots();
			int quota = calculateQuota(totalNonExhaustedBallots);

			System.out.println();
			System.out.println("**** Round " + round + " ****");
			System.out.println("Votes :" + totalNonExhaustedBallots);

			System.out.println("Quota :" + quota);
			System.out.println("Exhausted Votes :" + totalExhaustedBallots);
			System.out.println("------- Votes ---------");
			getCandidateGroups().forEach((candidateGroup) -> {
				System.out.println(
						candidateGroup.getCandidate().toString() + " => " + candidateGroup.getNonExhaustedBallots());
			});
			System.out.println("-----------------------");

			// Check only for 1 winner
			CandidateGroup candidateWinner = candidateWinner(quota);
			if (candidateWinner != null) {
				System.out.println("\nCandidate selected as winner: \""
						+ candidateWinner.getCandidate().getName().toString() + "\".");
				break;
			}
			
			CandidateGroup candidateLeastVotes = candidateLeastVotes();
			
			// Check for a tie if there is not candidateLeastVotes			
			if (candidateLeastVotes == null) {
				CandidateGroup candidateRandomWinner = candidateRandomWinner();
				System.out.println("\nTie! Candidate randomly selected as winner: \""
						+ candidateRandomWinner.getCandidate().toString() + "\".");
				break;
			}				

			// There wasn't a winner, reallocate candidate with leastVotes
			reAllocateBallots(candidateLeastVotes);

			System.out.println("Candidate eliminated: " + candidateLeastVotes.getCandidate().getName());

			round += 1;
		}

	}
}
