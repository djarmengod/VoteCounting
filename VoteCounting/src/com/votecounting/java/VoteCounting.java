package com.votecounting.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VoteCounting {

	private static List<BallotPaper> ballotPapers = new ArrayList<>();
	private static Scanner scanner;

	public static void main(String[] args) {
		
		// Read File with the Candidates options
		CandidatesFile candidatesFile = CandidatesFile.getInstance();
		if (!candidatesFile.readFile(CandidatesFile.PATH)) {
			return;
		}
		
		System.out.println("******************** Vote Counting Application ********************\n");
		
		List<Candidate> candidates = candidatesFile.getCandidates();
		CandidatesDisplay display = new CandidatesDisplay(candidates);		

		int i = 1;
		// Read vote preferences input for each team member
		while (true) {
			
			String teamMember = "Team Member:" + i;
			System.out.println(teamMember);
			// Display the Candidates
			display.show();		
			
			System.out.println("Enter the candidates in order of preference or \"tully\" to count votes.\n>");

			scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			
			if (line.toLowerCase().equals("tully")) {
				// voteCount
				if (!ballotPapers.isEmpty()) {
					VoteCount voteCount = new VoteCount();
					voteCount.createCandidateGroups(candidates);
					voteCount.allocateBallots(candidates, ballotPapers);
					voteCount.start();
					break;
				}
			} else if (line.length() > 0) {
				// Enter Vote
				VoteEnter voteEnter = new VoteEnter();
				voteEnter.readVote(line, candidates);
				if (!voteEnter.getVotes().isEmpty()) {
					// Create Ballot Paper
					BallotPaper ballotPaper = new BallotPaper(teamMember, voteEnter.getVotes());
					ballotPapers.add(ballotPaper);
					i++;
				}else
				{
				 System.out.println("\nError: You must enter at least 1 valid vote.\n");	
				}
			}
		}

	}

}
