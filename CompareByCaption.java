/**
 * Homework 4
 * Timothy Cha, thc8pku
 * 
 * Sources: Lecture and TA Office Hours
 */

import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph>{

    /**
     * This method compares two photographs based off of their captions. The method returns a negative number if
     * the current photograph's caption precedes the parameter photo's caption, and positive if
     * vice versa. If the two photographs have the same caption, the method compares the two dates based off of
     * their ratings from higher to lower.
     * 
     * @param p The photograph whose date is to be compared to the current photograph's date
     * @return The comparison value between the two photographs
     */
    @Override
    public int compare(Photograph p1, Photograph p2) {
        if (p1.getCaption().equals(p2.getCaption())) {
            return p2.getRating() - p1.getRating();
        }
        return p1.getCaption().compareTo(p2.getCaption());
    }
    
}
