package services;

import component.VButton;
import component.VComponentGlobals;
import core.Globals;
import files.FileInput;
import misc.Logger;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public final class SettingsManager {
    /** The filename of the settings file. */
    private final static String settingsFileName = "settings.ini";

    /** The URL or IP address of the server to connect to. */
    private String serverAddress;
    /** The port of the server that MySQL is running on. */
    private String port;
    /** The name of the MySQL database to connect to. */
    private String databaseName;
    /** The username to use when connecting to MySQL. */
    private String username;

    /** The language to use for the programs text. */
    private String language;

    /**
     * If the settings file exists, then the settings are loaded.
     * Else a new settings file is created and the user is prompted to select a language.
     */
    public SettingsManager() {
        if(doesSettingsFileExist()) {
            processSettings();
        } else {
            createSettings();
        }
    }

    /** @return Whether or not the settings file exists. */
    private boolean doesSettingsFileExist() {
        final File file = new File(settingsFileName);

        return file.exists();
    }

    /**
     * Creates a new settings file and prompts the user to select a
     * language to use the program in.
     */
    private void createSettings() {
        // Create Components:
        final JDialog panel = new JDialog(new JFrame());
        final JComboBox<String> comboBox_languages = new JComboBox<>();
        final VButton button_accept = new VButton(Globals.localizedText.getString("SMC_button_accept"));

        // Set Component Colors:
        panel.setBackground(VComponentGlobals.BACKGROUND_COLOR);
        panel.setForeground(VComponentGlobals.FOREGROUND_COLOR);

        comboBox_languages.setBackground(VComponentGlobals.MIDGROUND_COLOR);
        comboBox_languages.setForeground(VComponentGlobals.TEXT_COLOR);

        // Setup Dialogue Frame:
        panel.setTitle("Language Selection");

        // Setup Combo Box:
        comboBox_languages.addItem("Deutsch");
        comboBox_languages.addItem("English");

        comboBox_languages.setSelectedIndex(1); // Select English by default.

        // Dialog - Window Listener:
        final WindowListener windowListener_dialog = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
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
        };

        panel.addWindowListener(windowListener_dialog);

        // Accept Button - Action Listener:
        button_accept.addActionListener(e -> {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(settingsFileName, false)));

                out.println("serverAddress=");
                out.println("port=");
                out.println("databaseName=");
                out.println("username=");

                switch(comboBox_languages.getSelectedIndex()) {
                    case 0: {
                        out.println("language=German");
                        break;
                    }
                    case 1: {
                        out.println("language=English");
                        break;
                    }
                }

                out.close();

                panel.removeWindowListener(windowListener_dialog);
                panel.dispose();

                processSettings();
            } catch(final IOException exception) {
                exception.printStackTrace();
            }
        });

        // Setup Layout:
        panel.setLayout(new MigLayout());
        panel.add(comboBox_languages, "w 225, dock north");
        panel.add(button_accept, "align center center, dock south");

        panel.setModal(true);
        panel.setLocationRelativeTo(null);
        panel.pack();
        panel.setVisible(true);
    }

    /**
     * Saves the specified settings to the settings file.
     * @param serverAddress The URL or IP address of the server to connect to.
     * @param port The port of the server that MySQL is running on.
     * @param databaseName The name of the MySQL database to connect to.
     * @param username The username to use when connecting to MySQL.
     */
    public void saveSettings(final String serverAddress, final String port, final String databaseName, final String username) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(settingsFileName, false)));

            out.println("serverAddress=" + serverAddress);
            out.println("port=" + port);
            out.println("databaseName=" + databaseName);
            out.println("username=" + username);
            out.println("language=" + language);

            out.close();
        } catch(final IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Attempts to load the settings from the settings file.
     */
    private void processSettings() {
        String[] settings = null;

        try {
            final List<String> settingsFile = FileInput.readEntireFile(Paths.get(settingsFileName), true);
            settings = settingsFile.toArray(new String[settingsFile.size()]);
        } catch(final IOException e) {
            Logger.writeLog(e.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(e), Logger.LOG_TYPE_ERROR);
            System.exit(1);
        }

        String[] setting;

        for(final String s : settings) {
            setting = s.split("=");

            switch(setting[0]) {
                case "serverAddress": {
                    if(setting.length == 2) {
                        serverAddress = setting[1];
                    } else {
                        serverAddress = "";
                    }

                    break;
                }
                case "port": {
                    if(setting.length == 2) {
                        port = setting[1];
                    } else {
                        port = "";
                    }

                    break;
                }
                case "databaseName": {
                    if(setting.length == 2) {
                        databaseName = setting[1];
                    } else {
                        databaseName = "";
                    }

                    break;
                }
                case "username": {
                    if(setting.length == 2) {
                        username = setting[1];
                    } else {
                        username = "";
                    }

                    break;
                }
                case "language": {
                    switch(setting[1]) {
                        case "English": {
                            language = "English";
                            Globals.localizedText = ResourceBundle.getBundle("Resource Bundles/Localization", Locale.ENGLISH);
                            break;
                        }
                        case "German": {
                            language = "German";
                            Globals.localizedText = ResourceBundle.getBundle("Resource Bundles/Localization", Locale.GERMAN);
                            break;
                        }
                        default: {
                            language = "English";
                            Globals.localizedText = ResourceBundle.getBundle("Resource Bundles/Localization", Locale.ENGLISH);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    /** @return The URL or IP address of the server to connect to. */
    public String getServerAddress() {
        return serverAddress;
    }

    /** @return The port of the server that MySQL is running on. */
    public String getPort() {
        return port;
    }

    /** @return The name of the MySQL database to connect to. */
    public String getDatabaseName() {
        return databaseName;
    }

    /** @return The username to use when connecting to MySQL. */
    public String getUsername() {
        return username;
    }
}
