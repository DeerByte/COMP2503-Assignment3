/**
 * @author Zain
 */
import java.util.Comparator;
//DescendingComparator
public class DescendingFrequencyComparator implements Comparator<Avenger> {

	public int compare(Avenger a1, Avenger a2) {
		int compareFrequency = a2.getFrequencyMentioned() - a1.getFrequencyMentioned();
		
		if (compareFrequency == 0) {
			return a1.getAlias().compareTo(a2.getAlias());
			
		} else {
            return compareFrequency;
		}
	}
}