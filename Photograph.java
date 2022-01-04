/**
 * Homework 4
 * Timothy Cha, thc8pku
 * 
 * Sources: Lecture and TA Office Hours
 */

import java.lang.Integer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Photograph implements Comparable<Photograph> {

    /**
     * A field which stores the filename of the photo
     */
    private String filename;
    /**
     * A field which stores the caption of the photo
     */
    private String caption;
    /**
     * A field which stores the date the photo was taken, in a YYYY-MM-DD format
     */
    private String dateTaken;
    /**
     * A field which stores the rating of the photograph on a scale from 0 to 5
     */
    private int rating;
    /**
     * A field which stores the image data of the photograph
     */
    private BufferedImage imageData;

    /**
     * Constructor that makes a new Photograph object
     * 
     * @param filename The name of the new photograph
     * @param caption  The caption of the new photograph
     */
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        this.dateTaken = "1901-01-01";
        this.rating = 0;
    }

    /**
     * Constructor that makes a new Photograph object, but also takes parameters for rating and the date taken
     * 
     * @param caption   The caption of the new photograph
     * @param filename  The name of the new photograph
     * @param dateTaken The date the photograph was taken, in the format YYYY-MM-DD
     * @param rating    The rating of the photograph, in a scale from 0 to 5
     */
    public Photograph(String caption, String filename, String dateTaken, int rating) {
        this.caption = caption;
        this.filename = filename;
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            this.rating = 0;
        }
        if (dateTaken.length() == 10 && dateTaken.indexOf("-") == 4 && dateTaken.lastIndexOf("-") == 7) {
            try {
                int year = Integer.parseInt(dateTaken.substring(0, 4));
                int month = Integer.parseInt(dateTaken.substring(5, 7));
                int day = Integer.parseInt(dateTaken.substring(8, 10));
                if (year < 1901 || year > 2019) { // Does the year have a logical value?
                    this.dateTaken = "1901-01-01";
                    return;
                }
                if (month < 1 || month > 12) { // Does the month have a logical value?
                    this.dateTaken = "1901-01-01";
                    return;
                }
                if (day < 1 || day > 31) { // Does the day have a logical value?
                    this.dateTaken = "1901-01-01";
                    return;
                }
                // Are the days appropriate for each month?
                if (month == 2) { // February has 28 days.
                    if (day < 1 || day > 28) {
                        this.dateTaken = "1901-01-01";
                        return;
                    }
                    else {
                        this.dateTaken = dateTaken;
                        return;
                    }
                }
                else if (month == 4 || month == 6 || month == 9 || month == 11) { // April, June, September and November have 30 days.
                    if (day < 1 || day > 30) {
                        this.dateTaken = "1901-01-01";
                        return;
                    }
                    else {
                        this.dateTaken = dateTaken;
                        return;
                    }
                }
                else {
                    this.dateTaken = dateTaken;
                    return;
                }
            }
            catch (NumberFormatException e) {
                this.dateTaken = "1901-01-01";
                return;
            }
        } else {
            this.dateTaken = "1901-01-01";
            return;
        }
    }
    
    // Check this code and see if it should be good, or not.
    /**
     * This method loads image data from the file which has the filename given in the parameter, and stores it
     * in the imageData field, while the filename parameter is stored in the Photograph's filename field. If
     * the image is successfully added, the method returns true, and otherwise false.
     * The try-catch block is added in case the file is not found or there is an input-output exception.
     * 
     * @param filename The name of the file of the image where its data is to be stored in imageData.
     * @return Whether the image was successfully stored into imageData.
     */
    public boolean loadImageData(String filename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        }
        catch (FileNotFoundException f) {
            f.printStackTrace();
            return false;
        }
        catch (IOException i) {
            i.printStackTrace();
            return false;
        }
        imageData = image;
        this.filename = filename;
        return true;
    }

    /**
     * Gets the filename of the photograph
     * 
     * @return the filename of the photograph
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Gets the caption of the photograph
     * 
     * @return the caption of the photograph
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Gets the date taken of the photograph
     * 
     * @return the date taken of the photograph
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     * Gets the rating of the photograph
     * 
     * @return the rating of the photograph
     */
    public int getRating() {
        return rating;
    }
    
    /**
     * Gets the image data of the photograph as a BufferedImage
     * 
     * @return the image's data as a BufferedImage
     */
    public BufferedImage getImageData() {
        return imageData;
    }

    /**
     * Sets the caption of the photograph
     * 
     * @param caption The new caption of the photograph
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Sets the rating of the photograph
     * 
     * @param rating The new rating of the photograph
     */
    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            this.rating = 0;
        }
    }

    // Do you use a string filename in the parameter or the actual BufferedImage in the parameter?
    /**
     * Sets the image data of the photograph
     * 
     * @param imageData The new image's data
     */
    public void setImageData(BufferedImage imageData) {
        this.imageData = imageData;
    }
    
    /**
     * This method compares two photographs based off of their dates. The method returns a negative number if
     * the current photograph has an earlier date than the photograph in the parameter, and positive if
     * vice versa. If the two photographs have the same date, the method compares the two dates based off of
     * their captions.
     * 
     * @param p The photograph whose date is to be compared to the current photograph's date
     * @return The comparison value between the two photographs
     */
    @Override
    public int compareTo(Photograph p) {
        if (this.dateTaken.equals(p.dateTaken)) {
            return this.caption.compareTo(p.caption);
        }
        return this.dateTaken.compareTo(p.dateTaken);
    }
    
    /**
     * This method checks that two Photograph objects are equal by comparing their filenames and captions. If the filenames
     * and captions are the same, this means that they are the same photograph.
     * 
     * @param o the Photograph object (if it is one) where its filename and caption is to be compared to the current
     *          Photograph object's filename and caption
     * @return Whether or not the Photograph object in the argument has the same filename and caption as the current
     *         Photograph object.
     */
    @Override
    public boolean equals(Object o) {
        // Check that Object o is not null
        if (o == null) {
            return false;
        }
        // Check if o is of type Photograph
        if (o instanceof Photograph) {
            Photograph p = (Photograph) o;
            // Compare the two filenames and two captions, and if both are equal to each other respectively then return true
            if (p.filename.equals(this.filename) && p.caption.equals(this.caption)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a string representation of the photograph, which shows the values of the filename and caption.
     * 
     * @return the string representation of the photograph.
     */
    @Override
    public String toString() {
        return (filename + "\n" + caption);
    }

    /**
     * This method returns the hashcode of the photograph's file name
     * 
     * @return the hashcode of the the photograph's filename
     */
    @Override
    public int hashCode() {
        return this.filename.hashCode();
    }

    /**
     * This method will run test cases.
     */
    public static void main(String[] args) {
        Photograph p = new Photograph("foo", "foo", "2019-00-45", 3);
        System.out.println(p.getDateTaken());
    }

}
