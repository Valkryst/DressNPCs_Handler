package gui;

import services.DataManagerDressNPCs;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class DressNPCImportDialogController implements  ListSelectionListener, WindowListener {
    private final JFrame frame;

    private final DressNPCImportDialogView view;
    private final DressNPCImportDialogModel model;

    public DressNPCImportDialogController(final JFrame frame, final DataManagerDressNPCs dataManager) {
        this.frame = frame;
        this.model = new DressNPCImportDialogModel(dataManager);
        this.view = new DressNPCImportDialogView(this, model);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        final Object source = e.getSource();

        if(source instanceof JList) {
            final JList list = (JList)e.getSource();
            final String selectedString = list.getSelectedValue().toString();

            try {
                model.setCurrentlySelectedModelID(Integer.parseInt(selectedString));
            } catch(final NumberFormatException exception) {
                model.setCurrentlySelectedModelID(-1);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        model.setCurrentlySelectedModelID(-1);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    public JFrame getFrame() {
        return frame;
    }

    public DressNPCImportDialogModel getModel() {
        return model;
    }
}
