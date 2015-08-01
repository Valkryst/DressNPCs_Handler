package objects;

public class Model {
    /** The entry ID of the model. */
    private int entry = -1;

    /** The race ID of the model. */
    private byte race = -1;
    /** The gender ID of the model. */
    private byte gender = -1;
    /** The skin ID of the model. */
    private byte skin = -1;
    /** The face ID of the model. */
    private byte face = -1;
    /** The hair ID of the model. */
    private byte hair = -1;
    /** The hair color ID of the model. */
    private byte hairColor = -1;
    /** The facial hair ID of the model. */
    private byte facialHair = -1;

    /** The entry ID of the head equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int head = -1;
    /** The entry ID of the shoulder equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int shoulders = -1;
    /** The entry ID of the body equipment (shirt) of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int body = -1;
    /** The entry ID of the chest equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int chest = -1;
    /** The entry ID of the waist equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int waist = -1;
    /** The entry ID of the leg equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int legs = -1;
    /** The entry ID of the foot equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int feet = -1;
    /** The entry ID of the wrist equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int wrists = -1;
    /** The entry ID of the hand equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int hands = -1;
    /** The entry ID of the back equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int back = -1;
    /** The entry ID of the tabard equipment of the model. This ID corresponds to either a displayID or a record in the Item.dbc file. */
    private int tabard = -1;

    /**
     * Sets the entry ID of the model.
     * @param entry The entry ID of the model.
     * @return Whether or not the supplied entry ID value is acceptable and has been set.
     */
    public boolean setEntry(final int entry) {
        if(entry < 0) {
            return false;
        } else {
            this.entry = entry;
            return true;
        }
    }

    /**
     * Sets the race ID of the model.
     * @param race The race ID of the model.
     * @return Whether or not the supplied race ID value is acceptable and has been set.
     */
    public boolean setRace(final byte race) {
        if(race > 21 || race < 1) {
            return false;
        } else {
            this.race = race;
            return true;
        }
    }

    /**
     * Sets the gender ID of the model.
     * @param gender The gender ID of the model.
     * @return Whether or not the supplied gender ID value is acceptable and has been set.
     */
    public boolean setGender(final byte gender) {
        if(gender < 0 || gender > 1) {
            return false;
        } else {
            this.gender = gender;
            return true;
        }
    }

    /**
     * Sets the skin ID of the model.
     * @param skin The skin ID of the model.
     * @return Whether or not the supplied skin ID value is acceptable and has been set.
     */
    public boolean setSkin(final byte skin) {
        if(skin < 0) {
            return false;
        } else {
            this.skin = skin;
            return true;
        }
    }

    /**
     * Sets the face ID of the model.
     * @param face The face ID of the model.
     * @return Whether or not the supplied face ID value is acceptable and has been set.
     */
    public boolean setFace(final byte face) {
        if(face < 0) {
            return false;
        } else {
            this.face = face;
            return true;
        }
    }

    /**
     * Sets the hair ID of the model.
     * @param hair The hair ID of the model.
     * @return Whether or not the supplied hair ID value is acceptable and has been set.
     */
    public boolean setHair(final byte hair) {
        if(hair < 0) {
            return false;
        } else {
            this.hair = hair;
            return true;
        }
    }

    /**
     * Sets the hair color ID of the model.
     * @param hairColor The hair color ID of the model.
     * @return Whether or not the supplied hair color ID value is acceptable and has been set.
     */
    public boolean setHairColor(final byte hairColor) {
        if(hairColor < 0) {
            return false;
        } else {
            this.hairColor = hairColor;
            return true;
        }
    }

    /**
     * Sets the facial hair ID of the model.
     * @param facialHair The facial hair ID of the model.
     * @return Whether or not the supplied facial hair ID value is acceptable and has been set.
     */
    public boolean setFacialHair(final byte facialHair) {
        if(facialHair < 0) {
            return false;
        } else {
            this.facialHair = facialHair;
            return true;
        }
    }

    public void setHead(final int head) {
        this.head = head;
    }

    public void setShoulders(final int shoulders) {
        this.shoulders = shoulders;
    }

    public void setBody(final int body) {
        this.body = body;
    }

    public void setChest(final int chest) {
        this.chest = chest;
    }

    public void setWaist(final int waist) {
        this.waist = waist;
    }

    public void setLegs(final int legs) {
        this.legs = legs;
    }

    public void setFeet(final int feet) {
        this.feet = feet;
    }

    public void setWrists(final int wrists) {
        this.wrists = wrists;
    }

    public void setHands(final int hands) {
        this.hands = hands;
    }

    public void setBack(final int back) {
        this.back = back;
    }

    public void setTabard(final int tabard) {
        this.tabard = tabard;
    }

    public int getEntry() {
        return entry;
    }

    public byte getRace() {
        return race;
    }

    public byte getGender() {
        return gender;
    }

    public byte getSkin() {
        return skin;
    }

    public byte getFace() {
        return face;
    }

    public byte getHair() {
        return hair;
    }

    public byte getHaircolor() {
        return hairColor;
    }

    public byte getFacialhair() {
        return facialHair;
    }

    public int getHead() {
        return head;
    }

    public int getShoulders() {
        return shoulders;
    }

    public int getBody() {
        return body;
    }

    public int getChest() {
        return chest;
    }

    public int getWaist() {
        return waist;
    }

    public int getLegs() {
        return legs;
    }

    public int getFeet() {
        return feet;
    }

    public int getWrists() {
        return wrists;
    }

    public int getHands() {
        return hands;
    }

    public int getBack() {
        return back;
    }

    public int getTabard() {
        return tabard;
    }
}
