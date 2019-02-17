package com.mystudio.dungeon_adventure.helpers;

import com.mystudio.dungeon_adventure.model.Player.PlayerBasicClass;

import javax.annotation.processing.FilerException;
import java.io.*;

public class SaveState {
    public static final String PLAYER_SAVE_STATE = "player.json";

    public static int saveObject(Object serObj, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            objectOutputStream.writeObject(serObj);
            objectOutputStream.close();
            return 0;

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return 1;
    }

    public static Object readObject(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);

            Object serObj = objectInputStream.readObject();
            objectInputStream.close();

            return serObj;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean hasPreviousSaveState(String file) {
        File f = new File(file);
        return f.exists();
    }
}
