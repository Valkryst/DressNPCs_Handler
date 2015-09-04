package gui;

import component.VTextField;
import core.Globals;
import objects.Model;
import services.DataManagerDressNPCs;

import java.nio.file.Path;

public class DressNPCModel {
    // todo JavaDoc
    private final DataManagerDressNPCs dataManager;

    /** The path to the file in which the DressNPC form should be saved to whenever the user saves. */
    private Path path_saveLocation;

    /** The Model being manipulated. */
    private Model npcModel = new Model();

    // todo JavaDoc
    public DressNPCModel(final DataManagerDressNPCs dataManager) {
        this.dataManager = dataManager;
    }

    /** @return A String array containing all genders that a model can be assigned to. */
    public String[] getGender() {
        final String[] gender = new String[2];

        gender[0] = Globals.getLocalizedString("male");
        gender[1] = Globals.getLocalizedString("female");

        return gender;
    }

    /** @return A String array containing all races that a model can be assigned to. */
    public String[] getRaces() {
        final String[] races = new String[21];

        races[0] = Globals.getLocalizedString("human");
        races[1] = Globals.getLocalizedString("orc");
        races[2] = Globals.getLocalizedString("dwarf");
        races[3] = Globals.getLocalizedString("nightelf");
        races[4] = Globals.getLocalizedString("forsaken");
        races[5] = Globals.getLocalizedString("tauren");
        races[6] = Globals.getLocalizedString("gnome");
        races[7] = Globals.getLocalizedString("troll");
        races[8] = Globals.getLocalizedString("goblin");
        races[9] = Globals.getLocalizedString("bloodelf");
        races[10] = Globals.getLocalizedString("draenei");
        races[11] = Globals.getLocalizedString("felorc");
        races[12] = Globals.getLocalizedString("naga");
        races[13] = Globals.getLocalizedString("broken");
        races[14] = Globals.getLocalizedString("skeleton");
        races[15] = Globals.getLocalizedString("vrykul");
        races[16] = Globals.getLocalizedString("tuskarr");
        races[17] = Globals.getLocalizedString("foresttroll");
        races[18] = Globals.getLocalizedString("taunka");
        races[19] = Globals.getLocalizedString("northrendskeleton");
        races[20] = Globals.getLocalizedString("icetroll");

        return races;
    }

    /**
     * Attempts to create a Model object from the data entered into
     * the fields and comboboxes. If no data has been entered in
     * certain fields, then zero is automatically entered. If there
     * are any other problems with any of the fields, then the fields
     * error state is set to true and its tooltip is altered to describe
     * the error as well as possible.
     * If the model was successfully created, then it is submitted to the
     * database.
     * @param view todo JavaDoc
     * @return Whether or not the model was created sucessfully.
     */
    public boolean submitNPCModel(final DressNPCView view) {
        view.resetErrorStates();

        // Set all empty fields to zero as any unused fields must be zero.
        if(!view.getField_skin().hasTextBeenEntered()) {
            view.getField_skin().setText("0");
        }

        if(!view.getField_hair().hasTextBeenEntered()) {
            view.getField_hair().setText("0");
        }

        if(!view.getField_hairColor().hasTextBeenEntered()) {
            view.getField_hairColor().setText("0");
        }

        if(!view.getField_facialHair().hasTextBeenEntered()) {
            view.getField_facialHair().setText("0");
        }

        if(!view.getField_head().hasTextBeenEntered()) {
            view.getField_head().setText("0");
        }

        if(!view.getField_shoulders().hasTextBeenEntered()) {
            view.getField_shoulders().setText("0");
        }

        if(!view.getField_body().hasTextBeenEntered()) {
            view.getField_body().setText("0");
        }

        if(!view.getField_chest().hasTextBeenEntered()) {
            view.getField_chest().setText("0");
        }

        if(!view.getField_waist().hasTextBeenEntered()) {
            view.getField_waist().setText("0");
        }

        if(!view.getField_legs().hasTextBeenEntered()) {
            view.getField_legs().setText("0");
        }

        if(!view.getField_feet().hasTextBeenEntered()) {
            view.getField_feet().setText("0");
        }

        if(!view.getField_wrists().hasTextBeenEntered()) {
            view.getField_wrists().setText("0");
        }

        if(!view.getField_hands().hasTextBeenEntered()) {
            view.getField_hands().setText("0");
        }

        if(!view.getField_back().hasTextBeenEntered()) {
            view.getField_back().setText("0");
        }

        if(!view.getField_tabard().hasTextBeenEntered()) {
            view.getField_tabard().setText("0");
        }

        // Get all of the values from every field and determine whether or not
        // the model should be submitted to the database.
        boolean submitModel = true;

        int modelEntryID = 0;

        byte race = (byte)(view.getComboBox_race().getSelectedIndex() + 1); // Add one because the Race IDs begin at 1 and not 0.
        byte gender = (byte)view.getComboBox_gender().getSelectedIndex();
        byte skin = 0;
        byte face = 0;
        byte hair = 0;
        byte hairColor = 0;
        byte facialHair = 0;

        int head = 0;
        int shoulders = 0;
        int body = 0;
        int chest = 0;
        int waist = 0;
        int legs = 0;
        int feet = 0;
        int wrists = 0;
        int hands = 0;
        int back = 0;
        int tabard = 0;

        final VTextField field_modelEntryID = view.getField_modelEntryID();
        final VTextField field_skin = view.getField_skin();
        final VTextField field_face = view.getField_face();
        final VTextField field_hair = view.getField_hair();
        final VTextField field_hairColor = view.getField_hairColor();
        final VTextField field_facialHair = view.getField_facialHair();
        final VTextField field_head = view.getField_head();
        final VTextField field_shoulders = view.getField_shoulders();
        final VTextField field_body = view.getField_body();
        final VTextField field_chest = view.getField_chest();
        final VTextField field_waist = view.getField_waist();
        final VTextField field_legs = view.getField_legs();
        final VTextField field_feet = view.getField_feet();
        final VTextField field_wrists = view.getField_wrists();
        final VTextField field_hands = view.getField_hands();
        final VTextField field_back = view.getField_back();
        final VTextField field_tabard = view.getField_tabard();

        try {
            modelEntryID = field_modelEntryID.getTextAsInteger();

            if(!field_modelEntryID.hasTextBeenEntered()) {
                field_modelEntryID.setErrorState(true);
                field_modelEntryID.setToolTipText(Globals.getLocalizedString("DNS_error_modelEntryID_A"));
                submitModel = false;
            }

            if(modelEntryID < 0) {
                field_modelEntryID.setErrorState(true);
                field_modelEntryID.setToolTipText(Globals.getLocalizedString("DNS_error_modelEntryID_B"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_modelEntryID.setErrorState(true);
            field_modelEntryID.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            skin = field_skin.getTextAsByte();

            if(skin < 0) {
                field_skin.setErrorState(true);
                field_skin.setToolTipText(Globals.getLocalizedString("DNS_error_skin_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_skin.setErrorState(true);
            field_skin.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            face = field_face.getTextAsByte();

            if(face < 0) {
                field_face.setErrorState(true);
                field_face.setToolTipText(Globals.getLocalizedString("DNS_error_face_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_face.setErrorState(true);
            field_face.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            hair = field_hair.getTextAsByte();

            if(hair < 0) {
                field_hair.setErrorState(true);
                field_hair.setToolTipText(Globals.getLocalizedString("DNS_error_hair_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_hair.setErrorState(true);
            field_hair.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            hairColor = field_hairColor.getTextAsByte();

            if(hairColor < 0) {
                field_hairColor.setErrorState(true);
                field_hairColor.setToolTipText(Globals.getLocalizedString("DNS_error_hairColor_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_hairColor.setErrorState(true);
            field_hairColor.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            facialHair = field_facialHair.getTextAsByte();

            if(facialHair < 0) {
                field_facialHair.setErrorState(true);
                field_facialHair.setToolTipText(Globals.getLocalizedString("DNS_error_facialHair_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_facialHair.setErrorState(true);
            field_facialHair.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            head = field_head.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_head.setErrorState(true);
            field_head.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            shoulders = field_shoulders.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_shoulders.setErrorState(true);
            field_shoulders.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            body = field_body.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_body.setErrorState(true);
            field_body.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            chest = field_chest.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_chest.setErrorState(true);
            field_chest.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            waist = field_waist.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_waist.setErrorState(true);
            field_waist.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            legs = field_legs.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_legs.setErrorState(true);
            field_legs.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            feet = field_feet.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_feet.setErrorState(true);
            field_feet.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            wrists = field_wrists.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_wrists.setErrorState(true);
            field_wrists.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            hands = field_hands.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_hands.setErrorState(true);
            field_hands.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            back = field_back.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_back.setErrorState(true);
            field_back.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            tabard = field_tabard.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            exception.printStackTrace();
            field_tabard.setErrorState(true);
            field_tabard.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }


        // Setup Model:
        npcModel.setEntry(modelEntryID);

        npcModel.setSkin(skin);
        npcModel.setFace(face);
        npcModel.setHair(hair);
        npcModel.setHairColor(hairColor);
        npcModel.setFacialHair(facialHair);
        npcModel.setRace(race);
        npcModel.setGender(gender);

        npcModel.setHead(head);
        npcModel.setShoulders(shoulders);
        npcModel.setBody(body);
        npcModel.setChest(chest);
        npcModel.setWaist(waist);
        npcModel.setLegs(legs);
        npcModel.setFeet(feet);
        npcModel.setWrists(wrists);
        npcModel.setHands(hands);
        npcModel.setBack(back);
        npcModel.setTabard(tabard);

        if(submitModel) {
            dataManager.setModel(npcModel);
            return true;
        } else {
            return false;
        }
    }

    public DataManagerDressNPCs getDataManager() {
        return dataManager;
    }

    public Path getPath_saveLocation() {
        return path_saveLocation;
    }

    public Model getNpcModel() {
        return npcModel;
    }

    public void setPath_saveLocation(final Path path_saveLocation) {
        this.path_saveLocation = path_saveLocation;
    }

    public void setNpcModel(final Model npcModel) {
        this.npcModel = npcModel;
    }
}
