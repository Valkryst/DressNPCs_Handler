package services;

import core.Globals;
import misc.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;

/**
 * Controls access to the database for all classes
 * which extend this class. Allows for two types of
 * queries, one with and one without results.
 *
 * @author Valkryst
 */
public class DataManager {
    /** Whether or not to log the execution time of every query that is run. */
    public static boolean logQueryExecutionTime = false;

    /** The connection to use when connecting the the database. */
    protected Connection dbConnection;

    /**
     * Construct a new DataManager object with it's own
     * connection to the database.
     *
     * @param ipAddress The IP address to the server on which MySQL is running.
     * @param port The port to use when connecting to MySQL.
     * @param databaseName The database name of the database to connect to.
     * @param username The username for a MySQL account with access to the specified database.
     * @param password The password for the MySQL account specified by the specified username.
     */
    public DataManager(final String ipAddress, final String port, final String databaseName, final String username, final String password) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + ipAddress + ":" + port + "/" + databaseName + "?noAccessToProcedureBodies=true"; //?noAccessToProcedureBodies=true is required for using the stored procedures.
        dbConnection = DriverManager.getConnection(url, username, password);

        // Attempt to overwrite the password using reflection:
        try {
            final Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            final char[] chars = (char[]) value.get(password);
            Arrays.fill(chars, '*');
        } catch (final NoSuchFieldException e) {
            Logger.writeLog(e.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(e), Logger.LOG_TYPE_ERROR);
            System.exit(1);
        }
    }

    /**
     * Execute the specified query and return no result.
     * This is best used when the query doesn't result in results.
     * This should be used whenever the query will update anything in
     * the database.
     * @param preparedStatement The query to execute.
     */
    protected void executeUpdateWithoutResults(final PreparedStatement preparedStatement) {
        try {
            if(logQueryExecutionTime) {
                long before = System.currentTimeMillis();
                preparedStatement.execute();
                Logger.writeLog("The following query took " + (System.currentTimeMillis() - before) + "milliseconds to run.\\n" + preparedStatement.toString(), Logger.LOG_TYPE_VERBOSE);
            } else {
                preparedStatement.executeUpdate();
            }
        } catch(SQLException e) {
            Logger.writeLog(Globals.getLocalizedString("DMC_error_withoutResults_sqlException") + e.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(e), Logger.LOG_TYPE_ERROR);
            System.exit(1);
        }
    }

    /**
     * Execute the specified query and return no result.
     * This is best used when the query doesn't result in results.
     * This should not be used whenever the query will update anything in
     * the database.
     * @param preparedStatement The query to execute.
     */
    protected void executeQueryWithoutResults(final PreparedStatement preparedStatement) {
        try {
            if(logQueryExecutionTime) {
                long before = System.currentTimeMillis();
                preparedStatement.execute();
                Logger.writeLog("The following query took " + (System.currentTimeMillis() - before) + "milliseconds to run.\\n" + preparedStatement.toString(), Logger.LOG_TYPE_VERBOSE);
            } else {
                preparedStatement.executeQuery();
            }
        } catch(SQLException e) {
            Logger.writeLog(Globals.getLocalizedString("DMC_error_withoutResults_sqlException") + e.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(e), Logger.LOG_TYPE_ERROR);
            System.exit(1);
        }
    }

    /**
     * Execute the specified query and return all results.
     * This is best used when the query results in results.
     * @param preparedStatement The query to execute.
     * @return The result set of the executed query.
     */
    protected ResultSet executeQueryWithResults(final PreparedStatement preparedStatement) {
        try {
            if(logQueryExecutionTime) {
                long before = System.currentTimeMillis();
                ResultSet result = preparedStatement.executeQuery();
                Logger.writeLog("The following query took " + (System.currentTimeMillis() - before) + "milliseconds to run.\\n" + preparedStatement.toString(), Logger.LOG_TYPE_VERBOSE);

                return result;
            } else {
                return preparedStatement.executeQuery();
            }
        } catch(SQLException e) {
            Logger.writeLog(Globals.getLocalizedString("DMC_error_withResults_sqlException") + e.getMessage() + "\n\n" + ExceptionUtils.getStackTrace(e), Logger.LOG_TYPE_ERROR);
            System.exit(1);
        }
        return null;
    }
}
