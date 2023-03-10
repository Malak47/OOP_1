package api;

import observer.*;

import java.util.Random;

public class ConcreteMember implements Member {
    private UndoableStringBuilder usb;
    private final String ID;

    /**
     * Constructs a new ConcreteMember object with an empty UndoableStringBuilder.
     */
    public ConcreteMember() {
        this.usb = new UndoableStringBuilder();
        this.ID = "member" + (new Random().nextInt(1000 - 10) + 10);    //random member name.
    }

    /**
     * Constructs a new ConcreteMember object with the specified UndoableStringBuilder.
     *
     * @param usb: The UndoableStringBuilder to be used in this ConcreteMember object.
     */
    public ConcreteMember(UndoableStringBuilder usb) {
        this.usb = usb;
        this.ID = "member" + (new Random().nextInt(1000 - 10) + 10);    //random member name.
    }

    /**
     * Constructs a new ConcreteMember object with the specified String ID.
     * @param ID: String that tells the name/ID of the member.
     */
    public ConcreteMember(String ID) {
        this.usb = new UndoableStringBuilder();
        this.ID = ID;
    }

    /**
     * Constructs a new ConcreteMember object with the specified UndoableStringBuilder String ID.
     * @param usb: The UndoableStringBuilder to be used in this ConcreteMember object.
     * @param ID: String that tells the name/ID of the member.
     */
    public ConcreteMember(UndoableStringBuilder usb, String ID) {
        this.usb = usb;
        this.ID = ID;
    }

    /**
     * @return: The ID of the specified member.
     */
    public String getID() {
        return this.ID;
    }

    /**
     * @return: The UndoableStringBuilder that is used in this ConcreteMember.
     */
    public UndoableStringBuilder getUsb() {
        return usb;
    }

    /**
     * Updates the state of this Member object with the state of the UndoableStringBuilder object.
     *
     * @param usb: The UndoableStringBuilder object whose state is used to update this Member object.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * Returns the current state of this Member object as a String.
     *
     * @return the current state of this Member object.
     */
    @Override
    public String toString() {
        return "ConcreteMember{" +
                "usb::" + usb.toString() +
                ", ID::" + ID +
                '}';
    }

}
