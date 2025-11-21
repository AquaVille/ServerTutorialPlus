package nl.martenm.servertutorialplus.helpers;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple colour class for particle colours.
 * @author MartenM
 * @since 27-11-2017.
 */

public class Color{
    @Getter
    private int red;
    @Setter
    @Getter
    private int green;
    @Setter
    @Getter
    private int blue;

    public Color(int red, int green, int blue){
        if(red == 0){
            this.red = 1;
        } else this.red = red;

        this.green = green;
        this.blue = blue;
    }

    public void setRed(int red) {
        if(red == 0){
            this.red = 1;
        } else this.red = red;
    }

    public void set(int red, int green, int blue){
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    @Override
    public String toString() {
        return red + " " + green + " " + blue;
    }

    public static Color fromString(String input){
        try{
            String[] data = input.split(" ");
            return new Color(Integer.parseInt(data[0]), Integer.parseInt(data[1]),Integer.parseInt(data[2]));
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("[!!] Invalid colour from string!");
            return new Color(255, 0, 0);
        }
    }

}
