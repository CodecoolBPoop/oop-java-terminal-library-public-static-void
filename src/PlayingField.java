import com.codecool.termlib.Terminal;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Attribute;
import com.codecool.termlib.Color;

import java.util.Scanner;
import java.io.InputStream;
import java.io.*;

public class PlayingField {

    Terminal terminal = new Terminal();
    
    private String[][] playingField = new String[10][10];
    for (int i = 0; i < 10; i++) {
        Arrays.fill(playingField[i], " ");
    }
    
    
    public void setFieldsValue(int x, int y, char value) {
        playingField[x][y] = value;
    }

    
    

}
