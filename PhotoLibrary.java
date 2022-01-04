/**
 * Homework 4
 * Timothy Cha, thc8pku
 * 
 * Sources: Lecture and TA Office Hours
 */

import java.util.ArrayList;
import java.util.HashSet;

public class PhotoLibrary extends PhotographContainer {

    /**
     * A field which stores the photo library ID. An ID is unique for each photo library.
     */
    private final int id;
    /**
     * A HashSet of albums the user has created in their photo library.
     */
    private HashSet<Album> albums;

    /**
     * Constructor that makes a new PhotoLibrary object
     * 
     * @param name The name of the new photo library
     * @param id   The ID of the new photo library
     */
    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
        this.albums = new HashSet<Album>();
    }

    /**
     * Gets the ID of the photo library
     * 
     * @return the ID of the photo library
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the albums of the photo library
     * 
     * @return a reference to the photo library's albums
     */
    public HashSet<Album> getAlbums() {
        return albums;
    }

    /**
     * This method removes a photo to the photo library's list of photos if the photo is in the list. This method returns
     * true if the photo was removed, and otherwise false if not.
     * 
     * @param p The photograph to be erased from the photo library
     * @return Whether or not the photo was erased
     */
    @Override
    public boolean removePhoto(Photograph p) {
        boolean b1 = false;
        boolean b2 = false;
        for (Album a : this.albums) {
            if (a.getPhotos().contains(p)) {
                a.getPhotos().remove(p);
                b1 = true;
            }
        }
        if (photos.contains(p)) {
            photos.remove(p);
            b2 = true;
        }
        return (b1 && b2);
    }

    /**
     * This method creates an album with the given name in the parameter to the photo library's list of albums, only if the
     * album was not already in the list. This method returns true if the album was added, and otherwise false if not.
     * 
     * @param p The photograph to be added to the photo library
     * @return Whether or not the photo was added
     */
    public boolean createAlbum(String albumName) {
        Album a = new Album(albumName);
        return this.albums.add(a);
    }

    /**
     * This method removes an album with the given name in the parameter from the photo library if that album was in the
     * photo library. The method returns true if the photo was removed, and otherwise false if not.
     * 
     * @param p The photograph to be erased from the photo library
     * @return Whether or not the photo was erased
     */
    public boolean removeAlbum(String albumName) {
        Album a = this.getAlbumByName(albumName);
        return this.albums.remove(a);
    }

    /**
     * This method adds a photograph to the album in the photo library's album set with the album name in the given
     * parameter if the photo is in the Photolibrary's list of photos and not already in the album. If the photograph was
     * added, the method returns true, and if not, false.
     * 
     * @param p         The photograph to be added to the designated album
     * @param albumName The name of the album where the photo is to be added to
     * @return Whether or not the photo was added to the album
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        if (p == null) {
            return false;
        }
        if (this.albums.contains(this.getAlbumByName(albumName)) == false) {
            return false;
        }
        if (this.photos.contains(p) && !(this.getAlbumByName(albumName).getPhotos().contains(p))) {
            return this.getAlbumByName(albumName).addPhoto(p);
        }
        return false;
    }

    /**
     * This method removes a photograph from the album in the photo library's album set with the album name. If the
     * photograph was removed, the method returns true, and if not, false.
     * 
     * @param p         The photograph to be removed to the designated album
     * @param albumName The name of the album where the photo is to be added to
     * @return Whether or not the photo was added to the album
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        if (this.albums.contains(this.getAlbumByName(albumName)) == false) {
            return false;
        }
        Album a = this.getAlbumByName(albumName);
        return a.removePhoto(p);
    }

    /**
     * This is a private helper method which, given the album name in the parameter, returns the album with that name from
     * the photo library's album set. The method returns null if the album was not found.
     * 
     * @param albumName The name which the album with that name is to be returned
     * @return the album which has the given name
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                return a;
            }
        }
        return null;
    }

    /**
     * This method checks that two PhotoLibrary objects are equal by comparing their ID values. If the ID values are the
     * same, this means that they represent the same photograph.
     * 
     * @param o the PhotoLibrary object (if it is one) where its ID is to be compared to the current PhotoLibrary object's
     *          ID
     * @return Whether or not the PhotoLibrary object in the argument has the same ID value as the current PhotoLibrary
     *         object.
     */
    public boolean equals(Object o) {
        // Check that Object o is not null
        if (o == null) {
            return false;
        }
        // Check if o is of type PhotoLibrary
        if (o instanceof PhotoLibrary) {
            PhotoLibrary p = (PhotoLibrary) o;
            // Compare the two ID values to see if they are equal, and if so, return true
            if (p.id == this.id) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a string representation of the photo library, which shows the values of the photo library name,
     * ID, and photos.
     * 
     * @return the string representation of the photo library.
     */
    public String toString() {
        String s = name + "\n" + id + "\n" + "\n";
        for (Photograph p : photos) {
            s = s + p.toString() + "\n";
        }
        s += "\n";
        for (Album a : albums) {
            s = s + a.getName() + "\n";
        }
        return s;
    }

    /**
     * This method returns an ArrayList which contains the photos that both PhotoLibrary a and PhotoLibrary b have in
     * common.
     * 
     * @param a One of the photo libraries that will be compared to the other for common photos
     * @param b The other of the photo libraries that will be compared the first one for common photos
     * @return an ArrayList which has the photos that both PhotoLibrary a and PhotoLibrary b have in common
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> p = new ArrayList<Photograph>();
        for (Photograph p1 : a.photos) {
            for (Photograph p2 : b.photos) {
                if (p1.equals(p2)) {
                    p.add(p2);
                }
            }
        }
        return p;
    }

    /**
     * This method measures how similar photo feeds are between two PhotoLibrary objects and bases it on a value from 0 to
     * 1.
     * 
     * @param a The first photo library
     * @param b The second photo library
     * @return the number of common photos divided by the number of photos in the photo library with the smaller number of
     *         photos. If either photo library has zero photos, the method returns 0.0.
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        if (a.photos.size() == 0 || b.photos.size() == 0) {
            return 0.0;
        }
        int small; // This is the number of photos in the smaller photo library.
        if (a.photos.size() < b.photos.size()) { // This is where the TA's helped me figure out what the method was dividing the
                                                 // number of common photos by
            small = a.photos.size();
        } else {
            small = b.photos.size();
        }
        double d = ((double) commonPhotos(a, b).size() / small); // Here we perform the calculation to measure similarity of photo
                                                                 // feeds
        return d;
    }

    public static void main(String[] args) {
        PhotoLibrary zach = new PhotoLibrary("Zachary Myers", 23);
        Photograph eighteen = new Photograph("I turned 18 today!", "Eighteen", "2019-08-28", 5);
        Photograph prom = new Photograph("Prom 2019!", "Prom", "2019-04-27", 5);
        Photograph ugly = new Photograph("Me being ugly in a new state.", "Ugly", "2017-04-15", 2);
        Photograph starWars = new Photograph("YES!!!", "Star Wars", "2015-12-19", 3);
        Photograph beach = new Photograph("Beach with Ryan", "Beach", "2015-07-05", 4);
        Photograph cold = new Photograph("I'm cold", "Cold", "2016-12-23", 4);
        zach.addPhoto(eighteen);
        zach.addPhoto(prom);
        zach.addPhoto(ugly);
        zach.addPhoto(starWars);
        zach.addPhoto(beach);
        zach.addPhoto(cold);
        ArrayList<Photograph> foo = zach.getPhotosBetween("2016-01-01", "2019-09-01");
        for (Photograph p : foo) {
            System.out.println(p.toString());
        }
    }

}
