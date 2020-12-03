package mvc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class SearchView extends JFrame {

    private static final int FRAME_WIDTH = 800;  //Default frame width
    private static final int FRAME_HEIGHT = 450; //Default frame height

    private static final Toolkit screen = Toolkit.getDefaultToolkit();
    private static final Dimension d = screen.getScreenSize();

    private SearchModel model;
    private SearchControl control;

    JTextArea indexList;
    JTextArea indexListRev;

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

        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void fileChooserDialog() {

        JFileChooser dialog = new JFileChooser("./res");
        dialog.setDialogTitle("Select Files");
        dialog.setMultiSelectionEnabled(true);
        int option = dialog.showDialog(this, "Select");

        if (option == JFileChooser.APPROVE_OPTION) {

            // System.out.println("Here");
            model.updateFiles(dialog.getSelectedFiles());
        }
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
            default: System.out.println("Lolilou");
        }

        parent.add(button);
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
}