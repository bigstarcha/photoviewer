/**
 * Homework 4
 * Timothy Cha, thc8pku
 * 
 * Sources: Lecture and TA Office Hours
 */

import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph>{

    /**
     * This method compares two photographs based off of their ratings in descending order.
     * The method returns a positive number if the current photograph's rating is less than the parameter photo's
     * rating, and negative if vice versa. If the two photographs have the same rating, the method compares
     * the two dates based off of their captions in alphabetical order.
     * 
     * @param p The photograph whose date is to be compared to the current photograph's date
     * @return The comparison value between the two photographs
     */
    @Override
    public int compare(Photograph p1, Photograph p2) {
        if (p1.getRating() == (p2.getRating())) {
            return p1.getCaption().compareTo(p2.getCaption());
        }
        return p2.getRating() - p1.getRating();
    }
    
}
