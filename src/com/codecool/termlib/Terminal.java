package com.codecool.termlib;

public class Terminal {
    /**
     * The beginning of control sequences.
     */
    private static final String CONTROL_CODE = "\033[";
    /**
     * Command for whole screen clearing.
     *
     * Might be partitioned if needed.
     */
    private static final String CLEAR = "2J";
    /**
     * Command for moving the cursor.
     */
    private static final String MOVE = "H";
    /**
     * Command for printing style settings.
     *
     * Handles foreground color, background color, and any other
     * styles, for example color brightness, or underlines.
     */
    private static final String STYLE = "m";

    /**
     * Reset printing rules in effect to terminal defaults.
     *
     * Reset the color, background color, and any other style
     * (i.e.: underlined, dim, bright) to the terminal defaults.
     */
    public void resetStyle() {
        command("\033[0m");
    }

    /**
     * Clear the whole screen.
     *
     * Might reset cursor position.
     */
    public void clearScreen() {
        command("\033[2J");
    }

    /**
     * Move cursor to the given position.
     *
     * Positions are counted from one.  Cursor position 1,1 is at
     * the top left corner of the screen.
     *
     * @param x Column number.
     * @param y Row number.
     */
    public void moveTo(Integer x, Integer y) {
        String cursorPosition = "\033[" + String.valueOf(x) + ";" + String.valueOf(y)+ "H";
        command(cursorPosition);
    }

    /**
     * Set the foreground printing color.
     *
     * Already printed text is not affected.
     *
     * @param color The color to set.
     */
    public void setColor(Color color) {
        int colorCode;
        if (color == Color.BLACK) {colorCode = 30;}
        else if (color == Color.RED) {colorCode = 31;}
        else if (color == Color.GREEN) {colorCode = 32;}
        else if (color == Color.YELLOW) {colorCode = 33;}
        else if (color == Color.BLUE) {colorCode = 34;}
        else if (color == Color.MAGENTA) {colorCode = 35;}
        else if (color == Color.CYAN) {colorCode = 36;}
        else {colorCode = 37;}

        String newColor = "\033[" + String.valueOf(colorCode) + "m";
        command(newColor);
    }

    /**
     * Set the background printing color.
     *
     * Already printed text is not affected.
     *
     * @param color The background color to set.
     */
    public void setBgColor(Color color) {
        int colorCode;
        if (color == Color.BLACK) {colorCode = 40;}
        else if (color == Color.RED) {colorCode = 41;}
        else if (color == Color.GREEN) {colorCode = 42;}
        else if (color == Color.YELLOW) {colorCode = 43;}
        else if (color == Color.BLUE) {colorCode = 44;}
        else if (color == Color.MAGENTA) {colorCode = 45;}
        else if (color == Color.CYAN) {colorCode = 46;}
        else {colorCode = 47;}

        String newColor = "\033[" + String.valueOf(colorCode) + "m";
        command(newColor);
    }

    /**
     * Make printed text underlined.
     *
     * On some terminals this might produce slanted text instead of
     * underlined.  Cannot be turned off without turning off colors as
     * well.
     */
    public void setUnderline() {
    }

    /**
     * Move the cursor relatively.
     *
     * Move the cursor amount from its current position in the given
     * direction.
     *
     * @param direction Step the cursor in this direction.
     * @param amount Step the cursor this many times.
     */
    public void moveCursor(Direction direction, Integer amount) {
        String moveCursor;
            if (direction == Direction.FORWARD) {
                moveCursor = "\033[" + String.valueOf(amount) + "C";
            }else if (direction == Direction.BACKWARD) {
                moveCursor = "\033[" + String.valueOf(amount) + "D";
            }else if (direction == Direction.UP) {
                moveCursor = "\033[" + String.valueOf(amount) + "A";
            }else {
                moveCursor = "\033[" + String.valueOf(amount) + "B";
            }
        command(moveCursor);
    }

    /**
     * Set the character diplayed under the current cursor position.
     *
     * The actual cursor position after calling this method is the
     * same as beforehand.  This method is useful for drawing shapes
     * (for example frame borders) with cursor movement.
     *
     * @param c the literal character to set for the current cursor
     * position.
     */
    public void setChar(char c) {
        command(String.valueOf(c));
        command("\033[D");
    }

    /**
     * Helper function for sending commands to the terminal.
     *
     * The common parts of different commands shall be assembled here.
     * The actual printing shall be handled from this command.
     *
     * @param commandString The unique part of a command sequence.
     */
    private void command(String commandString) {
	    System.out.print(commandString);
    }
}
