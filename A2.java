import java.util.Scanner;

/** 
 * COMP 2503 Winter 2020 Assignment 2 
 * 
 * This program must read a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi
 * @date Fall 2020
*/
/**
 * Added methods: 
 * 		getRosterIndex(int);
 * 		createAvenger(int);
 * 		addToMentionList(Avenger);
 * 		printFirstXFromList(int, SLL<Avenger>).
 * 
 * Finished method: 
 * 		createOrderedLists();
 * 		printResults();
 * 
 * @version November 1, 2020
 * @author DeerByte 
 */

public class A2 {

	private static final String[][] AVENGER_ROSTER = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	private SLL<Avenger> mentionList = new SLL<>();
	private SLL<Avenger> alphabeticList = new SLL<>();
	private SLL<Avenger> mostPopularList = new SLL<>(new DescendingComparator());
	private SLL<Avenger> leastPopularList = new SLL<>(new AscendingComparator());
	
	public static void main(String[] args) {
		A2 a1 = new A2();
		a1.run();
	}

	public void run() {
		readInput();
		createdOrderedLists();
		printResults();
	}

	private void createdOrderedLists() {
		Avenger curr;

		if (mentionList.size() == 0) {
			return;
		}

		for (int i = 0; i < mentionList.size(); i++) {
			curr = mentionList.get(i);
			alphabeticList.addInOrder(curr);
			mostPopularList.addInOrder(curr);
			leastPopularList.addInOrder(curr);
		}
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 */
	private void readInput() {
		/*
		In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name then
				- Create a new avenger object with the corresponding alias and last name.
				- if this avenger has already been mentioned, increase the frequency count for the object already in the list.
				- if this avenger has not been mentioned before, add the newly created avenger to the end of the list, remember to set the frequency.
		*/ 
		while (input.hasNext()) {

			String word = cleanWord(input.next());

			if (word.length() > 0) {
				totalwordcount++;
				
				int rosterIndex = getRosterIndex(word);

				if (rosterIndex != -1) {
					Avenger hero = createAvenger(rosterIndex);
					addToMentionList(hero);
				}
			}
		}
	}
	
	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}
	

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionList.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		for (Avenger hero : mentionList) {
			System.out.println(hero);
		}

		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		printFirstXFromList(topN, mostPopularList);
		
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		printFirstXFromList(topN, leastPopularList);
		
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		for (Avenger hero : alphabeticList) {
			System.out.println(hero.toString());
		}
		
		System.out.println();
	}

	/**
	 * !Uses AVENGER_ROSTER!
	 * 
	 *  Requires changes if AVENGER_ROSTER indices go beyond [x][y>1].
	 * 
	 * 	If no match  is found, returns -1; 
	 *  If a match is found in AVENGER_ROSTER, returns the first index of avengersRoster
	 * 
	 *  
	 * @param input - String to be matched against AVENGER_ROSTER.
	 * @return int - first index of corresponding Avenger within avengersRoster. 
	 */
	private int getRosterIndex(String input) {
		int index= -1;
		for(int i = 0; i < AVENGER_ROSTER.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (AVENGER_ROSTER[i][j].equals(input)) {
					index = i;
					return index;
				}
			}
		}
		return index;
	}

	/**
	 * Creates an Avenger object using the first index of AVENGER_ROSTER.
	 * 
	 * @param rosterIndex - first index of the 2D AVENGER_ROSTER array.
	 * @return Avenger object representing the index within AVENGER_ROSTER.
	 */
	private Avenger createAvenger(int rosterIndex) {
		return new Avenger(AVENGER_ROSTER[rosterIndex][0], AVENGER_ROSTER[rosterIndex][1], 1);
	}

	/**
	 * Checks for index of Avenger within mentionList. 
	 * If there is no pseudo-equivalent object, the Avenger is added to mentionList.
	 * If a pseudo-equivalent object exists, this object is retrieved and mentioned().
	 * 
	 * Pseudo-equivalent implies the objects are not equal as per Object.equals(Object o).
	 * Avenger objects are only compared by their Alias and LastName fields for this implementation.
	 * 
	 * @see Avenger
	 * @see SLL
	 */
	private void addToMentionList(Avenger a) {
		int index = mentionList.indexOf(a);

		if (index == -1) {
			mentionList.add(a);

		} else {
			mentionList.get(index).mentioned();
		}
	}

	/**
	 * Prints the first 'X' number of elements from the SLL<Avenger> list referenced.
	 * If numToPrint >= size, all elements within the list will be printed.
	 * @param numToPrint
	 * @param list
	 */
	private void printFirstXFromList(int numToPrint, SLL<Avenger> list) {
		int size = list.size();

		if (list.isEmpty()) {
			return;
		}
		ListIterator<Avenger> itr = list.iterator();

		if (numToPrint > size) {
			
			for (int i = 0; i < size; i++) {
				System.out.println(itr.next().toString());
			}
			
		} else {
			
			for (int i = 0; i < numToPrint; i++) {
				System.out.println(itr.next().toString());
			}
		}
		
	}
}
