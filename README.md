# Vote Counting
Counts the votes from the ballots and selects a winner candidate.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The application runs on any computer with the JRE installed.

```
Windows, MacOS, Linux.
```

### Installing

You can also download a copy of the project files into the same folder by clicking on "Clone or Download" button. If you prefer you can use this URL: https://github.com/djarmengod/VoteCounting.git with Git or checkout with SVN.

Click on Clone or download button and press Download ZIP.

```
Once downloaded unzip the files in the same folder and open a command window, navigate to the folder and execute: java -jar VoteCounting.jar.
```

## Usage instructions
Check the file: CandidatesFile.txt in the files folder.
Enter as many Candidates per line as you wish.

Once you executed the VoteCounting.jar file in your command window.

1. Enter the candidates in order of preference or "tully" to count votes.
```
*Note: The options must match with the letters displayed brefore the candidate's name on the screen to be considered as a vote.
```
2. Hit Enter to introduce the next ballot or type the word "tully" to count the ballots and find a winner.

3. Vote counting proceeds in rounds until the flow of preferences has resulted in one option reaching enough votes to meet the quota for selection.

4. The quota is calculated as: (number of non-exhausted ballots / 2) + 1.This is to be calculated at each round whenever needed or whenever the number of non-exhausted votes might change. At each round of voting the votes for each candidate are to measured against the quota. 

5.If a candidate has at least as many votes as the quota, it is selected as the winner and no more counting is necessary. 
If no option has enough votes to meet the quota, then the candidate or candidates with the smallest number of votes are eliminated, and all the votes for those options are distributed to their next available (i.e. non eliminated) preference as chosen at voting time. If there are no more preferences expressed for a ballot or the remaining preferences have all been eliminated, the ballot is considered to be exhausted.

6. At each round of counting, the program should display an explanation of what has happened (e.g. “Candidate X eliminated” or “Candidate selected as winner”), the quota required to win, and the number of votes currently assigned to each candidate. 

## Running the tests

The unit tests have been done by using JUnit Testing framework. To run the tests execute the files with the Test suffix inside the tests folder.

## Deployment

Copy all the files into the file folder of choice.

## Built With

* [Eclipse](https://www.eclipse.org) - The Java IDE used.
* [JUnit](https://junit.org) - The Java Unit Testing framework used.

## Contributing

If you wish to contribute submit a pull request.

## Versioning

For the versions available, see the tags on this repository [tags on this repository](https://github.com/djarmengod/VoteCounting/tags).

## Authors

* **David Jaramillo**

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments
