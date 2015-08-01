package gui;

import component.*;
import core.Globals;
import net.miginfocom.swing.MigLayout;
import objects.Model;
import org.apache.commons.io.FilenameUtils;
import services.DataManagerDressNPCs;
import services.LoadManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DressNPCScreen extends JPanel {
    /** The minimum size of the JFrame that contains the DressNPCScreen. */
    public final static Dimension EXACT_FRAME_DIMENSIONS = new Dimension(315, 360);

    /** The path to the file in which the DressNPC form should be saved to whenever the user saves. */
    private Path path_saveLocation;


    /** A field to enter the model entry ID. */
    private final VTextField field_modelEntryID = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, "Model Entry ID", "Tooltip");

    /** A combo box to select the race of the model. */
    private final JComboBox<String> comboBox_race = new JComboBox<>(getRaces());
    /** A combo box to select the gender of the model. */
    private final JComboBox<String> comboBox_gender = new JComboBox<>(getGender());

    /** A field to enter the skin ID of the model. */
    private final VTextField field_skin = new VTextField((byte)2, Globals.getLocalizedString("DNS_field_skin"), Globals.getLocalizedString("DNS_field_skin_tooltip"));
    /** A field to enter the face ID of the model. */
    private final VTextField field_face = new VTextField((byte)2, Globals.getLocalizedString("DNS_field_face"), Globals.getLocalizedString("DNS_field_face_tooltip"));
    /** A field to enter the hair ID of the model. */
    private final VTextField field_hair = new VTextField((byte)2,Globals.getLocalizedString("DNS_field_hair"), Globals.getLocalizedString("DNS_field_hair_tooltip"));
    /** A field to enter the hair color ID of the model. */
    private final VTextField field_hairColor = new VTextField((byte)2, Globals.getLocalizedString("DNS_field_hairColor"), Globals.getLocalizedString("DNS_field_hairColor_tooltip"));
    /** A field to enter the facial hair ID of the model. */
    private final VTextField field_facialHair = new VTextField((byte)2, Globals.getLocalizedString("DNS_field_facialHair"), Globals.getLocalizedString("DNS_field_facialHair_tooltip"));

    /** A field to enter the helm item ID of the model. */
    private final VTextField field_head = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_head"), Globals.getLocalizedString("DNS_field_head_tooltip"));
    /** A field to enter the shoulder item ID of the model. */
    private final VTextField field_shoulders = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_shoulders"), Globals.getLocalizedString("DNS_field_shoulders_tooltip"));
    /** A field to enter the shirt item ID of the model. */
    private final VTextField field_body = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_shirt"), Globals.getLocalizedString("DNS_field_shirt_tooltip"));
    /** A field to enter the chest item ID of the model. */
    private final VTextField field_chest = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_chest"), Globals.getLocalizedString("DNS_field_chest_tooltip"));
    /** A field to enter the waist item ID of the model. */
    private final VTextField field_waist = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_waist"), Globals.getLocalizedString("DNS_field_waist_tooltip"));
    /** A field to enter the leg item ID of the model. */
    private final VTextField field_legs = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_legs"), Globals.getLocalizedString("DNS_field_legs_tooltip"));
    /** A field to enter the feet item ID of the model. */
    private final VTextField field_feet = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_feet"), Globals.getLocalizedString("DNS_field_feet_tooltip"));
    /** A field to enter the wrist item ID of the model. */
    private final VTextField field_wrists = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_wrists"), Globals.getLocalizedString("DNS_field_wrists_tooltip"));
    /** A field to enter the hand item ID of the model. */
    private final VTextField field_hands = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_hands"), Globals.getLocalizedString("DNS_field_hands_tooltip"));
    /** A field to enter the back item ID of the model. */
    private final VTextField field_back = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_back"), Globals.getLocalizedString("DNS_field_back_tooltip"));
    /** A field to enter the tabard item ID of the model. */
    private final VTextField field_tabard = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, Globals.getLocalizedString("DNS_field_tabard"), Globals.getLocalizedString("DNS_field_tabard_tooltip"));


    /** The Model being manipulated. */
    private Model model = new Model();

    public DressNPCScreen(final JFrame frame, final DataManagerDressNPCs dataManager) {
        final VSection section_character = new VSection(new MigLayout("al center center, wrap, gapy 4"));
        final VSection section_armor = new VSection(new MigLayout("al center center, wrap, gapy 4"));

        final VButton button_submit = new VButton(Globals.getLocalizedString("DNS_button_submit"));
        final VButton button_reset = new VButton(Globals.getLocalizedString("DNS_button_reset"));

        // Set Component Colors:
        this.setBackground(VComponentGlobals.BACKGROUND_COLOR);
        this.setForeground(VComponentGlobals.FOREGROUND_COLOR);

        comboBox_race.setBackground(VComponentGlobals.MIDGROUND_COLOR);
        comboBox_race.setForeground(VComponentGlobals.TEXT_COLOR);

        comboBox_gender.setBackground(VComponentGlobals.MIDGROUND_COLOR);
        comboBox_gender.setForeground(VComponentGlobals.TEXT_COLOR);

        // Set Section Titles:
        section_character.setSectionTitle(Globals.getLocalizedString("DNS_section_character"));
        section_armor.setSectionTitle(Globals.getLocalizedString("DNS_section_armor"));

        // Button Submit - Action Listener:
        button_submit.addActionListener(e -> {
                // Either submit the model or do nothing as the error messages have already been set.
                if(createModel()) {
                    dataManager.setModel(model);
                }
            }
        );

        // Button Reset - Action Listener:
        button_reset.addActionListener(e -> {
                resetErrorStates();

                // Reset text and combobox selections:
                field_modelEntryID.setText(null);

                field_skin.setText(null);
                field_face.setText(null);
                field_hair.setText(null);
                field_hairColor.setText(null);
                field_facialHair.setText(null);
                comboBox_race.setSelectedIndex(0);
                comboBox_gender.setSelectedIndex(0);

                field_head.setText(null);
                field_shoulders.setText(null);
                field_body.setText(null);
                field_chest.setText(null);
                field_waist.setText(null);
                field_legs.setText(null);
                field_feet.setText(null);
                field_wrists.setText(null);
                field_hands.setText(null);
                field_back.setText(null);
                field_tabard.setText(null);
            }
        );

        // Setup Layout:
        section_character.add(field_modelEntryID, "growx");
        section_character.add(comboBox_race, "growx");
        section_character.add(comboBox_gender, "growx");
        section_character.add(field_skin, "growx");
        section_character.add(field_face, "growx");
        section_character.add(field_hair, "growx");
        section_character.add(field_hairColor, "growx");
        section_character.add(field_facialHair, "growx");

        section_armor.add(field_head, "growx");
        section_armor.add(field_shoulders, "growx");
        section_armor.add(field_body, "growx");
        section_armor.add(field_chest, "growx");
        section_armor.add(field_waist, "growx");
        section_armor.add(field_legs, "growx");
        section_armor.add(field_feet, "growx");
        section_armor.add(field_wrists, "growx");
        section_armor.add(field_hands, "growx");
        section_armor.add(field_back, "growx");
        section_armor.add(field_tabard, "growx");

        final JPanel panel_left = new JPanel();
        panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));
        panel_left.add(section_character);

        final JPanel panel_top = new JPanel(new MigLayout("fill"));
        panel_top.add(panel_left, "dock west");
        panel_top.add(section_armor, "dock east");

        final JPanel panel_bottom = new JPanel(new MigLayout("align center center, gapx 4"));
        panel_bottom.setBackground(VComponentGlobals.BACKGROUND_COLOR);
        panel_bottom.add(button_submit, "w 44");
        panel_bottom.add(button_reset, "w 40");

        this.setLayout(new MigLayout());
        this.add(panel_top, "dock north");
        this.add(panel_bottom, "dock south");

        frame.setJMenuBar(createMenuBar(frame, dataManager));
    }

    /** @return A String array containing all races that a model can be assigned to. */
    private String[] getRaces() {
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

    /** @return A String array containing all genders that a model can be assigned to. */
    private String[] getGender() {
        final String[] gender = new String[2];

        gender[0] = Globals.getLocalizedString("male");
        gender[1] = Globals.getLocalizedString("female");

        return gender;
    }

    /**
     * Creates a new VMenuBar for the screen. This does not place
     * the menubar on the specified screen.
     * @param frame The frame from which the import dialog is displayed if it is opened.
     * @param dataManager The datamanager to use when importing a DressNPC model from the database using the import dialog.
     * @return The created VMenuBar.
     */
    private VMenuBar createMenuBar(final JFrame frame, final DataManagerDressNPCs dataManager) {
        final VMenuBar menuBar = new VMenuBar();

        // Create Components:
        final VMenu menu_file = new VMenu(Globals.getLocalizedString("DNS_menu_file"));
        final VMenu menu_function = new VMenu(Globals.getLocalizedString("DNS_menu_function"));

        final VMenuItem item_file_load = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_load"), Globals.getLocalizedString("DNS_menuItem_load_tooltip"));
        final VMenuItem item_file_save = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_save"),Globals.getLocalizedString("DNS_menuItem_save_tooltip"));
        final VMenuItem item_file_import = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_import"), Globals.getLocalizedString("DNS_menuItem_import_tooltip"));

        final VMenuItem item_function_creatureWeaponHandler = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_creatureWeaponHandler"), Globals.getLocalizedString("DNS_menuItem_creatureWeaponHandler_tooltip"));

        // Item File Load - Action Listener:
        item_file_load.addActionListener(e -> {
            final JFileChooser fileChooser = new JFileChooser();
            final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(Globals.getLocalizedString("DNS_filetype_dressNPCFiles"), "npc");

            fileChooser.setFileFilter(extensionFilter);
            fileChooser.setDragEnabled(false);
            fileChooser.setMultiSelectionEnabled(false);

            if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                path_saveLocation = Paths.get(fileChooser.getSelectedFile().toURI());
                model = LoadManager.loadModelFromFile(path_saveLocation);


                field_modelEntryID.setText(String.valueOf(model.getEntry()));

                comboBox_race.setSelectedIndex(model.getRace() - 1); // Subtract one because the Race IDs begin at 1 and not 0.
                comboBox_gender.setSelectedIndex(model.getGender());

                field_skin.setText(String.valueOf(model.getSkin()));
                field_face.setText(String.valueOf(model.getFace()));
                field_hair.setText(String.valueOf(model.getHair()));
                field_hairColor.setText(String.valueOf(model.getHaircolor()));
                field_facialHair.setText(String.valueOf(model.getFacialhair()));

                field_head.setText(String.valueOf(model.getHead()));
                field_shoulders.setText(String.valueOf(model.getShoulders()));
                field_body.setText(String.valueOf(model.getBody()));
                field_chest.setText(String.valueOf(model.getChest()));
                field_waist.setText(String.valueOf(model.getWaist()));
                field_legs.setText(String.valueOf(model.getLegs()));
                field_feet.setText(String.valueOf(model.getFeet()));
                field_wrists.setText(String.valueOf(model.getWrists()));
                field_hands.setText(String.valueOf(model.getHands()));
                field_back.setText(String.valueOf(model.getBack()));
                field_tabard.setText(String.valueOf(model.getTabard()));
            }
        });

        // Item File Save - Action Listener:
        item_file_save.addActionListener(e -> {
            if(path_saveLocation == null) {
                final JFileChooser fileChooser = new JFileChooser();
                final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(Globals.getLocalizedString("DNS_filetype_dressNPCFiles"), "npc");

                fileChooser.setFileFilter(extensionFilter);
                fileChooser.setDragEnabled(false);
                fileChooser.setMultiSelectionEnabled(false);

                if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    path_saveLocation = Paths.get(fileChooser.getSelectedFile().toURI());
                }
            }

            if(path_saveLocation != null) {
                // Ensure that the extension is .npc when saving.
                if(! FilenameUtils.getExtension(path_saveLocation.toFile().getName()).equalsIgnoreCase("npc")) {
                    path_saveLocation = Paths.get(new File(path_saveLocation.toFile().getParentFile(), FilenameUtils.getBaseName(path_saveLocation.toFile().getName()) + ".npc").toURI());
                }

                try {
                    if(createModel()) { // Ensures that the model's fields are all acceptable before saving it.
                        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path_saveLocation.toFile(), false)));

                        out.println("modelEntryID=" + model.getEntry());
                        out.println("race=" + model.getRace());
                        out.println("gender=" + model.getGender());
                        out.println("skin=" + model.getSkin());
                        out.println("face=" + model.getFace());
                        out.println("hair=" + model.getHair());
                        out.println("hairColor=" + model.getHaircolor());
                        out.println("facialHair=" + model.getFacialhair());

                        out.println("head=" + model.getHead());
                        out.println("shoulders=" + model.getShoulders());
                        out.println("body=" + model.getBody());
                        out.println("chest=" + model.getChest());
                        out.println("waist=" + model.getWaist());
                        out.println("legs=" + model.getLegs());
                        out.println("feet=" + model.getFeet());
                        out.println("wrists=" + model.getWrists());
                        out.println("hands=" + model.getHands());
                        out.println("back=" + model.getBack());
                        out.println("tabard=" + model.getTabard());

                        out.close();
                    }
                } catch(final IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        // Item File Import - Action Listener:
        item_file_import.addActionListener(e -> {
            final DressNPCImportDialog dialog = new DressNPCImportDialog(frame, dataManager);
            int selectedModel = dialog.getCurrentlySelectedModelID();

            if(selectedModel >= 0) {
                final Model model = dataManager.getModel(selectedModel);

                field_modelEntryID.setText(String.valueOf(model.getEntry()));

                comboBox_race.setSelectedIndex(model.getRace() - 1); // Subtract one because the Race IDs begin at 1 and not 0.
                comboBox_gender.setSelectedIndex(model.getGender());

                field_skin.setText(String.valueOf(model.getSkin()));
                field_face.setText(String.valueOf(model.getFace()));
                field_hair.setText(String.valueOf(model.getHair()));
                field_hairColor.setText(String.valueOf(model.getHaircolor()));
                field_facialHair.setText(String.valueOf(model.getFacialhair()));

                field_head.setText(String.valueOf(model.getHead()));
                field_shoulders.setText(String.valueOf(model.getShoulders()));
                field_body.setText(String.valueOf(model.getBody()));
                field_chest.setText(String.valueOf(model.getChest()));
                field_waist.setText(String.valueOf(model.getWaist()));
                field_legs.setText(String.valueOf(model.getLegs()));
                field_feet.setText(String.valueOf(model.getFeet()));
                field_wrists.setText(String.valueOf(model.getWrists()));
                field_hands.setText(String.valueOf(model.getHands()));
                field_back.setText(String.valueOf(model.getBack()));
                field_tabard.setText(String.valueOf(model.getTabard()));
            }
        });

        // Item File Creature Weapon Handler - Action Listener:
        item_function_creatureWeaponHandler.addActionListener(e -> {
            frame.setMinimumSize(CreatureWeaponScreen.EXACT_FRAME_DIMENSIONS);
            frame.setPreferredSize(CreatureWeaponScreen.EXACT_FRAME_DIMENSIONS);
            frame.remove(this);
            frame.add(new CreatureWeaponScreen(frame, dataManager));
            frame.pack();
        });

        // Setup Layout:
        menu_file.add(item_file_load);
        menu_file.add(item_file_save);
        menu_file.add(item_file_import);

        menu_function.add(item_function_creatureWeaponHandler);

        menuBar.add(menu_file);
        menuBar.add(menu_function);

        return menuBar;
    }

    /**
     * Attempts to create a Model object from the data entered into
     * the fields and comboboxes. If no data has been entered in
     * certain fields, then zero is automatically entered. If there
     * are any other problems with any of the fields, then the fields
     * error state is set to true and its tooltip is altered to describe
     * the error as well as possible.
     * @return Whether or not the model was created sucessfully.
     */
    private boolean createModel() {
        resetErrorStates();

        // Set all empty fields to zero as any unused fields must be zero.
        if(! field_skin.hasTextBeenEntered()) {
            field_skin.setText("0");
        }

        if(! field_hair.hasTextBeenEntered()) {
            field_hair.setText("0");
        }

        if(! field_hairColor.hasTextBeenEntered()) {
            field_hairColor.setText("0");
        }

        if(! field_facialHair.hasTextBeenEntered()) {
            field_facialHair.setText("0");
        }

        if(! field_head.hasTextBeenEntered()) {
            field_head.setText("0");
        }

        if(! field_shoulders.hasTextBeenEntered()) {
            field_shoulders.setText("0");
        }

        if(! field_body.hasTextBeenEntered()) {
            field_body.setText("0");
        }

        if(! field_chest.hasTextBeenEntered()) {
            field_chest.setText("0");
        }

        if(! field_waist.hasTextBeenEntered()) {
            field_waist.setText("0");
        }

        if(! field_legs.hasTextBeenEntered()) {
            field_legs.setText("0");
        }

        if(! field_feet.hasTextBeenEntered()) {
            field_feet.setText("0");
        }

        if(! field_wrists.hasTextBeenEntered()) {
            field_wrists.setText("0");
        }

        if(! field_hands.hasTextBeenEntered()) {
            field_hands.setText("0");
        }

        if(! field_back.hasTextBeenEntered()) {
            field_back.setText("0");
        }

        if(! field_tabard.hasTextBeenEntered()) {
            field_tabard.setText("0");
        }

        // Get all of the values from every field and determine whether or not
        // the model should be submitted to the database.
        boolean submitModel = true;

        int modelEntryID = 0;

        byte race = (byte) (comboBox_race.getSelectedIndex() + 1); // Add one because the Race IDs begin at 1 and not 0.
        byte gender = (byte) comboBox_gender.getSelectedIndex();
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

        try {
            modelEntryID = field_modelEntryID.getTextAsInteger();

            if(! field_modelEntryID.hasTextBeenEntered()) {
                field_modelEntryID.getFocusListener().setErrorState(true);
                field_modelEntryID.getMouseListener().setErrorState(true);
                field_modelEntryID.setToolTipText(Globals.getLocalizedString("DNS_error_modelEntryID_A"));
                submitModel = false;
            }

            if(modelEntryID < 0) {
                field_modelEntryID.getFocusListener().setErrorState(true);
                field_modelEntryID.getMouseListener().setErrorState(true);
                field_modelEntryID.setToolTipText(Globals.getLocalizedString("DNS_error_modelEntryID_B"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_modelEntryID.getFocusListener().setErrorState(true);
            field_modelEntryID.getMouseListener().setErrorState(true);
            field_modelEntryID.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            skin = field_skin.getTextAsByte();

            if(skin < 0) {
                field_skin.getFocusListener().setErrorState(true);
                field_skin.getMouseListener().setErrorState(true);
                field_skin.setToolTipText(Globals.getLocalizedString("DNS_error_skin_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_skin.getFocusListener().setErrorState(true);
            field_skin.getMouseListener().setErrorState(true);
            field_skin.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            face = field_face.getTextAsByte();

            if(face < 0) {
                field_face.getFocusListener().setErrorState(true);
                field_face.getMouseListener().setErrorState(true);
                field_face.setToolTipText(Globals.getLocalizedString("DNS_error_face_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_face.getFocusListener().setErrorState(true);
            field_face.getMouseListener().setErrorState(true);
            field_face.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            hair = field_hair.getTextAsByte();

            if(hair < 0) {
                field_hair.getFocusListener().setErrorState(true);
                field_hair.getMouseListener().setErrorState(true);
                field_hair.setToolTipText(Globals.getLocalizedString("DNS_error_hair_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_hair.getFocusListener().setErrorState(true);
            field_hair.getMouseListener().setErrorState(true);
            field_hair.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            hairColor = field_hairColor.getTextAsByte();

            if(hairColor < 0) {
                field_hairColor.getFocusListener().setErrorState(true);
                field_hairColor.getMouseListener().setErrorState(true);
                field_hairColor.setToolTipText(Globals.getLocalizedString("DNS_error_hairColor_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_hairColor.getFocusListener().setErrorState(true);
            field_hairColor.getMouseListener().setErrorState(true);
            field_hairColor.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            facialHair = field_facialHair.getTextAsByte();

            if(facialHair < 0) {
                field_facialHair.getFocusListener().setErrorState(true);
                field_facialHair.getMouseListener().setErrorState(true);
                field_facialHair.setToolTipText(Globals.getLocalizedString("DNS_error_facialHair_A"));
                submitModel = false;
            }
        } catch(final NumberFormatException exception) {
            field_facialHair.getFocusListener().setErrorState(true);
            field_facialHair.getMouseListener().setErrorState(true);
            field_facialHair.setToolTipText(Globals.getLocalizedString("DNS_error_byte"));
            submitModel = false;
        }

        try {
            head = field_head.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_head.getFocusListener().setErrorState(true);
            field_head.getMouseListener().setErrorState(true);
            field_head.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            shoulders = field_shoulders.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_shoulders.getFocusListener().setErrorState(true);
            field_shoulders.getMouseListener().setErrorState(true);
            field_shoulders.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            body = field_body.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_body.getFocusListener().setErrorState(true);
            field_body.getMouseListener().setErrorState(true);
            field_body.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            chest = field_chest.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_chest.getFocusListener().setErrorState(true);
            field_chest.getMouseListener().setErrorState(true);
            field_chest.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            waist = field_waist.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_waist.getFocusListener().setErrorState(true);
            field_waist.getMouseListener().setErrorState(true);
            field_waist.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            legs = field_legs.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_legs.getFocusListener().setErrorState(true);
            field_legs.getMouseListener().setErrorState(true);
            field_legs.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            feet = field_feet.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_feet.getFocusListener().setErrorState(true);
            field_feet.getMouseListener().setErrorState(true);
            field_feet.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            wrists = field_wrists.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_wrists.getFocusListener().setErrorState(true);
            field_wrists.getMouseListener().setErrorState(true);
            field_wrists.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            hands = field_hands.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_hands.getFocusListener().setErrorState(true);
            field_hands.getMouseListener().setErrorState(true);
            field_hands.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            back = field_back.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            field_back.getFocusListener().setErrorState(true);
            field_back.getMouseListener().setErrorState(true);
            field_back.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }

        try {
            tabard = field_tabard.getTextAsInteger();
        } catch(final NumberFormatException exception) {
            exception.printStackTrace();
            field_tabard.getFocusListener().setErrorState(true);
            field_tabard.getMouseListener().setErrorState(true);
            field_tabard.setToolTipText(Globals.getLocalizedString("DNS_error_integer"));
            submitModel = false;
        }


        // Setup Model:
        model.setEntry(modelEntryID);

        model.setSkin(skin);
        model.setFace(face);
        model.setHair(hair);
        model.setHairColor(hairColor);
        model.setFacialHair(facialHair);
        model.setRace(race);
        model.setGender(gender);

        model.setHead(head);
        model.setShoulders(shoulders);
        model.setBody(body);
        model.setChest(chest);
        model.setWaist(waist);
        model.setLegs(legs);
        model.setFeet(feet);
        model.setWrists(wrists);
        model.setHands(hands);
        model.setBack(back);
        model.setTabard(tabard);

        return submitModel;
    }

    /**
     * Resets the error states of the focus and mouse listeners of
     * all fields on the screen. Also resets the tooltips of all fields
     * on the screen.
     */
    private void resetErrorStates() {
        // Set the error state of all fields and comboboxes to false.
        field_modelEntryID.getFocusListener().setErrorState(false);
        field_modelEntryID.getMouseListener().setErrorState(false);
        field_modelEntryID.resetTooltip();

        field_skin.getFocusListener().setErrorState(false);
        field_skin.getMouseListener().setErrorState(false);
        field_skin.resetTooltip();

        field_face.getFocusListener().setErrorState(false);
        field_face.getMouseListener().setErrorState(false);
        field_face.resetTooltip();

        field_hair.getFocusListener().setErrorState(false);
        field_hair.getMouseListener().setErrorState(false);
        field_hair.resetTooltip();

        field_hairColor.getFocusListener().setErrorState(false);
        field_hairColor.getMouseListener().setErrorState(false);
        field_hairColor.resetTooltip();

        field_facialHair.getFocusListener().setErrorState(false);
        field_facialHair.getMouseListener().setErrorState(false);
        field_facialHair.resetTooltip();

        field_head.getFocusListener().setErrorState(false);
        field_head.getMouseListener().setErrorState(false);
        field_head.resetTooltip();

        field_shoulders.getFocusListener().setErrorState(false);
        field_shoulders.getMouseListener().setErrorState(false);
        field_shoulders.resetTooltip();

        field_body.getFocusListener().setErrorState(false);
        field_body.getMouseListener().setErrorState(false);
        field_body.resetTooltip();

        field_chest.getFocusListener().setErrorState(false);
        field_chest.getMouseListener().setErrorState(false);
        field_chest.resetTooltip();

        field_waist.getFocusListener().setErrorState(false);
        field_waist.getMouseListener().setErrorState(false);
        field_waist.resetTooltip();

        field_legs.getFocusListener().setErrorState(false);
        field_legs.getMouseListener().setErrorState(false);
        field_legs.resetTooltip();

        field_feet.getFocusListener().setErrorState(false);
        field_feet.getMouseListener().setErrorState(false);
        field_feet.resetTooltip();

        field_wrists.getFocusListener().setErrorState(false);
        field_wrists.getMouseListener().setErrorState(false);
        field_wrists.resetTooltip();

        field_hands.getFocusListener().setErrorState(false);
        field_hands.getMouseListener().setErrorState(false);
        field_hands.resetTooltip();

        field_back.getFocusListener().setErrorState(false);
        field_back.getMouseListener().setErrorState(false);
        field_back.resetTooltip();

        field_tabard.getFocusListener().setErrorState(false);
        field_tabard.getMouseListener().setErrorState(false);
        field_tabard.resetTooltip();
    }
}
