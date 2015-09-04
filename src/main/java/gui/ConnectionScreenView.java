package gui;

import component.VButton;
import component.VPanel;
import component.VPasswordField;
import component.VTextField;
import core.Globals;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class ConnectionScreenView extends VPanel {
    /** The field for the IP or address of the server to connect to. */
    private final VTextField field_serverAddress = new VTextField(Globals.MAX_TEXTUAL_IP_ADDRESS_LENGTH, Globals.getLocalizedString("CSC_field_serverAddress_backgroundText"), Globals.getLocalizedString("CSC_field_serverAddress_tooltip_partA") + " " + Globals.MAX_TEXTUAL_IP_ADDRESS_LENGTH + " " + Globals.getLocalizedString("CSC_field_serverAddress_tooltip_partB"));
    /** The field for the port to connect to MySQL on. */
    private final VTextField field_mysqlPort = new VTextField(Globals.MAX_TEXTUAL_PORT_LENGTH, Globals.getLocalizedString("CSC_field_mysqlPort_backgroundText"), Globals.getLocalizedString("CSC_field_mysqlPort_tooltip"));
    /** The field for the name of the MySQL database to connect to. */
    private final VTextField field_databaseName = new VTextField(Globals.MAX_TEXTUAL_DATABASE_NAME_LENGTH, Globals.getLocalizedString("CSC_field_databaseName_backgroundText"), Globals.getLocalizedString("CSC_field_databaseName_tooltip"));
    /** The field for the username to log into the MySQL database with. */
    private final VTextField field_username = new VTextField(Globals.MAX_USERNAME_LENGTH, Globals.getLocalizedString("CSC_field_username_backgroundText"), Globals.getLocalizedString("CSC_field_username_tooltip"));
    /** The field for the password to log into the MySQL database with. */
    private final VPasswordField field_password = new VPasswordField(Globals.MAX_PASSWORD_LENGTH, Globals.getLocalizedString("CSC_field_password_tooltip"));

    /** The button to submit the ip, port, database name, username, and password with. */
    private final VButton button_submit = new VButton(Globals.getLocalizedString("CSC_button_submit"), Globals.getLocalizedString("CSC_button_submit_tooltip"));
    /** The button to save the ip, port, database name, and username with. */
    private final VButton button_save = new VButton(Globals.getLocalizedString("CSC_button_save"), Globals.getLocalizedString("CSC_button_save_tooltip"));
    /** The button to exit the program. */
    private final VButton button_cancel = new VButton(Globals.getLocalizedString("CSC_button_cancel"), Globals.getLocalizedString("CSC_button_cancel_tooltip"));

    /** The panel on which the ip, port, name, username, and password fields are placed. */
    private final VPanel panel_fields = new VPanel();
    /** The panel on which the submit, save, and canvel buttons are placed. */
    private final VPanel panel_buttons = new VPanel();

    /**
     * Constructs a new connection screen view.
     * @param controller The controller for the new view.
     * @param model The model for the new view.
     */
    public ConnectionScreenView(final ConnectionScreenController controller, final ConnectionScreenModel model) {
        // Add Saved Settings to Fields if Possible:
        field_serverAddress.setText(model.getSettingsManager().getServerAddress());
        field_mysqlPort.setText(model.getSettingsManager().getPort());
        field_databaseName.setText(model.getSettingsManager().getDatabaseName());
        field_username.setText(model.getSettingsManager().getUsername());

        // Add Action Listeners:
        button_submit.addActionListener(controller);
        button_save.addActionListener(controller);
        button_cancel.addActionListener(controller);

        // Add Key Listeners:
        field_serverAddress.addKeyListener(controller);
        field_mysqlPort.addKeyListener(controller);
        field_databaseName.addKeyListener(controller);
        field_username.addKeyListener(controller);
        field_password.addKeyListener(controller);
        button_submit.addKeyListener(controller);
        button_cancel.addKeyListener(controller);
        this.addKeyListener(controller);

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
        this.requestFocus();

        // Set the password field border to error if the username has been loaded from the settings:
        if(field_username.getText().equals(model.getSettingsManager().getUsername())) {
            field_password.getFocusListener().setErrorState(true);
            field_password.appendToTooltip("Your password is not loaded from the settings file. You will always need to enter it.");
        }
    }

    public VTextField getField_serverAddress() {
        return field_serverAddress;
    }

    public VTextField getField_mysqlPort() {
        return field_mysqlPort;
    }

    public VTextField getField_databaseName() {
        return field_databaseName;
    }

    public VTextField getField_username() {
        return field_username;
    }

    public VPasswordField getField_password() {
        return field_password;
    }

    public VButton getButton_submit() {
        return button_submit;
    }

    public VButton getButton_save() {
        return button_save;
    }

    public VButton getButton_cancel() {
        return button_cancel;
    }
}
