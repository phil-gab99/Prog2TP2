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

public class SearchView extends JFrame {

    private static final int FRAME_WIDTH = 800;  //Default frame width
    private static final int FRAME_HEIGHT = 450; //Default frame height

    private static final Toolkit screen = Toolkit.getDefaultToolkit();
    private static final Dimension d = screen.getScreenSize();

    public static final int ERROR = JOptionPane.ERROR_MESSAGE;

    private SearchModel model;
    private SearchControl control;

    JDialog dialog;              //Dialog used for various user input contexts
    JTextArea indexList;
    JTextArea indexListRev;
    JTextField wordQuery;

    public SearchView() {

        model = new SearchModel(this);
        control = new SearchControl(model);

        //Configuring frame layout, size and location
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        centerComponent(this, 0);

        indexList = new JTextArea();
        JScrollPane scrollL = new JScrollPane(indexList);
        JLabel headL = new JLabel("Indexation List", JLabel.LEFT);
        headL.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollL.setColumnHeaderView(headL);
        indexList.setMargin(new Insets(5, 20, 5, 20));
        indexList.setEditable(false);

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

    public void fileChooserDialog() {

        JFileChooser fileDialog = new JFileChooser("./res");
        fileDialog.setDialogTitle("Select Files");
        fileDialog.setMultiSelectionEnabled(true);
        int option = fileDialog.showDialog(this, "Select");

        if (option == JFileChooser.APPROVE_OPTION) {

            model.updateFiles(fileDialog.getSelectedFiles());
        }
    }

    public void searchWordsDialog() {

        //Instantiating dialog, radio button group and details list
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

            case 1: button.addActionListener(control.new AddFiles()); break;
            case 2: button.addActionListener(control.new Search()); break;
            case 3: button.addActionListener(control.new OkSearch()); break;
            case 4: button.addActionListener(control. new Cancel()); break;
            default: System.out.println("Lolilou");
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
}