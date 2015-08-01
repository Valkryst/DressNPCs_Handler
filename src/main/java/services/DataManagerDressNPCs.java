package services;

import misc.Logger;
import objects.Equipment;
import objects.Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Acts as a middle-man between the rest of the program and
 * the database for any matters related to the importing, exporting,
 * and editing of DressNPCs models.
 *
 * @author Valkryst
 */
public class DataManagerDressNPCs extends DataManager {
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
    public DataManagerDressNPCs(final String ipAddress, final String port, final String databaseName, final String username, final String password) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        super(ipAddress, port, databaseName, username, password);
    }

    /**
     * Attempts to retrieve a listing of all model IDs that
     * contain the specified integer.
     * @param modelId The ID of the model to search for.
     * @return An array of integers containing all IDs similar to the specified ID.
     */
    public int[] searchModelIDs(final int modelId) {
        try {
            // Prepare and execute statement to retrieve the model, if it exists.
            final CallableStatement statement = dbConnection.prepareCall("SELECT `entry` FROM `creature_template_outfits` WHERE `entry` REGEXP CONCAT(\"^[0-9]*\", ?, \"[0-9]*\");");
            statement.setInt(1, modelId);

            final ResultSet results = super.executeQueryWithResults(statement);

            // Skip to the last record so we can get the total number of rows, then skip back to the first record.
            results.last();
            final int resultsLength = results.getRow();
            results.first();

            // If any models were found, then add them to an array of ints return it
            final int[] modelIds = new int[resultsLength];

            for(int i=0;i<resultsLength;i++) {
                modelIds[i] = results.getInt("entry");
                results.next();
            }

            return modelIds;
        } catch(final SQLException e) {
            Logger.writeLog(e.getMessage(), Logger.LOG_TYPE_ERROR);
            System.exit(1); // todo Show the user an error message, don't exit the program.
        }

        return null;
    }

    /**
     * Attempts to retrieve a model from the database with the specified ID.
     * @param modelId The ID of the model to retrieve.
     * @return Either the retrieved model or null if no model was found.
     */
    public Model getModel(final int modelId) {
        try {
            // Prepare and execute statement to retrieve the model, if it exists.
            final CallableStatement statement = dbConnection.prepareCall("SELECT * FROM `creature_template_outfits` WHERE `entry` = ?;");
            statement.setInt(1, modelId);

            final ResultSet results = super.executeQueryWithResults(statement);

            // Skip to the last record so we can get the total number of rows, then skip back to the first record.
            results.last();
            final int resultsLength = results.getRow();
            results.first();

            // If a model was found, then create and return it, else return null.
            if(resultsLength == 1) {
                final Model model = new Model();

                model.setEntry(modelId);

                model.setRace(results.getByte("race"));
                model.setGender(results.getByte("gender"));
                model.setSkin(results.getByte("skin"));
                model.setFace(results.getByte("face"));
                model.setHair(results.getByte("hair"));
                model.setHairColor(results.getByte("haircolor"));
                model.setFacialHair(results.getByte("facialhair"));

                model.setHead(results.getInt("head"));
                model.setShoulders(results.getInt("shoulders"));
                model.setBody(results.getInt("body"));
                model.setChest(results.getInt("chest"));
                model.setWaist(results.getInt("waist"));
                model.setLegs(results.getInt("legs"));
                model.setFeet(results.getInt("feet"));
                model.setWrists(results.getInt("wrists"));
                model.setHands(results.getInt("hands"));
                model.setBack(results.getInt("back"));
                model.setTabard(results.getInt("tabard"));

                return model;
            } else {
                return null;
            }
        } catch(final SQLException e) {
            Logger.writeLog(e.getMessage(), Logger.LOG_TYPE_ERROR);
            System.exit(1); // todo Show the user an error message, don't exit the program.
        }

        return null;
    }

    /**
     * Attempts to retrieve an equipment set from the database with the specified
     * creature ID.
     * @param creatureID The ID of the creature whose equipment is to be retrieved.
     * @return Either the retrieved equipment set or an empty equipment set if none was found.
     */
    public Equipment getEquipment(final int creatureID) {
        try {
            // Prepare and execute statement to retrieve the equipment set, if it exists.
            final CallableStatement statement = dbConnection.prepareCall("SELECT * FROM `creature_equip_template` WHERE `CreatureID` = ?;");
            statement.setInt(1, creatureID);

            final ResultSet results = super.executeQueryWithResults(statement);

            // Skip to the last record so we can get the total number of rows, then skip back to the first record.
            results.last();
            final int resultsLength = results.getRow();
            results.first();

            // If an equipment set was found, then create and return it, else return an empty equipment set.
            if(resultsLength == 1) {
                final Equipment equipment = new Equipment();

                equipment.setSetID(results.getByte("ID"));
                equipment.setMainHand(results.getInt("ItemID1"));
                equipment.setOffHand(results.getInt("ItemID2"));
                equipment.setRanged(results.getInt("ItemID3"));

                return equipment;
            } else {
                return new Equipment();
            }
        } catch(final SQLException e) {
            Logger.writeLog(e.getMessage(), Logger.LOG_TYPE_ERROR);
            System.exit(1); // todo Show the user an error message, don't exit the program.
        }

        return null;
    }

    /**
     * Inserts the specified model into the database. If a model with the same entry
     * ID as the specified model already exists in the database, then it's fields are
     * replaced with the fields of the specified model.
     * @param model The model to insert into the database.
     */
    public void setModel(final Model model) {
        try {
            final CallableStatement statement = dbConnection.prepareCall("INSERT INTO `creature_template_outfits` (`entry`, `race`, `gender`, `skin`, `face`, `hair`, `haircolor`, `facialhair`, `head`, `shoulders`, `body`, `chest`, `waist`, `legs`, `feet`, `wrists`, `hands`, `back`, `tabard`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE `race`=VALUES(`race`), `gender`=VALUES(`gender`), `skin`=VALUES(`skin`), `face`=VALUES(`face`), `hair`=VALUES(`hair`), `haircolor`=VALUES(`haircolor`), `facialhair`=VALUES(`facialhair`), `head`=VALUES(`head`), `shoulders`=VALUES(`shoulders`), `body`=VALUES(`body`), `chest`=VALUES(`chest`), `waist`=VALUES(`waist`), `legs`=VALUES(`legs`), `feet`=VALUES(`feet`), `wrists`=VALUES(`wrists`), `hands`=VALUES(`hands`), `back`=VALUES(`back`), `tabard`=VALUES(`tabard`);");

            statement.setInt(1, model.getEntry());

            statement.setByte(2, model.getRace());
            statement.setByte(3, model.getGender());
            statement.setByte(4, model.getSkin());
            statement.setByte(5, model.getFace());
            statement.setByte(6, model.getHair());
            statement.setByte(7, model.getHaircolor());
            statement.setByte(8, model.getFacialhair());

            statement.setInt(9, model.getHead());
            statement.setInt(10, model.getShoulders());
            statement.setInt(11, model.getBody());
            statement.setInt(12, model.getChest());
            statement.setInt(13, model.getWaist());
            statement.setInt(14, model.getLegs());
            statement.setInt(15, model.getFeet());
            statement.setInt(16, model.getWrists());
            statement.setInt(17, model.getHands());
            statement.setInt(18, model.getBack());
            statement.setInt(19, model.getTabard());

            super.executeUpdateWithoutResults(statement);
        } catch(final SQLException e) {
            Logger.writeLog(e.getMessage(), Logger.LOG_TYPE_ERROR);
            System.exit(1); // todo Show the user an error message, don't exit the program.
        }
    }

    /**
     * Inserts the specified equipment set into the database. If an equipment set with the
     * same ID as they specified model already exists in the database, then it's fields are
     * replaced with the fields of the specified equipment set.
     * @param creatureID The ID of the creature whose equipment set is to be inserted into the database.
     * @param equipment The equipment set to insert into the database.
     */
    public void setEquipment(final int creatureID, final Equipment equipment) {
        try {
            final CallableStatement statement = dbConnection.prepareCall("INSERT INTO `creature_equip_template` (`CreatureID`, `ID`, `ItemID1`, `ItemID2`, `ItemID3`) VALUES (?, ?, ? ,? ,?) ON DUPLICATE KEY UPDATE `CreatureID`=VALUES(`CreatureID`), `ID`=VALUES(`ID`), `ItemID1`=VALUES(`ItemID1`), `ItemID2`=VALUES(`ItemID2`), `ItemID3`=VALUES(`ItemID3`);");

            statement.setInt(1, creatureID);
            statement.setInt(2, equipment.getSetID());
            statement.setInt(3, equipment.getMainHand());
            statement.setInt(4, equipment.getOffHand());
            statement.setInt(5, equipment.getRanged());

            super.executeUpdateWithoutResults(statement);
        } catch(final SQLException e) {
            Logger.writeLog(e.getMessage(), Logger.LOG_TYPE_ERROR);
            System.exit(1); // todo Show the user an error message, don't exit the program.
        }
    }
}
