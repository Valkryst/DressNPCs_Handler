package gui;

import component.*;
import core.Globals;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class DressNPCView extends VPanel {
    /** The minimum size of the JFrame that contains the DressNPCScreen. */
    public final static Dimension EXACT_FRAME_DIMENSIONS = new Dimension(315, 360);

    /** A field to enter the model entry ID. */
    private final VTextField field_modelEntryID = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, "Model Entry ID", "Tooltip");

    /** A combo box to select the race of the model. */
    private final JComboBox<String> comboBox_race;
    /** A combo box to select the gender of the model. */
    private final JComboBox<String> comboBox_gender;

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

    private final VSection section_character = new VSection(new MigLayout("al center center, wrap, gapy 4"));
    private final VSection section_armor = new VSection(new MigLayout("al center center, wrap, gapy 4"));

    private final VButton button_submit = new VButton(Globals.getLocalizedString("DNS_button_submit"));
    private final VButton button_reset = new VButton(Globals.getLocalizedString("DNS_button_reset"));

    final VMenuBar menuBar = new VMenuBar();

    final VMenu menu_file = new VMenu(Globals.getLocalizedString("DNS_menu_file"));
    final VMenu menu_function = new VMenu(Globals.getLocalizedString("DNS_menu_function"));

    final VMenuItem item_file_load = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_load"), Globals.getLocalizedString("DNS_menuItem_load_tooltip"));
    final VMenuItem item_file_save = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_save"),Globals.getLocalizedString("DNS_menuItem_save_tooltip"));
    final VMenuItem item_file_import = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_import"), Globals.getLocalizedString("DNS_menuItem_import_tooltip"));

    final VMenuItem item_function_creatureWeaponHandler = new VMenuItem(Globals.getLocalizedString("DNS_menuItem_creatureWeaponHandler"), Globals.getLocalizedString("DNS_menuItem_creatureWeaponHandler_tooltip"));

    public DressNPCView(final DressNPCController controller, final DressNPCModel model) {
        this.comboBox_race = new JComboBox<>(model.getRaces());
        this.comboBox_gender = new JComboBox<>(model.getGender());

        // Set Component Colors:
        comboBox_race.setBackground(VComponentGlobals.MIDGROUND_COLOR);
        comboBox_race.setForeground(VComponentGlobals.TEXT_COLOR);

        comboBox_gender.setBackground(VComponentGlobals.MIDGROUND_COLOR);
        comboBox_gender.setForeground(VComponentGlobals.TEXT_COLOR);

        // Set Section Titles:
        section_character.setSectionTitle(Globals.getLocalizedString("DNS_section_character"));
        section_armor.setSectionTitle(Globals.getLocalizedString("DNS_section_armor"));

        // Add Action Listeners:
        button_submit.addActionListener(controller);
        button_reset.addActionListener(controller);

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

        controller.getFrame().setJMenuBar(createMenuBar(controller));
    }

    /**
     * Creates a new VMenuBar for the screen. This does not place
     * the menubar on the specified screen.
     * @param controller todo JavaDoc
     * @return The created VMenuBar.
     */
    private VMenuBar createMenuBar(final DressNPCController controller) {
        // Add Action Listeners:
        item_file_load.addActionListener(controller);
        item_file_save.addActionListener(controller);
        item_file_import.addActionListener(controller);
        item_function_creatureWeaponHandler.addActionListener(controller);

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
     * Resets the error states of the focus and mouse listeners of
     * all fields on the screen. Also resets the tooltips of all fields
     * on the screen.
     */
    public void resetErrorStates() {
        // Set the error state of all fields and comboboxes to false.
        field_modelEntryID.setErrorState(false);
        field_modelEntryID.resetTooltip();

        field_skin.setErrorState(false);
        field_skin.resetTooltip();

        field_face.setErrorState(false);
        field_face.resetTooltip();

        field_hair.setErrorState(false);
        field_hair.resetTooltip();

        field_hairColor.setErrorState(false);
        field_hairColor.resetTooltip();

        field_facialHair.setErrorState(false);
        field_facialHair.resetTooltip();

        field_head.setErrorState(false);
        field_head.resetTooltip();

        field_shoulders.setErrorState(false);
        field_shoulders.resetTooltip();

        field_body.setErrorState(false);
        field_body.resetTooltip();

        field_chest.setErrorState(false);
        field_chest.resetTooltip();

        field_waist.setErrorState(false);
        field_waist.resetTooltip();

        field_legs.setErrorState(false);
        field_legs.resetTooltip();

        field_feet.setErrorState(false);
        field_feet.resetTooltip();

        field_wrists.setErrorState(false);
        field_wrists.resetTooltip();

        field_hands.setErrorState(false);
        field_hands.resetTooltip();

        field_back.setErrorState(false);
        field_back.resetTooltip();

        field_tabard.setErrorState(false);
        field_tabard.resetTooltip();
    }

    public VTextField getField_modelEntryID() {
        return field_modelEntryID;
    }

    public JComboBox<String> getComboBox_race() {
        return comboBox_race;
    }

    public JComboBox<String> getComboBox_gender() {
        return comboBox_gender;
    }

    public VTextField getField_skin() {
        return field_skin;
    }

    public VTextField getField_face() {
        return field_face;
    }

    public VTextField getField_hair() {
        return field_hair;
    }

    public VTextField getField_hairColor() {
        return field_hairColor;
    }

    public VTextField getField_facialHair() {
        return field_facialHair;
    }

    public VTextField getField_head() {
        return field_head;
    }

    public VTextField getField_shoulders() {
        return field_shoulders;
    }

    public VTextField getField_body() {
        return field_body;
    }

    public VTextField getField_chest() {
        return field_chest;
    }

    public VTextField getField_waist() {
        return field_waist;
    }

    public VTextField getField_legs() {
        return field_legs;
    }

    public VTextField getField_feet() {
        return field_feet;
    }

    public VTextField getField_wrists() {
        return field_wrists;
    }

    public VTextField getField_hands() {
        return field_hands;
    }

    public VTextField getField_back() {
        return field_back;
    }

    public VTextField getField_tabard() {
        return field_tabard;
    }

    public VButton getButton_submit() {
        return button_submit;
    }

    public VButton getButton_reset() {
        return button_reset;
    }

    public VMenuItem getItem_file_load() {
        return item_file_load;
    }

    public VMenuItem getItem_file_save() {
        return item_file_save;
    }

    public VMenuItem getItem_file_import() {
        return item_file_import;
    }

    public VMenuItem getItem_function_creatureWeaponHandler() {
        return item_function_creatureWeaponHandler;
    }
}
