package api;

import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {

    private GroupAdmin groupAdmin = new GroupAdmin();
    private UndoableStringBuilder usb = new UndoableStringBuilder();
    private ConcreteMember member1 = new ConcreteMember();
    private ConcreteMember member2 = new ConcreteMember();

    @Test
    public void register() {
        this.groupAdmin.register(this.member1);
        this.groupAdmin.register(this.member2);
        assertEquals(2, this.groupAdmin.getMembers().size());
        assertEquals(this.member1, this.groupAdmin.getMembers().get(0));
        assertEquals(this.member2, this.groupAdmin.getMembers().get(1));

        this.groupAdmin.unregister(this.member1);
        this.groupAdmin.unregister(this.member2);
    }

    @Test
    public void unregister() {
        this.groupAdmin.register(this.member1);
        this.groupAdmin.register(this.member2);
        this.groupAdmin.unregister(this.member1);
        assertEquals(1, this.groupAdmin.getMembers().size());
        assertEquals(this.member2, this.groupAdmin.getMembers().get(0));
    }

    @Test
    public void insert() {
        this.usb.append("hello");
        this.groupAdmin.append(this.usb.toString());
        this.groupAdmin.insert(2, " world");
        assertEquals("he worldllo", this.groupAdmin.getSequence().toString());
        this.groupAdmin.register(this.member1);
        this.groupAdmin.register(this.member2);

        this.groupAdmin.undo();
        this.groupAdmin.undo();
        this.usb.undo();
        this.groupAdmin.unregister(this.member1);
        this.groupAdmin.unregister(this.member2);

    }

    @Test
    public void append() {
        this.usb.append("hello");
        this.groupAdmin.append(this.usb.toString());
        this.groupAdmin.append(" world");
        assertEquals("hello world", this.groupAdmin.getSequence().toString());

        this.groupAdmin.undo();
        this.groupAdmin.undo();
        this.usb.undo();
    }

    @Test
    public void delete() {
        this.usb.append("hello world");
        this.groupAdmin.append(this.usb.toString());
        this.groupAdmin.delete(5, 10);
        assertEquals("hellod", this.groupAdmin.getSequence().toString());

        this.groupAdmin.undo();
        this.groupAdmin.undo();
        this.usb.undo();
    }

    @Test
    public void undo() {
        this.usb.append("hello");
        groupAdmin.append(this.usb.toString());
        this.groupAdmin.append(" world");
        this.groupAdmin.undo();
        assertEquals("hello", this.groupAdmin.getSequence().toString());

        this.groupAdmin.undo();
        this.usb.undo();
    }

    @Test
    public void update() {
        this.groupAdmin.register(this.member1);
        this.groupAdmin.register(this.member2);
        this.usb.append("hello");
        this.groupAdmin.append(this.usb.toString());
        this.groupAdmin.append(" world");
        this.groupAdmin.update();
        assertEquals("hello world", this.member1.getUsb().toString());
        assertEquals("hello world", this.member2.getUsb().toString());

        this.groupAdmin.undo();
        this.groupAdmin.undo();
        this.usb.undo();
        this.groupAdmin.unregister(this.member1);
        this.groupAdmin.unregister(this.member2);
    }
}