package gui;

import component.VButton;
import component.VComponentGlobals;
import component.VPasswordField;
import component.VTextField;
import core.Globals;
import misc.Logger;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.exception.ExceptionUtils;
import services.DataManagerDressNPCs;
import services.SettingsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Arrays;


public class ConnectionScreen extends JPanel {
    /**
     * Constructs a new ConnectionScreen.
     * @param frame The frame in which the ConnectionScreen will be placed.
     */
    public ConnectionScreen(final JFrame frame) {
        final SettingsManager settingsManager = new SettingsManager();

        // Create components:
        final VTextField field_serverAddress = new VTextField(Globals.MAX_TEXTUAL_IP_ADDRESS_LENGTH, Globals.getLocalizedString("CSC_field_serverAddress_backgroundText"), Globals.getLocalizedString("CSC_field_serverAddress_tooltip_partA") + " " + Globals.MAX_TEXTUAL_IP_ADDRESS_LENGTH + " " + Globals.getLocalizedString("CSC_field_serverAddress_tooltip_partB"));
        final VTextField field_mysqlPort = new VTextField(Globals.MAX_TEXTUAL_PORT_LENGTH, Globals.getLocalizedString("CSC_field_mysqlPort_backgroundText"), Globals.getLocalizedString("CSC_field_mysqlPort_tooltip"));
        final VTextField field_databaseName = new VTextField(Globals.MAX_TEXTUAL_DATABASE_NAME_LENGTH, Globals.getLocalizedString("CSC_field_databaseName_backgroundText"), Globals.getLocalizedString("CSC_field_databaseName_tooltip"));
        final VTextField field_username = new VTextField(Globals.MAX_USERNAME_LENGTH, Globals.getLocalizedString("CSC_field_username_backgroundText"), Globals.getLocalizedString("CSC_field_username_tooltip"));
        final VPasswordField field_password = new VPasswordField(Globals.MAX_PASSWORD_LENGTH, Globals.getLocalizedString("CSC_field_password_tooltip"));
        final VButton button_submit = new VButton(Globals.getLocalizedString("CSC_button_submit"), Globals.getLocalizedString("CSC_button_submit_tooltip"));
        final VButton button_save = new VButton(Globals.getLocalizedString("CSC_button_save"), Globals.getLocalizedString("CSC_button_save_tooltip"));
        final VButton button_cancel = new VButton(Globals.getLocalizedString("CSC_button_cancel"), Globals.getLocalizedString("CSC_button_cancel_tooltip"));

        final JPanel panel_fields = new JPanel();
        final JPanel panel_buttons = new JPanel();

        // Set component colors:
        this.setBackground(VComponentGlobals.BACKGROUND_COLOR);
        this.setForeground(VComponentGlobals.FOREGROUND_COLOR);

        panel_fields.setBackground(VComponentGlobals.BACKGROUND_COLOR);
        panel_fields.setForeground(VComponentGlobals.FOREGROUND_COLOR);

        panel_buttons.setBackground(VComponentGlobals.BACKGROUND_COLOR);
        panel_buttons.setForeground(VComponentGlobals.FOREGROUND_COLOR);

        // Add Saved Settings to Fields if Possible:
        field_serverAddress.setText(settingsManager.getServerAddress());
        field_mysqlPort.setText(settingsManager.getPort());
        field_databaseName.setText(settingsManager.getDatabaseName());
        field_username.setText(settingsManager.getUsername());

        // Submit Button - Action Listener:
        button_submit.addActionListener(e -> {
            boolean attemptLogin = true;

            // Reset error states:
            field_username.getFocusListener().setErrorState(false);
            field_username.getMouseListener().setErrorState(false);

            field_password.getFocusListener().setErrorState(false);
            field_password.getMouseListener().setErrorState(false);

            // Reset tooltips:
            field_serverAddress.resetTooltip();
            field_mysqlPort.resetTooltip();
            field_databaseName.resetTooltip();
            field_username.resetTooltip();
            field_password.resetTooltip();

            // Check if server address, MySQL port, and database name fields have their background text as
            // the only text, then check to see if the fields are empty.
            if(field_serverAddress.getText().equals(field_serverAddress.getBackgroundText())) {
                field_serverAddress.getFocusListener().setErrorState(true);
                field_serverAddress.getMouseListener().setErrorState(true);
                attemptLogin = false;
            }

            if(field_mysqlPort.getText().equals(field_mysqlPort.getBackgroundText())) {
                field_mysqlPort.getFocusListener().setErrorState(true);
                field_mysqlPort.getMouseListener().setErrorState(true);
                attemptLogin = false;
            }

            if(field_databaseName.getText().equals(field_databaseName.getBackgroundText())) {
                field_databaseName.getFocusListener().setErrorState(true);
                field_databaseName.getMouseListener().setErrorState(true);
                attemptLogin = false;
            }

            // Check if username or password fields have their background text as the only text,
            // then check to see if the fields are empty.
            if(field_username.getText().isEmpty() || field_username.getText().equals(field_username.getBackgroundText())) {
                field_username.getFocusListener().setErrorState(true);
                field_username.getMouseListener().setErrorState(true);
                attemptLogin = false;
            }

            if(field_password.getPassword().length == 0 || Arrays.equals(field_password.getPassword(), field_password.getBackgroundText().toCharArray())) {
                field_password.getFocusListener().setErrorState(true);
                field_password.getMouseListener().setErrorState(true);
                attemptLogin = false;
            }

            // If all checks have been passed, then attempt to login the user.
            if(attemptLogin) {
                final String serverAddress = field_serverAddress.getText();
                final String mysqlPort = field_mysqlPort.getText();
                final String databaseName = field_databaseName.getText();
                final String username = field_username.getText();
                final char[] password = field_password.getPassword();

                try {
                    final DataManagerDressNPCs dataManager = new DataManagerDressNPCs(serverAddress, mysqlPort, databaseName, username, new String(password));

                    frame.remove(this);
                    frame.add(new DressNPCScreen(frame, dataManager));
                    frame.setMinimumSize(DressNPCScreen.EXACT_FRAME_DIMENSIONS);
                    frame.setPreferredSize(DressNPCScreen.EXACT_FRAME_DIMENSIONS);
                    frame.setResizable(false);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                } catch(final ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException exception) {
                    Logger.writeLog(exception.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(exception), Logger.LOG_TYPE_ERROR);

                    field_serverAddress.getFocusListener().setErrorState(true);
                    field_serverAddress.getMouseListener().setErrorState(true);
                    field_mysqlPort.getFocusListener().setErrorState(true);
                    field_mysqlPort.getMouseListener().setErrorState(true);
                    field_databaseName.getFocusListener().setErrorState(true);
                    field_databaseName.getMouseListener().setErrorState(true);
                    field_username.getFocusListener().setErrorState(true);
                    field_username.getMouseListener().setErrorState(true);
                    field_password.getFocusListener().setErrorState(true);
                    field_password.getMouseListener().setErrorState(true);

                    final String errorMessage = Globals.getLocalizedString("CSC_error_incorrectConnectionInformation");
                    field_serverAddress.appendToTooltip(errorMessage);
                    field_mysqlPort.appendToTooltip(errorMessage);
                    field_databaseName.appendToTooltip(errorMessage);
                    field_username.appendToTooltip(errorMessage);
                    field_password.appendToTooltip(errorMessage);
                }
            }
        });

        // Save button - Action Listener:
        button_save.addActionListener(e -> {
            final String serverAddress;
            final String port;
            final String databaseName;
            final String username;

            // Ignore the background text of Server Address if that's all there is in the field:
            if(!field_serverAddress.getText().equals(field_serverAddress.getBackgroundText())) {
                serverAddress = field_serverAddress.getText();
            } else {
                serverAddress = "";
            }

            // Ignore the background text of Port if that's all there is in the field:
            if(!field_mysqlPort.getText().equals(field_mysqlPort.getBackgroundText())) {
                port = field_mysqlPort.getText();
            } else {
                port = "";
            }

            // Ignore the background text of Database Name if that's all there is in the field:
            if(!field_databaseName.getText().equals(field_databaseName.getBackgroundText())) {
                databaseName = field_databaseName.getText();
            } else {
                databaseName = "";
            }

            // Ignore the background text of Username if that's all there is in the field:
            if(!field_username.getText().equals(field_username.getBackgroundText())) {
                username = field_username.getText();
            } else {
                username = "";
            }
            settingsManager.saveSettings(serverAddress, port, databaseName, username);
        });

        // Cancel Button - Action Listener:
        button_cancel.addActionListener(e -> {
            System.exit(0);
        });

        // Create a key listener to press the submit button from any component
        // when the user presses the ENTER key.
        KeyListener keyListener_submit = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(e.getSource().equals(button_cancel)) {
                        button_cancel.doClick();
                    } else {
                        button_submit.doClick();
                    }
                }
            }
        };

        field_username.addKeyListener(keyListener_submit);
        field_password.addKeyListener(keyListener_submit);
        button_submit.addKeyListener(keyListener_submit);
        button_cancel.addKeyListener(keyListener_submit);
        this.addKeyListener(keyListener_submit);

        // Setup the Layout:
        panel_fields.setLayout(new MigLayout("align center center, wrap, gapy 5"));
        panel_fields.add(field_serverAddress, "growx");
        panel_fields.add(field_mysqlPort, "growx");
        panel_fields.add(field_databaseName, "growx");
        panel_fields.add(field_username, "growx");
        panel_fields.add(field_password, "growx");

        panel_buttons.setLayout(new MigLayout("align center center, gapx 4"));
        panel_buttons.add(button_submit);
        panel_buttons.add(button_save);
        panel_buttons.add(button_cancel);

        this.setLayout(new MigLayout("align center center, wrap"));
        this.add(panel_fields, BorderLayout.NORTH);
        this.add(panel_buttons, BorderLayout.SOUTH);
        frame.requestFocus();

        // Set the password field border to error if the username has been loaded from the settings:
        if(field_username.getText().equals(settingsManager.getUsername())) {
            field_password.getFocusListener().setErrorState(true);
            field_password.appendToTooltip("Your password is not loaded from the settings file. You will always need to enter it.");
        }
    }
}
