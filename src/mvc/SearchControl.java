package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class SearchControl {

    private SearchModel model;

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
    * button this class listens to present in many dialogs
    ***/

    public class Cancel implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.cancel(); //Method closing current dialog
        }
    }

    /**
    * The class AddWords handles the event triggered upon pressing the Add
    * Words button this class listens to
    ***/

    public class AddWords implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.addWords(); //Method closing current dialog
        }
    }

    public class Update implements ActionListener {

        /**
        * The method actionPerformed is triggered upon pressing the button the
        * current instance is listening to
        *
        * @param e ActionEvent holding information on the press of the button
        ***/

        public void actionPerformed(ActionEvent e) {

            model.update(); //Method closing current dialog
        }
    }
}