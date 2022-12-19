package api;

import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {
    private ConcreteMember member = new ConcreteMember();
    private UndoableStringBuilder usb = new UndoableStringBuilder();

    @Test
    void update() {
        this.usb.append("hello");
        this.member.update(this.usb);
        assertEquals("hello", this.member.getUsb().toString());
        this.usb.undo();
    }

    /*
     *  Test the update method with a null UndoableStringBuilder object.
     */
    @Test
    void testUpdateWithNullUndoableStringBuilder() {
        this.member.update(null);
        assertNull(this.member.getUsb());
    }

    /*
     *  Test the update method with an empty UndoableStringBuilder object.
     */
    @Test
    void testUpdateWithEmptyUndoableStringBuilder() {
        this.member.update(this.usb);
        assertEquals("", this.member.getUsb().toString());
    }

    /*
     *  Test the update method with multiple UndoableStringBuilder objects.
     */
    @Test
    void testUpdateMultipleTimes() {
        this.usb.append("hello ");
        this.member.update(this.usb);
        this.usb.append("world");
        this.member.update(this.usb);
        assertEquals("hello world", this.member.getUsb().toString());
        this.usb.undo();
        this.usb.undo();
    }
}

