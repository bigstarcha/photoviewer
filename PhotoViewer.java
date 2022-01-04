/**
 * Homework 4
 * Timothy Cha, thc8pku
 * 
 * Sources: Lecture, TA Office Hours, zetcode.com, and StackOverflow
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

public class PhotoViewer extends JFrame {
    
    /**
     * Below are all the instance fields to create the GUI (Buttons, frame, panels, etc.)
     */
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel imagePanel;
    private JPanel thumbnailPanel;
    private JPanel ratingPanel;
    private JButton previous;
    private JButton next;
    private JButton exit;
    private ButtonGroup sortButtons;
    private JRadioButton sortByDateButton;
    private JRadioButton sortByRatingButton;
    private JRadioButton sortByCaptionButton;
    private ButtonGroup rateButtons;
    private JRadioButton one;
    private JRadioButton two;
    private JRadioButton three;
    private JRadioButton four;
    private JRadioButton five;
    private JLabel sortBy;
    private JLabel firstImage;
    private JLabel secondImage;
    private JLabel thirdImage;
    private JLabel fourthImage;
    private JLabel fifthImage;
    private JLabel displayImage; // main image?
    private JLabel rating;
    private ImageIcon first;
    private ImageIcon second;
    private ImageIcon third;
    private ImageIcon fourth;
    private ImageIcon fifth;
    
    /**
     * This is a private variable which denotes the index of the image currently in display.
     */
    private int imageIndex;

    /**
     * A field which stores the library of images
     */
    private PhotoLibrary imageLibrary;
    
    /**
     * Photoviewer constructor which initializes the private GUI variables (JFrame, JLabels, JButtons, etc.)
     */
    public PhotoViewer() {
        frame = new JFrame();
        buttonPanel = new JPanel();
        imagePanel = new JPanel();
        thumbnailPanel = new JPanel();
        ratingPanel = new JPanel();
        previous = new JButton("Previous");
        next = new JButton("Next");
        
        exit = new JButton("exit");
        
        sortButtons = new ButtonGroup();
        sortBy = new JLabel("Sort by:");
        sortByDateButton = new JRadioButton("Date");
        sortByRatingButton = new JRadioButton("Rating");
        sortByCaptionButton = new JRadioButton("Caption");
        one = new JRadioButton("1");
        rateButtons = new ButtonGroup();
        two = new JRadioButton("2");
        three = new JRadioButton("3");
        four = new JRadioButton("4");
        five = new JRadioButton("5");
        firstImage = new JLabel();
        secondImage = new JLabel();
        thirdImage = new JLabel();
        fourthImage = new JLabel();
        fifthImage = new JLabel();
        displayImage = new JLabel();
        rating = new JLabel("Rate the photo:");
        
        // the default image index will be zero
        imageIndex = 0;
    }
    
    /**
     * This method adds an ActionEvent to each button to give them function.
     */
    
    /**
     * This method adds an ActionEvent to each button to give them function, adds
     * each component to its respective panel, adds each panel to the frame,
     * and executes the GUI to make the JFrame and its components visible.
     */
    public void go() {
        
        // Each actionListener and mouseListener method has the rating() method to update the displayed image's rating every time.
        
        // Adding the action listeners to each button
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image goes backward by one index
                if (imageIndex < 0 || imageIndex > 4) {
                    return;
                }
                else if (imageIndex == 0) {
                    imageIndex = 4;
                }
                else {
                    imageIndex--;
                }
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(imageIndex).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                rating();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image goes forward by one index
                if (imageIndex < 0 || imageIndex > 4) {
                    return;
                }
                else if (imageIndex == 4) {
                    imageIndex = 0;
                }
                else {
                    imageIndex++;
                }
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(imageIndex).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                rating();
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        sortByDateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image thumbnails are sorted by date
                Collections.sort(imageLibrary.photos);
                first = new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                second = new ImageIcon(imageLibrary.photos.get(1).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                third = new ImageIcon(imageLibrary.photos.get(2).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                fourth = new ImageIcon(imageLibrary.photos.get(3).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                fifth = new ImageIcon(imageLibrary.photos.get(4).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                firstImage.setIcon(first);
                firstImage.setText(caption(0));
                secondImage.setIcon(second);
                secondImage.setText(caption(1));
                thirdImage.setIcon(third);
                thirdImage.setText(caption(2));
                fourthImage.setIcon(fourth);
                fourthImage.setText(caption(3));
                fifthImage.setIcon(fifth);
                fifthImage.setText(caption(4));
                
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 0;
                rating();
            }
        });
        sortByRatingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image thumbnails are sorted by rating
                Collections.sort(imageLibrary.photos, new CompareByRating());
                first = new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                second = new ImageIcon(imageLibrary.photos.get(1).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                third = new ImageIcon(imageLibrary.photos.get(2).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                fourth = new ImageIcon(imageLibrary.photos.get(3).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                fifth = new ImageIcon(imageLibrary.photos.get(4).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                firstImage.setIcon(first);
                firstImage.setText(caption(0));
                secondImage.setIcon(second);
                secondImage.setText(caption(1));
                thirdImage.setIcon(third);
                thirdImage.setText(caption(2));
                fourthImage.setIcon(fourth);
                fourthImage.setText(caption(3));
                fifthImage.setIcon(fifth);
                fifthImage.setText(caption(4));
                
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 0;
                rating();
            }
        });
        sortByCaptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image thumbnails are sorted by caption
                Collections.sort(imageLibrary.photos, new CompareByCaption());
                first = new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                second = new ImageIcon(imageLibrary.photos.get(1).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                third = new ImageIcon(imageLibrary.photos.get(2).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                fourth = new ImageIcon(imageLibrary.photos.get(3).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                fifth = new ImageIcon(imageLibrary.photos.get(4).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                firstImage.setIcon(first);
                firstImage.setText(caption(0));
                secondImage.setIcon(second);
                secondImage.setText(caption(1));
                thirdImage.setIcon(third);
                thirdImage.setText(caption(2));
                fourthImage.setIcon(fourth);
                fourthImage.setText(caption(3));
                fifthImage.setIcon(fifth);
                fifthImage.setText(caption(4));
                
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 0;
                rating();
            }
        });
        one.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image is given a rating of one
                imageLibrary.photos.get(imageIndex).setRating(1);
                for (int i = 0; i < imageLibrary.photos.size(); i++) {
                    // Check to see if which image in the thumbnail list is the same as the current image
                    if (imageLibrary.photos.get(i).getImageData().equals(imageLibrary.photos.get(imageIndex).getImageData())) {
                        switch (i) {
                            case 0:
                                firstImage.setText(caption(0));
                                break;
                            case 1:
                                secondImage.setText(caption(1));
                                break;
                            case 2:
                                thirdImage.setText(caption(2));
                                break;
                            case 3:
                                fourthImage.setText(caption(3));
                                break;
                            case 4:
                                fifthImage.setText(caption(4));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
        two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image is given a rating of two
                imageLibrary.photos.get(imageIndex).setRating(2);
                for (int i = 0; i < imageLibrary.photos.size(); i++) {
                    // Check to see if which image in the thumbnail list is the same as the current image
                    if (imageLibrary.photos.get(i).getImageData().equals(imageLibrary.photos.get(imageIndex).getImageData())) {
                        switch (i) {
                            case 0:
                                firstImage.setText(caption(0));
                                break;
                            case 1:
                                secondImage.setText(caption(1));
                                break;
                            case 2:
                                thirdImage.setText(caption(2));
                                break;
                            case 3:
                                fourthImage.setText(caption(3));
                                break;
                            case 4:
                                fifthImage.setText(caption(4));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
        three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image is given a rating of three
                imageLibrary.photos.get(imageIndex).setRating(3);
                for (int i = 0; i < imageLibrary.photos.size(); i++) {
                    // Check to see if which image in the thumbnail list is the same as the current image
                    if (imageLibrary.photos.get(i).getImageData().equals(imageLibrary.photos.get(imageIndex).getImageData())) {
                        switch (i) {
                            case 0:
                                firstImage.setText(caption(0));
                                break;
                            case 1:
                                secondImage.setText(caption(1));
                                break;
                            case 2:
                                thirdImage.setText(caption(2));
                                break;
                            case 3:
                                fourthImage.setText(caption(3));
                                break;
                            case 4:
                                fifthImage.setText(caption(4));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
        four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image is given a rating of four
                imageLibrary.photos.get(imageIndex).setRating(4);
                for (int i = 0; i < imageLibrary.photos.size(); i++) {
                    // Check to see if which image in the thumbnail list is the same as the current image
                    if (imageLibrary.photos.get(i).getImageData().equals(imageLibrary.photos.get(imageIndex).getImageData())) {
                        switch (i) {
                            case 0:
                                firstImage.setText(caption(0));
                                break;
                            case 1:
                                secondImage.setText(caption(1));
                                break;
                            case 2:
                                thirdImage.setText(caption(2));
                                break;
                            case 3:
                                fourthImage.setText(caption(3));
                                break;
                            case 4:
                                fifthImage.setText(caption(4));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
        five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // the image is given a rating of five
                imageLibrary.photos.get(imageIndex).setRating(5);
                for (int i = 0; i < imageLibrary.photos.size(); i++) {
                    // Check to see if which image in the thumbnail list is the same as the current image
                    if (imageLibrary.photos.get(i).getImageData().equals(imageLibrary.photos.get(imageIndex).getImageData())) {
                        switch (i) {
                            case 0:
                                firstImage.setText(caption(0));
                                break;
                            case 1:
                                secondImage.setText(caption(1));
                                break;
                            case 2:
                                thirdImage.setText(caption(2));
                                break;
                            case 3:
                                fourthImage.setText(caption(3));
                                break;
                            case 4:
                                fifthImage.setText(caption(4));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
        
        
        // Adding each of the sort buttons and labels to the button panel
        buttonPanel.add(previous);
        buttonPanel.add(next);
        buttonPanel.add(exit);
        // sortButtons adds the buttons to itself to ensure that only one is clicked when the GUI is launched
        sortButtons.add(sortByDateButton);
        sortButtons.add(sortByRatingButton);
        sortButtons.add(sortByCaptionButton);
        buttonPanel.add(sortBy);
        buttonPanel.add(sortByDateButton);
        buttonPanel.add(sortByRatingButton);
        buttonPanel.add(sortByCaptionButton);
        // Adding each of the rating buttons and labels to the rating panel
        // rateButtons adds the buttons to itself to ensure that only one is clicked when the GUI is launched
        ratingPanel.add(rating);
        rateButtons.add(one);
        rateButtons.add(two);
        rateButtons.add(three);
        rateButtons.add(four);
        rateButtons.add(five);
        ratingPanel.add(one);
        ratingPanel.add(two);
        ratingPanel.add(three);
        ratingPanel.add(four);
        ratingPanel.add(five);
        
        // Before we add the thumbnails to the thumbnail panel, we need to set the thumbnails to their
        // corresponding images first.
        // Sort the images by date, since that is their default order.
        Collections.sort(imageLibrary.photos);
        first = new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        second = new ImageIcon(imageLibrary.photos.get(1).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        third = new ImageIcon(imageLibrary.photos.get(2).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        fourth = new ImageIcon(imageLibrary.photos.get(3).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        fifth = new ImageIcon(imageLibrary.photos.get(4).getImageData().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        firstImage = new JLabel(first);
        firstImage.setText(caption(0));
        secondImage = new JLabel(second);
        secondImage.setText(caption(1));
        thirdImage = new JLabel(third);
        thirdImage.setText(caption(2));
        fourthImage = new JLabel(fourth);
        fourthImage.setText(caption(3));
        fifthImage = new JLabel(fifth);
        fifthImage.setText(caption(4));
        
        // Adding the mouse listeners to each icon
        firstImage.addMouseListener(new MouseAdapter() {
            // Each mouseClicked method changes the current image to the clicked thumbnail image.
            public void mouseClicked(MouseEvent e) {
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(0).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 0;
                rating();
            }
        });
        secondImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(1).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 1;
                rating();
            }
        });
        thirdImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(2).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 2;
                rating();
            }
        });
        fourthImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(3).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 3;
                rating();
            }
        });
        fifthImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayImage.setIcon(new ImageIcon(imageLibrary.photos.get(4).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
                imageIndex = 4;
                rating();
            }
        });
        
        
        // Adding each of the thumbnail components to the thumbnail panel
        thumbnailPanel.add(firstImage);
        thumbnailPanel.add(secondImage);
        thumbnailPanel.add(thirdImage);
        thumbnailPanel.add(fourthImage);
        thumbnailPanel.add(fifthImage);
        // Adding the main image to the display panel
        displayImage = new JLabel(new ImageIcon(imageLibrary.photos.get(imageIndex).getImageData().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        imagePanel.add(displayImage);
        rating();
        
        // Adding all panels to the frame
        frame.add(buttonPanel, BorderLayout.PAGE_START);
        frame.add(thumbnailPanel, BorderLayout.PAGE_END);
        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(ratingPanel, BorderLayout.LINE_END);
        
        // Make the frame visible and make its close operation exit
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(850, 850);
        frame.setVisible(true);
    }
    
    /**
     * This is a private helper method to access the captions of each thumbnail
     */
    private String caption(int i) {
        return ("<html>" + imageLibrary.photos.get(i).getCaption() + "<br/>" +
                imageLibrary.photos.get(i).getDateTaken() + "<br/>" +
                "Rating: " +
                imageLibrary.photos.get(i).getRating() + "<html>");
    }
    
    /**
     * This is a private helper method to show the rating of the display image
     */
    private void rating() {
        switch (imageLibrary.photos.get(imageIndex).getRating()) {
            case 1:
                one.setSelected(true);
                break;
            case 2:
                two.setSelected(true);
                break;
            case 3:
                three.setSelected(true);
                break;
            case 4:
                four.setSelected(true);
                break;
            case 5:
                five.setSelected(true);
                break;
            default:
                break;
        }
    }

    /**
     * Gets the library of images from the photo viewer
     * 
     * @return The image library
     */
    public PhotoLibrary getImageLibrary() {
        return imageLibrary;
    }

    /**
     * Sets the image library of the photo viewer
     * 
     * @param imageLibrary The new image library for the imageLibrary variable
     */
    public void setImageLibrary(PhotoLibrary imageLibrary) {
        this.imageLibrary = imageLibrary;
    }
    
    public static void main(String[] args) {
        // Creating a new photo library
        PhotoLibrary tim = new PhotoLibrary("Timothy", 25);
        // Create five new photos and add them to the photo library
        Photograph dad = new Photograph("Dad", "images\\dad.jpg", "2007-07-05", 4);
        Photograph mom = new Photograph("Mom", "images\\mom.jpg", "2003-08-30", 3);
        Photograph sister = new Photograph("Little sister", "images\\sister.jpg", "2015-04-11", 2);
        Photograph littleTim = new Photograph("Little Tim", "images\\littleTim.jpg", "2015-04-11", 2);
        Photograph timNow = new Photograph("Tim", "images\\timNow.jpg", "2019-10-19", 5);
        dad.loadImageData("images\\dad.jpg");
        mom.loadImageData("images\\mom.jpg");
        sister.loadImageData("images\\sister.jpg");
        littleTim.loadImageData("images\\littleTim.jpg");
        timNow.loadImageData("images\\timNow.jpg");
        tim.addPhoto(dad);
        tim.addPhoto(mom);
        tim.addPhoto(sister);
        tim.addPhoto(littleTim);
        tim.addPhoto(timNow);
        
        // Create the photo viewer object and launch the GUI
        PhotoViewer p = new PhotoViewer();
        p.setImageLibrary(tim);
        p.go();
    }

}