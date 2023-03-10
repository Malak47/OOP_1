package api;

import observer.Member;
import observer.Sender;
import observer.UndoableStringBuilder;

import java.util.ArrayList;
import java.util.Random;

public class GroupAdmin implements Sender {
    private UndoableStringBuilder sequence;
    private ArrayList<Member> members;

    private final String ID;

    /**
     * Constructs a default GroupAdmin.
     */
    public GroupAdmin() {
        this.sequence = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
        this.ID = "GroupAdmin" + (new Random().nextInt(1000 - 10) + 10);    //random GroupAdmin name.

    }

    /**
     * Constructs a GroupAdmin with specified ID.
     *
     * @param ID: String that tells the name/ID of the GroupAdmin.
     */
    public GroupAdmin(String ID) {
        this.sequence = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
        this.ID = ID;
    }

    /**
     * Constructs a GroupAdmin that contains the same sequence and list of members.
     *
     * @param sequence: It represents the UndoableStringBuilder sequence.
     * @param members:  The members that we want to work with.
     */
    public GroupAdmin(UndoableStringBuilder sequence, ArrayList<Member> members) {
        this.sequence = sequence;
        this.members = members;
        this.ID = "GroupAdmin" + (new Random().nextInt(1000 - 10) + 10);    //random GroupAdmin name.

    }

    /**
     * Registers a new Member object to the list of registered objects.
     *
     * @param obj: the Member object to be registered.
     */
    @Override
    public void register(Member obj) {
        if (!getMembers().contains(obj)) {
            this.members.add(obj);
            System.out.println("Member: " + ((ConcreteMember) (this.members.get(this.members.indexOf(obj)))).getID() + " registered in GroupAdmin: " + this.ID + ".");
        } else {
            System.out.println("Member: " + ((ConcreteMember) (this.members.get(this.members.indexOf(obj)))).getID() + " is already registered in GroupAdmin: " + this.ID + ".");
        }
    }

    /**
     * Unregisters a Member object from the list of registered objects.
     *
     * @param obj: the Member object to be unregistered.
     */
    @Override
    public void unregister(Member obj) {
        if (getMembers().contains(obj)) {
            System.out.println("Member: " + ((ConcreteMember) (this.members.get(this.members.indexOf(obj)))).getID() + " unregistered from GroupAdmin: " + this.ID + ".");
            this.members.remove(obj);
        } else {
            System.out.println("The given Member isn't registered in GroupAdmin: " + this.ID + ".");
        }
    }

    /**
     * Inserts the specified string into the UndoableStringBuilder object at the specified position.
     *
     * @param offset: the position at which the string will be inserted.
     * @param obj:    the string to be inserted.
     */
    @Override
    public void insert(int offset, String obj) {
        this.sequence.insert(offset, obj);
        update();
    }

    /**
     * Appends the specified string to the UndoableStringBuilder object.
     *
     * @param obj: the string to be appended.
     */
    @Override
    public void append(String obj) {
        this.sequence.append(obj);
        update();
    }

    /**
     * Removes the specified characters from the UndoableStringBuilder object.
     *
     * @param start: the starting position of the characters to be removed.
     * @param end:   the ending position of the characters to be removed.
     */
    @Override
    public void delete(int start, int end) {
        this.sequence.delete(start, end);
        update();
    }

    /**
     * Undoes the last change made to the UndoableStringBuilder object.
     */
    @Override
    public void undo() {
        this.sequence.undo();
        update();
    }

    /**
     * Calls the update method on all the registered Member objects, passing the UndoableStringBuilder object as an argument.
     */
    public void update() {
        for (Member member : members)
            member.update(this.sequence);
    }

    /**
     * @return: members list.
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * @return: sequence.
     */
    public UndoableStringBuilder getSequence() {
        return sequence;
    }

    @Override
    public String toString() {
        UndoableStringBuilder s1 = new UndoableStringBuilder();
        s1.append("GroupAdmin{" + "ID::" + ID + ", sequence::" + sequence + ", members::");
        for (Member member : members)
            s1.append(((ConcreteMember) member).getID()).append(",");
        s1 = s1.delete(s1.getLength()-1, s1.getLength());
        return s1 + "}";
    }
}
