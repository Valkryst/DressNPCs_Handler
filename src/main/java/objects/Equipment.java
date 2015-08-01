package objects;

public class Equipment {
    /** The entry ID of the equipment set. The entry ID corresponds to a record in the creature_template table. */
    private int entryID = -1;
    /** The set ID of the equipment set. */
    private byte setID = -1;
    /** The entry ID of the main-hand weapon. The entry ID corresponds to a record in the Item.dbc file. */
    private int mainHand = -1;
    /** The entry ID of the off-hand weapon. The entry ID corresponds to a record in the Item.dbc file. */
    private int offHand = -1;
    /** The entry ID of the ranged weapon. The entry ID corresponds to a record in the Item.dbc file. */
    private int ranged = -1;

    /**
     * Sets the entry ID.
     * @param entryID The entry ID of the creature which the equipment set will be used for.
     * @return Whether or not the supplied entry ID value is acceptable and has been set.
     */
    public boolean setNPCEntryID(final int entryID) {
        if(entryID < 0) {
            return false;
        } else {
            this.entryID = entryID;
            return true;
        }
    }

    /**
     * Sets the set ID.
     * @param setID The set ID of the equipment set.
     * @return Whether or not the supplied set ID value is acceptable and has been set.
     */
    public boolean setSetID(final byte setID) {
        if(setID < 0) {
            return false;
        } else {
            this.setID = setID;
            return true;
        }
    }

    /**
     * Sets the main-hand entry ID.
     * @param mainHand The entry ID of the main-hand weapon.
     * @return Whether or not the supplied entry ID value is acceptable and has been set.
     */
    public boolean setMainHand(final int mainHand) {
        if(mainHand < 0) {
            return false;
        } else {
            this.mainHand = mainHand;
            return true;
        }
    }

    /**
     * Sets the off-hand entry ID.
     * @param offHand The entry ID of the off-hand weapon.
     * @return Whether or not the supplied entry ID value is acceptable and has been set.
     */
    public boolean setOffHand(final int offHand) {
        if(offHand < 0) {
            return false;
        } else {
            this.offHand = offHand;
            return true;
        }
    }

    /**
     * Sets the ranged entry ID.
     * @param ranged The entry ID of the ranged weapon.
     * @return Whether or not the supplied entry ID value is acceptable and has been set.
     */
    public boolean setRanged(final int ranged) {
        if(ranged < 0) {
            return false;
        } else {
            this.ranged = ranged;
            return true;
        }
    }

    /** @return The entry ID of the equipment set. The entry ID corresponds to a record in the creature_template table. */
    public int getEntryID() {
        return entryID;
    }

    /** @return The set ID of the equipment set. */
    public byte getSetID() {
        return setID;
    }

    /** @return The entry ID of the main-hand weapon. The entry ID corresponds to a record in the Item.dbc file. */
    public int getMainHand() {
        return mainHand;
    }

    /** @return The entry ID of the off-hand weapon. The entry ID corresponds to a record in the Item.dbc file. */
    public int getOffHand() {
        return offHand;
    }

    /** @return The entry ID of the ranged weapon. The entry ID corresponds to a record in the Item.dbc file. */
    public int getRanged() {
        return ranged;
    }
}
