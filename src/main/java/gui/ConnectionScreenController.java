package gui;

import component.VPasswordField;
import component.VTextField;
import core.Globals;
import misc.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import services.DataManagerDressNPCs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class ConnectionScreenController implements ActionListener, KeyListener {
    /** The frame in which to place the connection screen view. */
    private final JFrame frame;

    /** The connection screen's model. **/
    private final ConnectionScreenModel model;
    /** The connection screen's view. */
    private final ConnectionScreenView view;

    /**
     * Construct a new connection screen controller.
     * @param frame The frame in which to place the connection screen view.
     */
    public ConnectionScreenController(final JFrame frame) {
        this.frame = frame;
        this.model = new ConnectionScreenModel();
        this.view = new ConnectionScreenView(this, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();

        boolean areFieldsCorrect = true;

        final VTextField field_serverAddress = view.getField_serverAddress();
        final VTextField field_mysqlPort = view.getField_mysqlPort();
        final VTextField field_databaseName = view.getField_databaseName();
        final VTextField field_username = view.getField_username();
        final VPasswordField field_password = view.getField_password();

        // Check fields to see if text has been entered:
        if(source.equals(view.getButton_submit()) || source.equals(view.getButton_save())) {
            // Reset error states:
            field_username.setErrorState(false);

            field_password.setErrorState(false);

            // Reset tooltips:
            field_serverAddress.resetTooltip();
            field_mysqlPort.resetTooltip();
            field_databaseName.resetTooltip();
            field_username.resetTooltip();
            field_password.resetTooltip();

            // Check if server address, MySQL port, and database name fields have their background text as
            // the only text, then check to see if the fields are empty.
            if(!field_serverAddress.hasTextBeenEntered()) {
                field_serverAddress.setErrorState(true);
                areFieldsCorrect = false;
            }

            if(!field_mysqlPort.hasTextBeenEntered()) {
                field_mysqlPort.setErrorState(true);
                areFieldsCorrect = false;
            }

            if(!field_databaseName.hasTextBeenEntered()) {
                field_databaseName.setErrorState(true);
                areFieldsCorrect = false;
            }

            // Check if username or password fields have their background text as the only text,
            // then check to see if the fields are empty.
            if(field_username.getText().isEmpty() || !field_username.hasTextBeenEntered()) {
                field_username.setErrorState(true);
                areFieldsCorrect = false;
            }

            if(field_password.getPassword().length == 0 || !field_password.hasTextBeenEntered()) {
                field_password.setErrorState(true);
                areFieldsCorrect = false;
            }
        }

        if(source.equals(view.getButton_submit())) {
            // If all checks have been passed, then attempt to login the user.
            if(areFieldsCorrect) {
                try {
                    final String serverAddress = field_serverAddress.getText();
                    final String mysqlPort = field_mysqlPort.getText();
                    final String databaseName = field_databaseName.getText();
                    final String username = field_username.getText();
                    final char[] password = field_password.getPassword();

                    final DataManagerDressNPCs dataManager = new DataManagerDressNPCs(serverAddress, mysqlPort, databaseName, username, new String(password));

                    frame.remove(view);
                    frame.add(new DressNPCController(frame, dataManager).getView());
                    frame.setMinimumSize(DressNPCView.EXACT_FRAME_DIMENSIONS);
                    frame.setPreferredSize(DressNPCView.EXACT_FRAME_DIMENSIONS);
                    frame.setResizable(false);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                } catch(final ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException exception) {
                    Logger.writeLog(exception.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(exception), Logger.LOG_TYPE_ERROR);

                    field_serverAddress.setErrorState(true);
                    field_mysqlPort.setErrorState(true);
                    field_databaseName.setErrorState(true);
                    field_username.setErrorState(true);
                    field_password.setErrorState(true);

                    final String errorMessage = Globals.getLocalizedString("CSC_error_incorrectConnectionInformation");
                    field_serverAddress.appendToTooltip(errorMessage);
                    field_mysqlPort.appendToTooltip(errorMessage);
                    field_databaseName.appendToTooltip(errorMessage);
                    field_username.appendToTooltip(errorMessage);
                    field_password.appendToTooltip(errorMessage);
                }
            }
        } else if(source.equals(view.getButton_save())) {
            final String serverAddress;
            final String port;
            final String databaseName;
            final String username;

            // Ignore the background text of Server Address if that's all there is in the field:
            if(!field_serverAddress.hasTextBeenEntered()) {
                serverAddress = field_serverAddress.getText();
            } else {
                serverAddress = "";
            }

            // Ignore the background text of Port if that's all there is in the field:
            if(!field_mysqlPort.hasTextBeenEntered()) {
                port = field_mysqlPort.getText();
            } else {
                port = "";
            }

            // Ignore the background text of Database Name if that's all there is in the field:
            if(!field_databaseName.hasTextBeenEntered()) {
                databaseName = field_databaseName.getText();
            } else {
                databaseName = "";
            }

            // Ignore the background text of Username if that's all there is in the field:
            if(!field_username.hasTextBeenEntered()) {
                username = field_username.getText();
            } else {
                username = "";
            }

            model.getSettingsManager().saveSettings(serverAddress, port, databaseName, username);
        } else if(source.equals(view.getButton_cancel())) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(e.getSource().equals(view.getButton_submit())) {
                view.getButton_submit().doClick();
            } else if(e.getSource().equals(view.getButton_save())) {
                view.getButton_save().doClick();
            } else if(e.getSource().equals(view.getButton_cancel())) {
                view.getButton_cancel().doClick();
            } else {
                view.getButton_submit().doClick();
            }
        }
    }

    /** @return The connection screen's view. */
    public ConnectionScreenView getView() {
        return view;
    }
}
