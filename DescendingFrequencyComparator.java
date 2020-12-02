/**
 * @author Zain
 */
import java.util.Comparator;
//DescendingComparator
public class DescendingFrequencyComparator implements Comparator<Avenger> {

	public int compare(Avenger a1, Avenger a2) {
		int compareFrequency = a2.getFrequencyMentioned() - a1.getFrequencyMentioned();
		
		if (compareFrequency == 0) {
			return a2.getAlias().compareTo(a1.getAlias());
			
		} else {
            return compareFrequency;
		}
	}
}