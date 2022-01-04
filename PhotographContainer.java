/**
 * Homework 4
 * Timothy Cha, thc8pku
 * 
 * Sources: Lecture, TA office hours
 */

import java.util.ArrayList;

public abstract class PhotographContainer {

    /**
     * A field which stores the name of the person's photograph container
     */
    protected String name;
    /**
     * A ArrayList of photographs in a person's photograph container
     */
    protected ArrayList<Photograph> photos;
    
    /**
     * Constructor that creates a new PhotographContainer object
     * 
     * @param name The name of which the photograph container is to be named
     */
    public PhotographContainer(String name) {
        this.setName(name);
        this.photos = new ArrayList<Photograph>();
    }

    /**
     * Gets the name of the photograph container
     * 
     * @return the name of the photograph container
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the photograph container
     * 
     * @param name The new name of the photograph container
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ArrayList of photos of the photograph container
     * 
     * @return the ArrayList of photos of the photograph container
     */
    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    /**
     * This method adds a photograph to the set of the photograph container's photos if the photo was not already in the set. If it was
     * added, the method returns true, and if not, false.
     * 
     * @param p The photograph to be added to the photograph container's photo set
     * @return Whether the photograph was added or not
     */
    public boolean addPhoto(Photograph p) {
        if (p == null) {
            return false;
        }
        return this.photos.add(p);
    }

    /**
     * This method checks if photograph p is in the photograph container's set of photos. If the photo is in the photograph container's photo set, the
     * method returns true, otherwise false.
     * 
     * @param p The photograph to be checked as to whether the photograph container contains it
     * @return Whether the photograph container's photo set contains the photo
     */
    public boolean hasPhoto(Photograph p) {
        return this.photos.contains(p);
    }

    /**
     * This method removes a photograph to the set of the photograph container's photos if the photo exists in the photograph container's photos. If it
     * was removed, the method returns true, and if not removed, false.
     * 
     * @param p The photograph to be removed to the photograph container's photo set
     * @return Whether the photograph was removed or not
     */
    public boolean removePhoto(Photograph p) {
        return this.photos.remove(p);
    }

    /**
     * This method returns the number of photographs in the photograph container's set of photos.
     * 
     * @return The number of photographs
     */
    public int numPhotographs() {
        return this.photos.size();
    }
    
    /**
     * Gets a list of photos which have a rating higher than or equal to the rating in the argument. If the given rating is
     * invalid, the method returns null. If no photo has a rating higher than given one, an empty list is returned.
     * 
     * @param rating The rating where each photo in the list returned has to have a rating greater than or equal to it.
     * @return the list of photos which have a rating equal to or greater than the rating given
     */
    public ArrayList<Photograph> getPhotos(int rating) {
        ArrayList<Photograph> ratedPhotos = new ArrayList<Photograph>();
        if (rating < 0 || rating > 5) {
            return null;
        }
        for (Photograph p : this.photos) {
            if (p.getRating() >= rating) {
                ratedPhotos.add(p);
            }
        }
        return ratedPhotos;
    }

    /**
     * Gets a list of photos which were taken in the given year. A year formatted incorrectly would return null. If no photo
     * was taken in the given year, an empty list is returned.
     * 
     * @param year The given year where each photo in the list returned must have been taken in that year
     * @return the list of photos which were taken in the given year
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> photosInYear = new ArrayList<Photograph>();
        if (year < 1000 || year > 9999) { // we want the year to have a valid value
            return null;
        }
        for (Photograph p : this.photos) {
            if (Integer.parseInt(p.getDateTaken().substring(0, 4)) == year) { // this checks if the photo's year and the given
                                                                              // year are the same
                photosInYear.add(p);
            }
        }
        return photosInYear;
    }

    /**
     * Gets a list of photos which were taken in the given month and year. Any incorrectly formatted parameter, whether
     * month or year, should make the method return null. If none of the photos were taken in the given month, an empty list
     * is returned.
     * 
     * @param month The given month where each photo in the list returned must have been taken in that month
     * @param year  The given year where each photo in the list returned must have been taken in that year
     * @return the list of photos which were taken in the given month and year
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> photosInMonth = new ArrayList<Photograph>();
        if (year < 1000 || year > 9999 || month < 1 || month > 12) {
            return null;
        }
        for (Photograph p : this.photos) {
            if (Integer.parseInt(p.getDateTaken().substring(0, 4)) == year
                    && Integer.parseInt(p.getDateTaken().substring(5, 7)) == month) { // this checks if the photo's year and month
                                                                                      // and the given year and month are the same
                photosInMonth.add(p);
            }
        }
        return photosInMonth;
    }

    /**
     * Gets a list of photos which were taken between the beginning and end dates in the parameters (inclusive). If the
     * given dates were formatted incorrectly, or the end date is before the beginning date, the method returns null, and if
     * none of the photos were taken between these dates, the method returns an empty list.
     * 
     * @param beginDate The given beginning date where all photos in the returned list are taken after this date, inclusive
     * @param endDate   The given end date where all photos in the returned list are taken before this date, inclusive
     * @return the list of photos which were taken between the given dates
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        ArrayList<Photograph> photosBetween = new ArrayList<Photograph>();
        if (this.checkDate(beginDate) != true || this.checkDate(endDate) != true) { // We want to ensure that the dates are in the
                                                                                    // proper formats.
            return null;
        }
        if (this.firstGreaterThanOrEqualToSecond(endDate, beginDate) != true) { // The end date must be greater than or equal to
                                                                                // the beginning date.
            return null;
        }
        for (Photograph p : this.photos) { // This will iterate through each photo and see if its date is between the boundary
                                           // dates.
            if (this.firstGreaterThanOrEqualToSecond(p.getDateTaken(), beginDate) == true
                    && this.firstGreaterThanOrEqualToSecond(endDate, p.getDateTaken())) {
                photosBetween.add(p);
            }
        }
        return photosBetween;
    }
    
    /**
     * This is a private helper method which checks a date to see if it is a valid date. If the date is not valid, the
     * method returns false. If the date is valid, the method returns true.
     * 
     * @param date The input string which will be checked to see if it is a proper date
     * @return Whether or not the date is a proper format
     */
    private boolean checkDate(String date) {
        // This method will use a three-step check to ensure that the date is a valid format, and that it is logical.
        if (date.length() != 10 || date.indexOf("-") != 4 || date.lastIndexOf("-") != 7) { // Step one: is the string a valid date
                                                                                           // format?
            return false;
        }
        int year = Integer.parseInt(date.substring(0, 4)); // let's partition our string into three integers: a year, month and
                                                           // day
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        if (year < 1901 || year > 9999 || month < 1 || month > 12 || day < 1 || day > 31) { // Step two: Does the year, month, and
                                                                                            // date have logical values?
            return false;
        }
        // Step three: we want to make sure that for each month, the day range is appropriate.
        if (month == 2) { // February has 28 days.
            if (day < 1 || day > 28) {
                return false;
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) { // April, June, September and November have 30 days.
            if (day < 1 || day > 30) {
                return false;
            }
        }
        // We don't have to check for the other months, since we ensured that the days don't exceed 31 in step 2.
        return true;
    }

    /**
     * This is a private helper method which checks to see if the date in the first argument is greater than the date in the
     * second argument. If the first date is greater, the method returns true. If the second date is greater, the method
     * returns false.
     * 
     * @param firstDate  The first input date, which is a string, to be compared to the other to see if the former is
     *                   greater than the latter
     * @param secondDate The second input date, which is a string, to be compared to the first.
     * @return Whether the first date is greater than the second date
     */
    private boolean firstGreaterThanOrEqualToSecond(String firstDate, String secondDate) {
        if (this.checkDate(firstDate) == true || this.checkDate(secondDate) == true) {
            int firstYear = Integer.parseInt(firstDate.substring(0, 4)); // let's partition our date strings into three integers:
                                                                         // a year, month and day
            int secondYear = Integer.parseInt(secondDate.substring(0, 4));
            int firstMonth = Integer.parseInt(firstDate.substring(5, 7));
            int secondMonth = Integer.parseInt(secondDate.substring(5, 7));
            int firstDay = Integer.parseInt(firstDate.substring(8, 10));
            int secondDay = Integer.parseInt(secondDate.substring(8, 10));
            if (firstYear < secondYear) { // Is the year in the first argument less than than the year in the second argument?
                                          // Must be our first check.
                return false;
            } else if (firstYear > secondYear) { // If the first year is greater than the second year, then the method should
                                                 // automatically return true.
                return true;
            } else { // What if the two years are equal? We need to check for months.
                if (firstMonth < secondMonth) { // Is the month in the first argument less than than the month in the second
                                                // argument?
                    return false;
                } else if (firstMonth > secondMonth) { // If the first month is greater than the second month, then the method
                                                       // should automatically return true.
                    return true;
                } else { // What if the two months are equal? We need to check for days.
                    if (firstDay < secondDay) { // Is the day in the first argument less than than the day in the second argument?
                                                // Must be our first check.
                        return false;
                    } else { // If the two days are equal or the first day is greater than the second, return true!
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * This method tests the equality of two photograph container objects by comparing their name values.
     * 
     * @param o The photograph container object (if it is one) whose name is to be compared to the current photograph container object's name
     * @return Whether the two objects have the same name values
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof PhotographContainer) {
            PhotographContainer p = (PhotographContainer) o;
            if (this.name.equals(p.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method provides a string representation of the photograph container.
     * 
     * @return the String representation of the photograph container, which shows the photograph container's name as well as the filenames for each
     *         individual photo it has.
     */
    public String toString() {
        String s = this.name + "\n";
        for (Photograph p : this.photos) {
            s += p.getFilename() + "\n";
        }
        return s;
    }

    /**
     * This method returns the hashcode of the photograph container's name
     * 
     * @return the hashcode of the the photograph container's name
     */
    public int hashCode() {
        return this.name.hashCode();
    }
    
}
