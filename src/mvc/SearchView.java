package mvc;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class SearchView extends JFrame {

    private static final int FRAME_WIDTH = 800;  //Default frame width
    private static final int FRAME_HEIGHT = 450; //Default frame height

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
        indexListRev = new JTextArea();

        JScrollPane scrollpane = new JScrollPane(listAccounts);
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