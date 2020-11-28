import java.util.Scanner;






public class A3 {

    private static final String[][] AVENGER_ROSTER = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private int avengerMentionIndexValue = 0;
	private Scanner input = new Scanner(System.in);
	private BST<Avenger> mentionBST = new BST<>(new mentionindexComparator());
	private BST<Avenger> alphabeticalBST= new BST<>();
	private BST<Avenger> mostPopularBST = new BST<> (new DescendingComparator());
	private BST<Avenger> leastPopularBST = new BST<>(new AscendingComparator())
    


public static void main(String[] args) {
    A3 a1 = new A3();
    a1.run();
}

public void run() {
    readInput();
    createdOrderedBST();
    printResults();
}





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
				if (alphabeticalBST.find(hero)) 
				{
					alphabeticalBST.getData(hero).mentioned();
				}
		
				else
				{
					alphabeticalBST.add(hero);
					avengerMentionIndexValue++;
					alphabeticalBST.getData(hero).mentionIndexSetter(avengerMentionIndexValue);
				}
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

 
  
    
    private void createdOrderedBST() {
    for (Avenger a : alphabeticalBST)
        {

            if (a.getAlias().equals("hawkeye"))
            {
                alphabeticalBST.remove(a);
            }

            else
            {
                mentionBST.add(a);
                mostPopularBST.add(a);
                leastPopularBST.add(a);
            }
        }
    }



	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionBST.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");

        Iterator<Avenger> mentionPrint = mentionBST.iterator();

        while (mentionPrint.hasNext())
        {
            System.out.println(mentionPrint.next());
        }

		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");

        Iterator<Avenger> mostPopularPrint = mostPopularBST.iterator();

        while (mostPopularPrint.hasNext())
        {
            System.out.println(mostPopularPrint.next());
        }
		
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");

        Iterator<Avenger> leastPopularPrint = leastPopularBST.iterator();

        while (leastPopularPrint.hasNext())
        {
            System.out.println(leastPopularPrint.next());
        }
		
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");

        Iterator<Avenger> alphabeticalPrint = alphabeticalBST.iterator();

        while (alphabeticalPrint.hasNext())
        {
            System.out.println(alphabeticalPrint.next());
        }
		
		System.out.println();
	}
}