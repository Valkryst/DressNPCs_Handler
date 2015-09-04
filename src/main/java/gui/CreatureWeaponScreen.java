package gui;

import component.*;
import core.Globals;
import net.miginfocom.swing.MigLayout;
import objects.Equipment;
import services.DataManagerDressNPCs;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class CreatureWeaponScreen extends JPanel {
    /** The minimum size of the JFrame that contains the DressNPCScreen. */
    public final static Dimension EXACT_FRAME_DIMENSIONS = new Dimension(300, 375);

    /** The path to the file in which the DressNPC form should be saved to whenever the user saves. */
    private Path path_saveLocation;

    public CreatureWeaponScreen(final JFrame frame, final DataManagerDressNPCs dataManager) {
        // Create Components:
        final VTextField[] fields_npcEntryID = new VTextField[4];
        final VTextField[] fields_equipmentSetID = new VTextField[4];
        final VTextField[] fields_mainHand = new VTextField[4];
        final VTextField[] fields_offHand = new VTextField[4];
        final VTextField[] fields_ranged = new VTextField[4];
        final VSection[] sections_weapons = new VSection[4];

        final String text_npcEntryID = Globals.getLocalizedString("CWC_field_npcEntryID");
        final String text_npcEntryID_tooltip = Globals.getLocalizedString("CWC_field_npcEntryID_tooltip");
        final String text_equipmentSetID = Globals.getLocalizedString("CWC_field_equipmentSetID");
        final String text_equipmentSetID_tooltip = Globals.getLocalizedString("CWC_field_equipmentSetID_tooltip");
        final String text_mainHand = Globals.getLocalizedString("CWC_field_mainHand");
        final String text_mainHand_tooltip = Globals.getLocalizedString("CWC_field_mainHand_tooltip");
        final String text_offHand = Globals.getLocalizedString("CWC_field_offHand");
        final String text_offHand_toltip = Globals.getLocalizedString("CWC_field_offHand_tooltip");
        final String text_ranged = Globals.getLocalizedString("CWC_field_ranged");
        final String text_ranged_tooltip = Globals.getLocalizedString("CWC_field_ranged_tooltip");

        final String text_sectionWeapons = Globals.getLocalizedString("CWC_section_weapons");

        fields_npcEntryID[0] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_npcEntryID, text_npcEntryID_tooltip);
        fields_npcEntryID[1] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_npcEntryID, text_npcEntryID_tooltip);
        fields_npcEntryID[2] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_npcEntryID, text_npcEntryID_tooltip);
        fields_npcEntryID[3] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_npcEntryID, text_npcEntryID_tooltip);

        fields_equipmentSetID[0] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_equipmentSetID, text_equipmentSetID_tooltip);
        fields_equipmentSetID[1] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_equipmentSetID, text_equipmentSetID_tooltip);
        fields_equipmentSetID[2] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_equipmentSetID, text_equipmentSetID_tooltip);
        fields_equipmentSetID[3] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_equipmentSetID, text_equipmentSetID_tooltip);

        fields_mainHand[0] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_mainHand, text_mainHand_tooltip);
        fields_mainHand[1] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_mainHand, text_mainHand_tooltip);
        fields_mainHand[2] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_mainHand, text_mainHand_tooltip);
        fields_mainHand[3] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_mainHand, text_mainHand_tooltip);

        fields_offHand[0] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_offHand, text_offHand_toltip);
        fields_offHand[1] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_offHand, text_offHand_toltip);
        fields_offHand[2] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_offHand, text_offHand_toltip);
        fields_offHand[3] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_offHand, text_offHand_toltip);

        fields_ranged[0] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_ranged, text_ranged_tooltip);
        fields_ranged[1] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_ranged, text_ranged_tooltip);
        fields_ranged[2] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_ranged, text_ranged_tooltip);
        fields_ranged[3] = new VTextField(Globals.MAX_TEXTUAL_LENGTH_OF_AN_INTEGER_WITH_SIGN, text_ranged, text_ranged_tooltip);

        sections_weapons[0] = new VSection(new MigLayout("al center center, wrap, gapy 4"));
        sections_weapons[1] = new VSection(new MigLayout("al center center, wrap, gapy 4"));
        sections_weapons[2] = new VSection(new MigLayout("al center center, wrap, gapy 4"));
        sections_weapons[3] = new VSection(new MigLayout("al center center, wrap, gapy 4"));

        final VButton button_submit = new VButton(Globals.getLocalizedString("CSC_button_submit"));
        final VButton button_reset = new VButton(Globals.getLocalizedString("DNS_button_reset"));

        // Set Section Titles:
        sections_weapons[0].setSectionTitle(text_sectionWeapons + "1");
        sections_weapons[1].setSectionTitle(text_sectionWeapons + "2");
        sections_weapons[2].setSectionTitle(text_sectionWeapons + "3");
        sections_weapons[3].setSectionTitle(text_sectionWeapons + "4");

        // Set Component Colors:
        this.setBackground(VComponentGlobals.BACKGROUND_COLOR);

        // Button Submit - Action Listener:
        button_submit.addActionListener(e -> {
                for(int i = 0; i < sections_weapons.length; i++) {
                    // Set the error state of all fields to false.
                    fields_npcEntryID[i].getFocusListener().setErrorState(false);
                    fields_npcEntryID[i].getMouseListener().setErrorState(false);
                    fields_npcEntryID[i].resetTooltip();

                    fields_equipmentSetID[i].getFocusListener().setErrorState(false);
                    fields_equipmentSetID[i].getMouseListener().setErrorState(false);
                    fields_equipmentSetID[i].resetTooltip();

                    fields_mainHand[i].getFocusListener().setErrorState(false);
                    fields_mainHand[i].getMouseListener().setErrorState(false);
                    fields_mainHand[i].resetTooltip();

                    fields_offHand[i].getFocusListener().setErrorState(false);
                    fields_offHand[i].getMouseListener().setErrorState(false);
                    fields_offHand[i].resetTooltip();

                    fields_ranged[i].getFocusListener().setErrorState(false);
                    fields_ranged[i].getMouseListener().setErrorState(false);
                    fields_ranged[i].resetTooltip();

                    // Set all empty fields to zero as any unused fields must be zero.
                    if(! fields_mainHand[i].hasTextBeenEntered()) {
                        fields_mainHand[i].setText("0");
                    }

                    if(! fields_offHand[i].hasTextBeenEntered()) {
                        fields_offHand[i].setText("0");
                    }

                    if(! fields_ranged[i].hasTextBeenEntered()) {
                        fields_ranged[i].setText("0");
                    }

                    // Get all of the values from every field and determine whether or not
                    // the model should be submitted to the database.
                    boolean submitEquipment = true;

                    int npcEntryID = 0;
                    byte setID = 0;
                    int mainHand = 0;
                    int offHand = 0;
                    int ranged = 0;

                    try {
                        npcEntryID = fields_npcEntryID[i].getTextAsInteger();

                        if(! fields_npcEntryID[i].hasTextBeenEntered()) {
                            fields_npcEntryID[i].getFocusListener().setErrorState(true);
                            fields_npcEntryID[i].getMouseListener().setErrorState(true);
                            fields_npcEntryID[i].setTooltip(Globals.getLocalizedString("CWC_field_npcEntryID_tooltip_error_A"));
                            submitEquipment = false;
                        }
                    } catch(final NumberFormatException exception) {
                        fields_npcEntryID[i].getFocusListener().setErrorState(true);
                        fields_npcEntryID[i].getMouseListener().setErrorState(true);
                        fields_npcEntryID[i].setTooltip(Globals.getLocalizedString("CWC_field_npcEntryID_tooltip_error_B"));
                        submitEquipment = false;
                    }

                    try {
                        setID = fields_equipmentSetID[i].getTextAsByte();

                        if(! fields_equipmentSetID[i].hasTextBeenEntered()) {
                            fields_equipmentSetID[i].getFocusListener().setErrorState(true);
                            fields_equipmentSetID[i].getMouseListener().setErrorState(true);
                            fields_equipmentSetID[i].setTooltip(Globals.getLocalizedString("CWC_field_equipmentSetID_tooltip_error_A"));
                            submitEquipment = false;
                        }

                        if(setID < 0) {
                            fields_equipmentSetID[i].getFocusListener().setErrorState(true);
                            fields_equipmentSetID[i].getMouseListener().setErrorState(true);
                            fields_equipmentSetID[i].setTooltip(Globals.getLocalizedString("CWC_field_equipmentSetID_tooltip_error_B"));
                            submitEquipment = false;
                        }
                    } catch(final NumberFormatException exception) {
                        fields_equipmentSetID[i].getFocusListener().setErrorState(true);
                        fields_equipmentSetID[i].getMouseListener().setErrorState(true);
                        fields_equipmentSetID[i].setTooltip(Globals.getLocalizedString("CWC_field_npcEntryID_tooltip_error_B"));
                        submitEquipment = false;
                    }

                    try {
                        mainHand = fields_mainHand[i].getTextAsInteger();
                    } catch(final NumberFormatException exception) {
                        fields_mainHand[i].getFocusListener().setErrorState(true);
                        fields_mainHand[i].getMouseListener().setErrorState(true);
                        fields_mainHand[i].setTooltip(Globals.getLocalizedString("CWC_tooltip_error_integer"));
                        submitEquipment = false;
                    }

                    try {
                        offHand = fields_offHand[i].getTextAsInteger();
                    } catch(final NumberFormatException exception) {
                        fields_offHand[i].getFocusListener().setErrorState(true);
                        fields_offHand[i].getMouseListener().setErrorState(true);
                        fields_offHand[i].setTooltip(Globals.getLocalizedString("CWC_tooltip_error_integer"));
                        submitEquipment = false;
                    }

                    try {
                        ranged = fields_ranged[i].getTextAsInteger();
                    } catch(final NumberFormatException exception) {
                        fields_ranged[i].getFocusListener().setErrorState(true);
                        fields_ranged[i].getMouseListener().setErrorState(true);
                        fields_ranged[i].setTooltip(Globals.getLocalizedString("CWC_tooltip_error_integer"));
                        submitEquipment = false;
                    }

                    // Either submit the model or do nothing as the error messages have already been set.
                    if(submitEquipment) {
                        // Setup Equipment:
                        final Equipment equipment = new Equipment();
                        equipment.setNPCEntryID(npcEntryID);
                        equipment.setSetID(setID);
                        equipment.setMainHand(mainHand);
                        equipment.setOffHand(offHand);
                        equipment.setRanged(ranged);

                        dataManager.setEquipment(equipment.getEntryID(), equipment);
                    }
                }
            }
        );

        // Button Reset - Action Listener:
        button_reset.addActionListener(e -> {
                for(int i = 0; i < sections_weapons.length; i++) {
                    // Set the error state of all fields and comboboxes to false.
                    fields_npcEntryID[i].getFocusListener().setErrorState(false);
                    fields_npcEntryID[i].getMouseListener().setErrorState(false);
                    fields_npcEntryID[i].resetTooltip();

                    fields_equipmentSetID[i].getFocusListener().setErrorState(false);
                    fields_equipmentSetID[i].getMouseListener().setErrorState(false);
                    fields_equipmentSetID[i].resetTooltip();

                    fields_mainHand[i].getFocusListener().setErrorState(false);
                    fields_mainHand[i].getMouseListener().setErrorState(false);
                    fields_mainHand[i].resetTooltip();

                    fields_offHand[i].getFocusListener().setErrorState(false);
                    fields_offHand[i].getMouseListener().setErrorState(false);
                    fields_offHand[i].resetTooltip();

                    fields_ranged[i].getFocusListener().setErrorState(false);
                    fields_ranged[i].getMouseListener().setErrorState(false);
                    fields_ranged[i].resetTooltip();

                    // Reset field selections:
                    fields_npcEntryID[i].setText(null);
                    fields_mainHand[i].setText(null);
                    fields_offHand[i].setText(null);
                    fields_ranged[i].setText(null);
                }
            }
        );

        // Setup Layout:
        for(int i = 0; i < sections_weapons.length ; i++) {
            sections_weapons[i].add(fields_npcEntryID[i], "growx");
            sections_weapons[i].add(fields_equipmentSetID[i], "growx");
            sections_weapons[i].add(fields_mainHand[i], "growx");
            sections_weapons[i].add(fields_offHand[i], "growx");
            sections_weapons[i].add(fields_ranged[i], "growx");
        }

        final JPanel panel_top = new JPanel(new GridLayout(2, 2));
        panel_top.add(sections_weapons[0]);
        panel_top.add(sections_weapons[1]);
        panel_top.add(sections_weapons[2]);
        panel_top.add(sections_weapons[3]);

        final JPanel panel_bottom = new JPanel(new MigLayout("align center center, gapx 4"));
        panel_bottom.setBackground(VComponentGlobals.BACKGROUND_COLOR);
        panel_bottom.add(button_submit);
        panel_bottom.add(button_reset);

        this.setLayout(new MigLayout("fill"));
        this.add(panel_top);
        this.add(panel_bottom, "dock south");

        frame.setJMenuBar(createMenuBar(frame, dataManager));
    }

    // todo JavaDoc
    private VMenuBar createMenuBar(final JFrame frame, final DataManagerDressNPCs dataManager) {
        final VMenuBar menuBar = new VMenuBar();

        // Create Components:
        final VMenu menu_function = new VMenu(Globals.getLocalizedString("CWC_menu_function"));

        final VMenuItem item_function_dressNPCHandler = new VMenuItem(Globals.getLocalizedString("CWC_menuItem_dressNPCHandler"), Globals.getLocalizedString("CWC_menuItem_dressNPCHandler_tooltip"));

        // Item File DressNPC Handler - Action Listener:
        item_function_dressNPCHandler.addActionListener(e -> {
            frame.setMinimumSize(DressNPCView.EXACT_FRAME_DIMENSIONS);
            frame.setPreferredSize(DressNPCView.EXACT_FRAME_DIMENSIONS);
            frame.remove(this);
            frame.add(new DressNPCController(frame, dataManager).getView());
            frame.pack();
        });

        // Setup Layout:
        menu_function.add(item_function_dressNPCHandler);

        menuBar.add(menu_function);

        return menuBar;
    }
}
