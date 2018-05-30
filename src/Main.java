import com.codecool.termlib.Terminal;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Attribute;
import com.codecool.termlib.Color;

import java.util.Scanner;
import java.io.InputStream;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        //System.out.print("ok");
    }
    
    private byte xPosition = 0;
    private byte yPosition = 0;
    
    private static void changeXPosition(Direction direction) {
        if (direction == Direction.FORWARD) {
            yPosition += 1;
        }else if (direction == Direction.BACKWARD) {
            yPosition -= 1;
        }else if (direction == Direction.UP) {
            xPosition -= 1;
        }else {
            xPosition += 1;
        }
    }
    
}
