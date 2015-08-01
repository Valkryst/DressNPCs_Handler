package core;

import misc.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * All values which are used program-wide are defined here.
 */
public class Globals {
    /** The maximum length, in characters, of an IP address. */
    public final static byte MAX_TEXTUAL_IP_ADDRESS_LENGTH = 39;
    /** The maximum length, in characters, of a port. */
    public final static byte MAX_TEXTUAL_PORT_LENGTH = 5;
    /** The maximum length, in characters, of a MySQL database name. */
    public final static byte MAX_TEXTUAL_DATABASE_NAME_LENGTH = 64;
    /** The maximum length of a username in MySQL. */
    public final static byte MAX_USERNAME_LENGTH = 16;
    /** The maximum length of a password in MySQL. */
    public final static byte MAX_PASSWORD_LENGTH = 41;

    /** The maximum length of an integer, in characters, when typed out without commas and with a + or - sign. */
    public final static byte MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN = 11;
    /** The maximum length of an integer, in characters, when typed out without commas and without a + or - sign. */
    public final static byte MAX_TEXTUAL_LENGTH_OF_AN_INTEGER = 10;

    /** The resource bundle to load all application text from. */
    public static ResourceBundle localizedText = ResourceBundle.getBundle("Resource Bundles/Localization", Locale.getDefault());

    /**
     * Attempts to retrieve the specified string from the currently loaded
     * ResourceBundle. If no string could be found, then null is returned.
     * @param identifier The identifier of the string to retrieve.
     * @return Either the retrieved string or null if no string was found.
     */
    public static String getLocalizedString(final String identifier) {
        try {
            final String temp = localizedText.getString(identifier);

            return new String(temp.getBytes("ISO-8859-1"), "UTF-8");
        } catch(final UnsupportedEncodingException e) {
            Logger.writeLog(e.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(e), Logger.LOG_TYPE_ERROR);
            System.exit(1);

            return null;
        }
    }
}
