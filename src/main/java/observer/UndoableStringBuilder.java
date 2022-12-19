package observer;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * A basic class that contains add, add at, remove, reverse, and undo methods for Char Arrays.
 *
 * @author : Malak Qaadan & Lara Abu hamad
 * @version : 1.1 - 10'th of November 2022
 */
public class UndoableStringBuilder {
    private char[] str;
    private Stack<char[]> cache;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";

    /**
     * Constructs a default UndoableStringBuilder.
     */
    public UndoableStringBuilder() {
        this.str = new char[0];
        this.cache = new Stack<char[]>();
    }

    /**
     * Constructs an UndoableStringBuilder that contains the same characters
     * as the specified {@code str}.
     *
     * @param str: the char array to copy.
     */
    public UndoableStringBuilder(char[] str) {
        if (str != null) {
            this.str = new char[str.length];
            System.arraycopy(str, 0, this.str, 0, str.length);
        } else
            this.str = new char[0];
        this.cache = new Stack<char[]>();
    }


    /**
     * Constructs an UndoableStringBuilder that contains the same characters
     * as the specified {@code str} with the same {@code cache}.
     *
     * @param str:   the char array to copy.
     * @param cache: contains all the changes of the char array.
     */
    public UndoableStringBuilder(char[] str, Stack<char[]> cache) {
        if (str != null) {
            this.str = new char[str.length];
            System.arraycopy(str, 0, this.str, 0, str.length);
        } else
            this.str = new char[0];
        this.cache = new Stack<char[]>();
        if (!cache.isEmpty())
            this.cache.addAll(cache);
    }

    /**
     * Computes the string length.
     *
     * @return This str length.
     */
    public int getLength() {
        return this.str.length;
    }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param str: String that will be added.
     * @return This object.
     */
    public UndoableStringBuilder append(String str) {
        if (this.str.length == 0) {
            this.str = str.toCharArray();
            return this;
        }
        this.cache.add(this.str);
        this.str = mergeArrays(this.str, str.toCharArray(), this.str.length);
        return this;
    }

    /**
     * Casts this str into String.
     *
     * @return Char array as String.
     */
    @Override
    public String toString() {
        return new String(this.str);
    }

    /**
     * Removes the characters in a substring of this sequence.
     * The substring begins at the specified {@code start} and extends to
     * the character at index {@code end - 1} or to the end of the
     * sequence if no such character exists.If
     * {@code start} is equal to {@code end}, no changes are made.
     *
     * @param start: The beginning index, inclusive.
     * @param end:   The ending index, exclusive.
     * @return This object.
     * @throws StringIndexOutOfBoundsException if {@code start}
     *                                         is negative, greater than {@code length()},
     *                                         greater than {@code end}.
     */
    public UndoableStringBuilder delete(int start, int end) {
        if (start < 0 || end < start || start > this.str.length) {
            throw new StringIndexOutOfBoundsException(
                    "start " + start + ", end " + end + ", length " + this.str.length);
        }
        if (start == end) {
            return this;
        }
        this.cache.add(this.str);

        if (end > this.str.length) {
            char[] temp = new char[start];
            System.arraycopy(this.str, 0, temp, 0, start);
            this.str = temp;
            return this;
        }
        char[] temp = new char[this.str.length - (end - start)];
        System.arraycopy(this.str, 0, temp, 0, start);
        int m = 0;
        for (int j = end; j < this.str.length; j++) {
            temp[start + m] = this.str[j];
            m++;
        }
        this.str = temp;
        return this;
    }

    /**
     * Inserts the {@code String} into this character sequence.
     *
     * @param offset: The beginning index, inclusive.
     * @param str:    String that will be inserted.
     * @return This object.
     * @throws StringIndexOutOfBoundsException if {@code offset}
     *                                         is negative, greater than {@code length()}.
     */
    public UndoableStringBuilder insert(int offset, String str) {
        if (offset > this.str.length || offset < 0) {
            throw new StringIndexOutOfBoundsException(
                    "offset " + offset + ", length " + this.str.length);
        }
        this.cache.add(this.str);
        this.str = mergeArrays(this.str, str.toCharArray(), offset);
        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence
     * with characters in the specified {@code String}. The substring
     * begins at the specified start and extends to the character
     * at index {@code end - 1} or to the end of the
     * sequence if no such character exists. First the
     * characters in the substring are removed and then the specified
     * {@code String} is inserted at {@code start}. (This
     * sequence will be lengthened to accommodate the
     * specified String if necessary).
     *
     * @param start: The beginning index, inclusive.
     * @param end:   The ending index, exclusive.
     * @param str:   String that will replace previous contents.
     * @return This object.
     * @throws StringIndexOutOfBoundsException if {@code start}
     *                                         is negative, greater than {@code length()}, or
     *                                         greater than {@code end}.
     */
    public UndoableStringBuilder replace(int start, int end, String str) throws StringIndexOutOfBoundsException {
        if (start < 0 || end < start || start > this.str.length) {
            throw new IndexOutOfBoundsException(
                    "start " + start + ", end " + end + ", length " + this.str.length);
        }
        this.cache.add(this.str);
        if (end > this.str.length) {
            char[] temp = new char[start + str.length()];
            System.arraycopy(this.str, 0, temp, 0, start);
            for (int i = 0; i < str.length(); i++) {
                temp[start + i] = str.charAt(i);
            }
            this.str = temp;
            return this;
        }
        char[] temp = new char[this.str.length - (end - start) + str.length()];

        System.arraycopy(this.str, 0, temp, 0, start);
        for (int i = 0; i < str.length(); i++) {
            temp[start + i] = str.charAt(i);
        }
        int m = end;
        for (int i = start + str.length(); i < temp.length; i++) {
            temp[i] = this.str[m];
            m++;
        }
        this.str = temp;
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the sequence.
     *
     * @return This object.
     */
    public UndoableStringBuilder reverse() {
        this.cache.add(this.str);
        char[] temp = new char[this.str.length];
        int k = 0;
        for (int i = this.str.length - 1; i >= 0; i--) {
            temp[k] = this.str[i];
            k++;
        }
        this.str = temp;
        return this;
    }

    /**
     * Undo the last change that has been done to the string.
     *
     * @throws EmptyStackException error is handled, Error will pop up to the screen if the stack is empty.
     */
    public void undo() throws EmptyStackException {
        try {
            this.str = this.cache.pop();
        } catch (EmptyStackException e) {
            this.str = new char[0];
            System.out.println(RED_BOLD + "Can't Undo: Stack Is Empty" + ANSI_RESET);
        }
    }

    /**
     * Merges two char arrays {@code str1}, {@code str2} from a specific index {@code start} in the first array.
     *
     * @param str1:  First char array.
     * @param str2:  Second char array.
     * @param start: The beginning index, inclusive.
     * @return The merged char array.
     */
    private char[] mergeArrays(char[] str1, char[] str2, int start) {
        char[] temp = new char[str1.length + str2.length];
        if (start == str1.length) {
            System.arraycopy(str1, 0, temp, 0, str.length);
            System.arraycopy(str2, 0, temp, start, str2.length);
            return temp;
        }
        int k = 0;
        for (int i = 0; i < str1.length; i++) {
            if (i == start) {
                for (char c : str2) {
                    temp[k] = c;
                    k++;
                }
                for (int j = i; j < str1.length; j++) {
                    temp[k] = str1[j];
                    k++;
                }
                return temp;
            } else {
                temp[k] = str1[i];
                k++;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("to be or not to be");
        System.out.println(usb);
        usb.replace(3, 5, "eat");
        System.out.println(usb);
        usb.replace(17, 19, "eat");
        System.out.println(usb);
        usb.reverse();
        System.out.println(usb);
        System.out.println();
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
    }
}