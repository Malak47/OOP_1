package api;

import observer.*;

public class ConcreteMember implements Member {
    private UndoableStringBuilder usb;

    public UndoableStringBuilder getUsb() {
        return usb;
    }

    public ConcreteMember() {
        this.usb = new UndoableStringBuilder();
    }

    public ConcreteMember(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}
