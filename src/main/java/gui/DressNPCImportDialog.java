package gui;

import component.*;
import core.Globals;
import net.miginfocom.swing.MigLayout;
import services.DataManagerDressNPCs;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DressNPCImportDialog extends JDialog {
    /** The minimum size of the JFrame that contains the DressNPCScreen. */
    public final static Dimension EXACT_FRAME_DIMENSIONS = new Dimension(315, 360);

    /** The ID of the currently selected model. This is -1 if no model ID has been selected. */
    final int[] currentlySelectedModelID = {-1}; // Variables used in a lambda expression must be final. To get around this, we're using an array for a single variable.

    /**
     * Constructs a new dialog window used for selecting
     * a DressNPC model to import from the database.
     * @param frame The frame from which the dialog is displayed.
     * @param dataManager The datamanager to use when importing a DressNPC model from the database.
     */
    public DressNPCImportDialog(final JFrame frame, final DataManagerDressNPCs dataManager) {
        super(frame, true);

        // Create Components:
        final JScrollPane scrollpane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        final VTextField field_search = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER, Globals.getLocalizedString("DNI_field_search"), Globals.getLocalizedString("DNI_field_search_tooltip"));
        final VButton button_select = new VButton(Globals.getLocalizedString("DNI_button_select"), Globals.getLocalizedString("DNI_button_select_tooltip"));

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) { // When the exit button in the top-right has been clicked.
                currentlySelectedModelID[0] = -1;
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        // Setup List Selection Listener:
        final ListSelectionListener listSelectionListener = e -> {
            final JList list = (JList)e.getSource();
            final String selectedString = list.getSelectedValue().toString();

            try {
                currentlySelectedModelID[0] = Integer.parseInt(selectedString);
            } catch(final NumberFormatException exception) {
                currentlySelectedModelID[0] = -1;
            }
        };

        // Setup Search Field:
        field_search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    String query = field_search.getText();

                    if(query.isEmpty() || query.equals(field_search.getBackgroundText())) {
                        scrollpane.setViewport(new JViewport());
                    } else {
                        final DefaultListModel<String> new_list_points = new DefaultListModel<String>();
                        int[] results = dataManager.searchModelIDs(field_search.getTextAsInteger());
                        for(int result : results) {
                            new_list_points.addElement(String.valueOf(result));
                        }
                        JViewport viewport = new JViewport();
                        JList<String> list = new JList<String>(new_list_points);
                        viewport.add(list);
                        list.addListSelectionListener(listSelectionListener);
                        scrollpane.setViewport(viewport);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // todo Find a way to set the background and foreground colors of the JScrollPane. Nothing seems to work.
        scrollpane.setBorder(VComponentGlobals.BORDER_NORMAL);

        // Button Select - Action Listener:
        button_select.addActionListener( e -> {
            this.setVisible(false);
            this.dispose();
        });

        // Mouse Listener:
        final VComponentMouseListener scrollpaneMouseListener = new VComponentMouseListener(scrollpane);
        scrollpane.addMouseListener(scrollpaneMouseListener);

        // Focus Listeners:
        final VComponentFocusListener scrollpaneFocusListener = new VComponentFocusListener(scrollpane);
        scrollpane.addFocusListener(scrollpaneFocusListener);

        // Setup the Layout:
        this.setMinimumSize(EXACT_FRAME_DIMENSIONS);
        this.setPreferredSize(EXACT_FRAME_DIMENSIONS);

        this.setLayout(new MigLayout("fill"));
        this.add(field_search, "dock north, growx");
        this.add(scrollpane, "growy, growx");
        this.add(button_select, "dock south, align center center");

        // Set Misc:
        this.setTitle(Globals.getLocalizedString("DNI_frame_title"));
        this.setLocation(frame.getX() + frame.getWidth(), frame.getY());
        this.pack();
        this.setVisible(true);
    }

    /** @return The ID of the DressNPC model that is currently selected in the list. */
    public int getCurrentlySelectedModelID() {
        return currentlySelectedModelID[0];
    }
}
