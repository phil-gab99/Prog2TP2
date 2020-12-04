package mvc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
* The class SearchView indicates how the first main window of the application
* is configured and offers plenty of methods for graphical elements
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class SearchView extends JFrame {

    private static final int FRAME_WIDTH = 800;  //Default frame width
    private static final int FRAME_HEIGHT = 450; //Default frame height

    //Acquiring screen dimensions
    private static final Toolkit screen = Toolkit.getDefaultToolkit();
    private static final Dimension d = screen.getScreenSize();

    //Saving error message value
    public static final int ERROR = JOptionPane.ERROR_MESSAGE;

    private SearchModel model;     //Model used for handling events triggered
    private SearchControl control; //Control directing where events lead

    JDialog dialog;             //Dialog used for various user input contexts
    JFrame searchResult;        //Frame holding user search results
    JTextArea indexList;        //Text Area for indexation list
    JTextArea indexListRev;     //Text Area for reverse indexation list
    JTextArea searchResultList; //Text Area for search results list
    JTextField wordQuery;       //Textfield for user search input

    /**
    * The constructor method SearchView generates the main frame of the
    * application and holds the straight and reversed indexation lists as well
    * as some buttons for certain options
    ***/

    public SearchView() {

        //The model and control are instantiated
        model = new SearchModel(this);
        control = new SearchControl(model);

        //Configuring frame layout, size and location
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        centerComponent(this, 0);

        //Instantiating and configuring indexation list area
        indexList = new JTextArea();
        JScrollPane scrollL = new JScrollPane(indexList);
        JLabel headL = new JLabel("Indexation List", JLabel.LEFT);
        headL.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollL.setColumnHeaderView(headL);
        indexList.setMargin(new Insets(5, 20, 5, 20));
        indexList.setEditable(false);

        //Instantiating and configuring reverse indexation list area
        indexListRev = new JTextArea();
        JScrollPane scrollLR = new JScrollPane(indexListRev);
        JLabel headLR = new JLabel("Reverse Indexation List", JLabel.LEFT);
        headLR.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollLR.setColumnHeaderView(headLR);
        indexListRev.setMargin(new Insets(5, 20, 5, 20));
        indexListRev.setEditable(false);

        //Layout properties configuration
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(5, 20, 5, 20);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //Applying constraints on text areas
        gridbag.setConstraints(scrollL, c);
        gridbag.setConstraints(scrollLR, c);
        add(scrollL);
        add(scrollLR);

        //Configuring button locations
        c.weighty = 0.0;
        c.insets = new Insets(5, 20, 5, 5);
        c.gridwidth = GridBagConstraints.RELATIVE;
        makeButton(this, "Add Files", gridbag, c, 1);
        c.insets = new Insets(5, 5, 5, 20);
        c.gridwidth = GridBagConstraints.REMAINDER;
        makeButton(this, "Search", gridbag, c, 2);

        setTitle("File Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
    * The method fileChooserDialog generates the file chooser dialog upon
    * pressing the Add Files button within the main window
    ***/

    public void fileChooserDialog() {

        JFileChooser fileDialog = new JFileChooser("./res");
        fileDialog.setDialogTitle("Select Files");
        fileDialog.setMultiSelectionEnabled(true);
        int option = fileDialog.showDialog(this, "Select");

        if (option == JFileChooser.APPROVE_OPTION) {

            model.updateFiles(fileDialog.getSelectedFiles());
        }
    }

    /**
    * The method searchWordsDialog generates the dialog box upon pressing the
    * Search button within the main window
    ***/

    public void searchWordsDialog() {

        //Instantiating dialog and input textfield
        dialog = new JDialog(this, "Search Words", true);
        wordQuery = new JTextField();
        wordQuery.addKeyListener(control.new KeyMixed());

        //Configuring dialog layout, size and location
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        dialog.setSize(FRAME_WIDTH/2, FRAME_HEIGHT/3);
        dialog.setLayout(gridbag);
        centerComponent(dialog, 0);

        //Textfield and button locations
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;

        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 20, 5, 5);
        makeLabel(dialog, "*Separate each entry with a comma", gridbag, c,
        SwingConstants.LEFT, Font.ITALIC, 12);

        c.gridy = 2;
        c.gridwidth = 5;
        c.insets = new Insets(5, 20, 5, 5);
        makeButton(dialog, "OK", gridbag, c, 3);
        c.insets = new Insets(5, 5, 5, 20);
        makeButton(dialog, "Cancel", gridbag, c, 4);

        c.weightx = 0.0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(5, 20, 5, 5);
        makeLabel(dialog, "Words: ", gridbag, c, SwingConstants.LEFT,
        Font.PLAIN, 14);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 5, 5, 20);
        gridbag.setConstraints(wordQuery, c);
        dialog.add(wordQuery);

        dialog.setVisible(true);
    }

    /**
    * The method addWordsDialog generates the dialog upon pressing the Add
    * Words button from within the search results frame
    ***/

    public void addWordsDialog() {

        //Instantiating dialog and input textfield
        dialog = new JDialog(this, "Add Words", true);
        wordQuery = new JTextField();
        wordQuery.addKeyListener(control.new KeyMixed());

        //Configuring dialog layout, size and location
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        dialog.setSize(FRAME_WIDTH/2, FRAME_HEIGHT/3);
        dialog.setLayout(gridbag);
        centerComponent(dialog, 0);

        //Textfield and button locations
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;

        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 20, 5, 5);
        makeLabel(dialog, "*Separate each entry with a comma", gridbag, c,
        SwingConstants.LEFT, Font.ITALIC, 12);

        c.gridy = 2;
        c.gridwidth = 5;
        c.insets = new Insets(5, 20, 5, 5);
        makeButton(dialog, "OK", gridbag, c, 6);
        c.insets = new Insets(5, 5, 5, 20);
        makeButton(dialog, "Cancel", gridbag, c, 4);

        c.weightx = 0.0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(5, 20, 5, 5);
        makeLabel(dialog, "Words: ", gridbag, c, SwingConstants.LEFT,
        Font.PLAIN, 14);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 5, 5, 20);
        gridbag.setConstraints(wordQuery, c);
        dialog.add(wordQuery);

        dialog.setVisible(true);
    }

    /**
    * The method makeResultsFrame generates the frame displaying the user's
    * search results
    ***/

    public void makeResultsFrame(String result) {

        //Instantiating frame
        searchResult = new JFrame("Search Results");

        //Configuring frame layout, size and location
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        searchResult.setLayout(gridbag);
        searchResult.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        centerComponent(searchResult, 50);

        //Instantiating and configuring results list text area
        searchResultList = new JTextArea(result);
        JScrollPane scrollRes = new JScrollPane(searchResultList);
        JLabel headRes = new JLabel("Search Result List", JLabel.LEFT);
        headRes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollRes.setColumnHeaderView(headRes);
        searchResultList.setMargin(new Insets(5, 20, 5, 20));
        searchResultList.setEditable(false);

        //Layout properties configuration
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(5, 20, 5, 20);
        c.gridwidth = GridBagConstraints.REMAINDER;

        //Applying constraints on text area
        gridbag.setConstraints(scrollRes, c);
        searchResult.add(scrollRes);

        //Configuring button locations
        c.weighty = 0.0;
        c.insets = new Insets(5, 20, 20, 5);
        makeButton(searchResult, "Add Words", gridbag, c, 5);

        searchResult.setVisible(true);
    }

    /**
    * The makeButton method creates a button to implement onto the interface
    * and assigns it a specific listener
    *
    * @param parent Container that will hold the button
    * @param name String indicating button label
    * @param gridbag GridBagLayout with parent layout details
    * @param c GridBagConstraints indicating the specific constraints and
    * location details
    * @param listenType Integer indicating which type of listener to assign to
    * the given button
    ***/

    public void makeButton(Container parent, String name,
    GridBagLayout gridbag, GridBagConstraints c, int listenType) {

        JButton button = new JButton(name);
        gridbag.setConstraints(button, c);

        switch (listenType) {

            case 1: button.addActionListener(control.new AddFiles());    break;
            case 2: button.addActionListener(control.new Search());      break;
            case 3: button.addActionListener(control.new OkSearch());    break;
            case 4: button.addActionListener(control.new Cancel());      break;
            case 5: button.addActionListener(control.new AddWords());    break;
            case 6: button.addActionListener(control.new UpdateWords()); break;
        }

        parent.add(button);
    }

    /**
    * The makeLabel method creates a label to implement onto the interface
    *
    * @param parent Container that will hold the label
    * @param name String indicating label content
    * @param gridbag GridBagLayout with parent layout details
    * @param c GridBagConstraints indicating the specific constraints and
    * location details
    * @param aligment Integer indicating the text alignment within label
    * @param style Font constant to affect text
    * @param size Font size of label text
    ***/

    public void makeLabel(Container parent, String name, GridBagLayout gridbag,
    GridBagConstraints c, int alignment, int style, int size) {

        JLabel label = new JLabel(name, alignment);
        label.setFont(new Font("Dialog", style, size));
        gridbag.setConstraints(label, c);

        parent.add(label);
    }

    /**
    * The method centerComponent centers a given component with a given offset
    * with respect to the screen dimensions
    *
    * @param c Component to be centered
    * @param offset Integer indicating offset from center
    ***/

    public void centerComponent(Component c, int offset) {

        c.setLocation((d.width - c.getWidth()) / 2 - offset,
        (d.height - c.getHeight()) / 2 - offset);
    }

    /**
    * The method isSearchResultVisible indicates whether the search results
    * frame is currently active or not
    *
    * @return Boolean indicating whether frame is visible or not
    ***/

    public boolean isSearchResultVisible() {

        return searchResult.isVisible();
    }

    /**
    * The method msgBox creates an informative pane to the user with
    * the given message as information
    *
    * @param message String holding information to display
    * @param title String indicating pane title
    * @param messageType Integer indicating the type of message to display
    ***/

    public static void msgBox(String message, String title, int messageType) {

        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    /**
    * The method run initiates the application by calling the view constructor
    ***/

    public static void run() {

        new SearchView();
    }
}