package gui;

import services.DataManagerDressNPCs;

public class DressNPCImportDialogModel {
    /** The ID of the currently selected model. This is -1 if no model ID has been selected. */
    private final int[] currentlySelectedModelID = {-1}; // Variables used in a lambda expression must be final. To get around this, we're using an array for a single variable.

    private final DataManagerDressNPCs dataManager;

    public DressNPCImportDialogModel(final DataManagerDressNPCs dataManager) {
        this.dataManager = dataManager;
    }

    public int getCurrentlySelectedModelID() {
        return currentlySelectedModelID[0];
    }

    public DataManagerDressNPCs getDataManager() {
        return dataManager;
    }

    public void setCurrentlySelectedModelID(final int currentlySelectedModelID) {
        this.currentlySelectedModelID[0] = currentlySelectedModelID;
    }
}
