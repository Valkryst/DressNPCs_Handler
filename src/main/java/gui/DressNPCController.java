package gui;

import core.Globals;
import objects.Model;
import org.apache.commons.io.FilenameUtils;
import services.DataManagerDressNPCs;
import services.LoadManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DressNPCController implements ActionListener {
    /** The frame in which to place the dressnpc screen view. */
    private final JFrame frame;

    /** The dressnpc screen's model. **/
    private final DressNPCModel model;
    /** The dressnpc screen's view. **/
    private final DressNPCView view;

    /**
     * Construct a new dressnpc screen controller.
     * @param frame The frame in which to place the dressnpc screen view.
     * @param dataManager todo JavaDoc
     */
    public DressNPCController(final JFrame frame, final DataManagerDressNPCs dataManager) {
        this.frame = frame;
        this.model = new DressNPCModel(dataManager);
        this.view = new DressNPCView(this, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();

        if(source.equals(view.getButton_submit())) {
            model.submitNPCModel(view);
        } else if(source.equals(view.getButton_reset())) {
            view.resetErrorStates();

            // Reset text and combobox selections:
            view.getField_modelEntryID().setText(null);

            view.getField_skin().setText(null);
            view.getField_face().setText(null);
            view.getField_hair().setText(null);
            view.getField_hairColor().setText(null);
            view.getField_facialHair().setText(null);
            view.getComboBox_gender().setSelectedIndex(0);
            view.getComboBox_race().setSelectedIndex(0);

            view.getField_head().setText(null);
            view.getField_shoulders().setText(null);
            view.getField_body().setText(null);
            view.getField_chest().setText(null);
            view.getField_waist().setText(null);
            view.getField_legs().setText(null);
            view.getField_feet().setText(null);
            view.getField_wrists().setText(null);
            view.getField_hands().setText(null);
            view.getField_back().setText(null);
            view.getField_tabard().setText(null);
        } else if(source.equals(view.getItem_file_load())) {
            final Model npcModel = model.getNpcModel();
            final JFileChooser fileChooser = new JFileChooser();
            final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(Globals.getLocalizedString("DNS_filetype_dressNPCFiles"), "npc");

            fileChooser.setFileFilter(extensionFilter);
            fileChooser.setDragEnabled(false);
            fileChooser.setMultiSelectionEnabled(false);

            if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                model.setPath_saveLocation(fileChooser.getSelectedFile().toPath());
                model.setNpcModel(LoadManager.loadModelFromFile(model.getPath_saveLocation()));


                view.getField_modelEntryID().setText(String.valueOf(npcModel.getEntry()));

                view.getComboBox_race().setSelectedIndex(npcModel.getRace() - 1); // Subtract one because the Race IDs begin at 1 and not 0.
                view.getComboBox_gender().setSelectedIndex(npcModel.getGender());

                view.getField_skin().setText(String.valueOf(npcModel.getSkin()));
                view.getField_face().setText(String.valueOf(npcModel.getFace()));
                view.getField_hair().setText(String.valueOf(npcModel.getHair()));
                view.getField_hairColor().setText(String.valueOf(npcModel.getHaircolor()));
                view.getField_facialHair().setText(String.valueOf(npcModel.getFacialhair()));

                view.getField_head().setText(String.valueOf(npcModel.getHead()));
                view.getField_shoulders().setText(String.valueOf(npcModel.getShoulders()));
                view.getField_body().setText(String.valueOf(npcModel.getBody()));
                view.getField_chest().setText(String.valueOf(npcModel.getChest()));
                view.getField_waist().setText(String.valueOf(npcModel.getWaist()));
                view.getField_legs().setText(String.valueOf(npcModel.getLegs()));
                view.getField_feet().setText(String.valueOf(npcModel.getFeet()));
                view.getField_wrists().setText(String.valueOf(npcModel.getWrists()));
                view.getField_hands().setText(String.valueOf(npcModel.getHands()));
                view.getField_back().setText(String.valueOf(npcModel.getBack()));
                view.getField_tabard().setText(String.valueOf(npcModel.getTabard()));
            }
        } else if(source.equals(view.getItem_file_save())) {
            final Model npcModel = model.getNpcModel();

            if(model.getPath_saveLocation() == null) {
                final JFileChooser fileChooser = new JFileChooser();
                final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(Globals.getLocalizedString("DNS_filetype_dressNPCFiles"), "npc");

                fileChooser.setFileFilter(extensionFilter);
                fileChooser.setDragEnabled(false);
                fileChooser.setMultiSelectionEnabled(false);

                if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    model.setPath_saveLocation(fileChooser.getSelectedFile().toPath());
                }
            }

            if(model.getPath_saveLocation() != null) {
                // Ensure that the extension is .npc when saving.
                if(! FilenameUtils.getExtension(model.getPath_saveLocation().toFile().getName()).equalsIgnoreCase("npc")) {
                    final Path path = Paths.get(new File(model.getPath_saveLocation().toFile().getParentFile(), FilenameUtils.getBaseName(model.getPath_saveLocation().toFile().getName()) + ".npc").toURI());
                    model.setPath_saveLocation(path);
                }

                try {
                    if(model.submitNPCModel(view)) { // Ensures that the model's fields are all acceptable before saving it.
                        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(model.getPath_saveLocation().toFile(), false)));

                        out.println("modelEntryID=" + npcModel.getEntry());
                        out.println("race=" + npcModel.getRace());
                        out.println("gender=" + npcModel.getGender());
                        out.println("skin=" + npcModel.getSkin());
                        out.println("face=" + npcModel.getFace());
                        out.println("hair=" + npcModel.getHair());
                        out.println("hairColor=" + npcModel.getHaircolor());
                        out.println("facialHair=" + npcModel.getFacialhair());

                        out.println("head=" + npcModel.getHead());
                        out.println("shoulders=" + npcModel.getShoulders());
                        out.println("body=" + npcModel.getBody());
                        out.println("chest=" + npcModel.getChest());
                        out.println("waist=" + npcModel.getWaist());
                        out.println("legs=" + npcModel.getLegs());
                        out.println("feet=" + npcModel.getFeet());
                        out.println("wrists=" + npcModel.getWrists());
                        out.println("hands=" + npcModel.getHands());
                        out.println("back=" + npcModel.getBack());
                        out.println("tabard=" + npcModel.getTabard());

                        out.close();
                    }
                } catch(final IOException exception) {
                    exception.printStackTrace();
                }
            }
        } else if(source.equals(view.getItem_file_import())) {
            final DressNPCImportDialog dialog = new DressNPCImportDialog(frame, model.getDataManager());
            int selectedModel = dialog.getCurrentlySelectedModelID();

            if(selectedModel >= 0) {
                final Model npcModel = model.getDataManager().getModel(selectedModel);

                view.getField_modelEntryID().setText(String.valueOf(npcModel.getEntry()));

                view.getComboBox_race().setSelectedIndex(npcModel.getRace() - 1); // Subtract one because the Race IDs begin at 1 and not 0.
                view.getComboBox_gender().setSelectedIndex(npcModel.getGender());

                view.getField_skin().setText(String.valueOf(npcModel.getSkin()));
                view.getField_face().setText(String.valueOf(npcModel.getFace()));
                view.getField_hair().setText(String.valueOf(npcModel.getHair()));
                view.getField_hairColor().setText(String.valueOf(npcModel.getHaircolor()));
                view.getField_facialHair().setText(String.valueOf(npcModel.getFacialhair()));

                view.getField_head().setText(String.valueOf(npcModel.getHead()));
                view.getField_shoulders().setText(String.valueOf(npcModel.getShoulders()));
                view.getField_body().setText(String.valueOf(npcModel.getBody()));
                view.getField_chest().setText(String.valueOf(npcModel.getChest()));
                view.getField_waist().setText(String.valueOf(npcModel.getWaist()));
                view.getField_legs().setText(String.valueOf(npcModel.getLegs()));
                view.getField_feet().setText(String.valueOf(npcModel.getFeet()));
                view.getField_wrists().setText(String.valueOf(npcModel.getWrists()));
                view.getField_hands().setText(String.valueOf(npcModel.getHands()));
                view.getField_back().setText(String.valueOf(npcModel.getBack()));
                view.getField_tabard().setText(String.valueOf(npcModel.getTabard()));
            }
        } else if(source.equals(view.getItem_function_creatureWeaponHandler())) {
            frame.setMinimumSize(CreatureWeaponScreen.EXACT_FRAME_DIMENSIONS);
            frame.setPreferredSize(CreatureWeaponScreen.EXACT_FRAME_DIMENSIONS);
            frame.remove(view);
            frame.add(new CreatureWeaponScreen(frame, model.getDataManager()));
            frame.pack();
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public DressNPCView getView() {
        return view;
    }
}
