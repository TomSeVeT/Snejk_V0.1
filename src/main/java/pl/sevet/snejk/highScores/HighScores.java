package pl.sevet.snejk.highScores;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScores {
    private static List<Player>  highScores = new ArrayList<Player>();

    public static List<Player> getHighScores() {
        return highScores;
    }

    public static void loadHighScores(String path){
        Player player = null;
        highScores.clear();
        File file = new File(path);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            FileInputStream fs = new FileInputStream(path);
            ObjectInputStream is = new ObjectInputStream(fs);
            for(int i=0; ;i++){
                try{
                    player = (Player)is.readObject();
                    highScores.add(player);
                }catch(EOFException e){
                    System.out.println("Załadowano " + i + " rekord(y/ów)!");
                    break;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveHighScores(String path){
        try{
            FileOutputStream fs = new FileOutputStream(path);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            for (Player player : highScores){
                os.writeObject(player);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
