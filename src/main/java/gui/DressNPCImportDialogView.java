package gui;

import component.*;
import core.Globals;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DressNPCImportDialogView extends JDialog {
    /** The minimum size of the JFrame that contains the DressNPCScreen. */
    public final static Dimension EXACT_FRAME_DIMENSIONS = new Dimension(315, 360);

    /** todo JavaDoc */
    private final JScrollPane scrollpane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    /** todo JavaDoc */
    private final VTextField field_search = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER, Globals.getLocalizedString("DNI_field_search"), Globals.getLocalizedString("DNI_field_search_tooltip"));
    /** todo JavaDoc */
    private final VButton button_select = new VButton(Globals.getLocalizedString("DNI_button_select"), Globals.getLocalizedString("DNI_button_select_tooltip"));

    /**
     * todo JavaDoc
     * @param controller todo JavaDoc
     */
    public DressNPCImportDialogView(final DressNPCImportDialogController controller, final DressNPCImportDialogModel model) {
        super(controller.getFrame(), true);

        this.addWindowListener(controller);

        // Setup Search Field:
        field_search.addKeyListener(new KeyListener() { // Unable to move this to the Controller class as there is some weird issue where everything seems to be null after doing this.setVisible(true)
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if(field_search.getText().isEmpty() || !field_search.hasTextBeenEntered()) {
                        scrollpane.setViewport(new JViewport());
                    } else {
                        final DefaultListModel<String> new_list_points = new DefaultListModel<>();
                        int[] results = model.getDataManager().searchModelIDs(field_search.getTextAsInteger());
                        for(int result : results) {
                            new_list_points.addElement(String.valueOf(result));
                        }
                        JViewport viewport = new JViewport();
                        JList<String> list = new JList<>(new_list_points);
                        viewport.add(list);
                        list.addListSelectionListener(controller);
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
        button_select.addActionListener(e -> {
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
        this.setLocation(controller.getFrame().getX() + controller.getFrame().getWidth(), controller.getFrame().getY());
        this.pack();
        this.setVisible(true);
    }

    public JScrollPane getScrollpane() {
        return scrollpane;
    }

    public VTextField getField_search() {
        return field_search;
    }

    public VButton getButton_select() {
        return button_select;
    }
}
