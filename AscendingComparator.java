/**
 * Compares Avenger objects by ascending value of frequency mentioned. 
 * If both objects are mentioned the same number of times, they will be compared by ascending order of lastName.length().
 * If both objects have the same length, they will be sorted by ascending alphabetic order of their lastName.
 * 
 * @author DeerByte, Zain
 */
import java.util.Comparator;

public class AscendingComparator implements Comparator<Avenger>{

    public int compare(Avenger a1, Avenger a2){
        int compareFrequency = a1.getFrequencyMentioned() - a2.getFrequencyMentioned();
        
        if (compareFrequency != 0) {
            return compareFrequency;
        }

        int compareLastNameSize = a1.getLastName().length() - a2.getLastName().length();

        if (compareLastNameSize != 0) {
            return compareLastNameSize;
        }
        
        return a1.getLastName().compareTo(a2.getLastName());
    }
}
