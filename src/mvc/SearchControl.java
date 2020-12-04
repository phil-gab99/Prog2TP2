package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
* The class SearchControl is responsible for controlling where events triggered
* by the user lead to the various possible actions
*
* @author Philippe Gabriel
* @version 1.7.3 2020-12-07
***/

class SearchControl {

    //Model granting access to the actions in response to user events
    private SearchModel model;

    /**
    * The constructor method SearchControl allows the link between the control
    * and model classes for event handling
    *
    * @param model SearchModel granting access to methods triggered by events
    ***/

    public SearchControl(SearchModel model) {

        this.model = model;
    }

    /**
    * The class KeyMixed handles events triggered upon typing a character in
    * a textfield this class listens to and restricts the allowed characters
    ***/

    public class KeyMixed extends KeyAdapter {

        /**
        * The method keyTyped is triggered upon typing a key from the textfield
        * the current instance is listening to
        *
        * @param e KeyEvent holding information on the key that was typed
        ***/

        public void keyTyped(KeyEvent e) {

            //Method restricting text to numbers and letters
            model.formatMixed(e);
        }
    }

    /**
    * The class AddFiles handles events triggered upon pressing the Add Files
    * button this class listens to
    ***/

    public class AddFiles implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.addFiles(); //Method calling JFileChooser for selecting files
        }
    }

    /**
    * The class Search handles events triggered upon pressing the Search button
    * this class listens to
    ***/

    public class Search implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.search(); //Method calling dialog for searches
        }
    }

    /**
    * The class OkSearch handles events triggered upon pressing the Ok button
    * from within the search dialog
    ***/

    public class OkSearch implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.OkSearch(); //Method calling frame with results
        }
    }

    /**
    * The class Cancel handles the event triggered upon pressing the Cancel
    * button this class listens to
    ***/

    public class Cancel implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.cancel(); //Method closing current dialog instance
        }
    }

    /**
    * The class AddWords handles the event triggered upon pressing the Add
    * Words button from the results frame this class listens to
    ***/

    public class AddWords implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.addWords(); //Method calling dialog for searches
        }
    }

    /**
    * The class UpdateWords handles the event triggered upon pressing the Ok
    * button from within the search dialog originating from the results frame
    ***/

    public class UpdateWords implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.updateWords(); //Method updating results list
        }
    }
}