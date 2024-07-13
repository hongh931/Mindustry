package mindustry.io;

import arc.files.Fi;
import arc.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

import static mindustry.Vars.bufferSize;
import static mindustry.io.SaveIO.versions;

public class SaveBackupManager {
    public static Fi backupFileFor(Fi file){
        return file.sibling(file.name() + "-backup." + file.extension());
    }

    public static boolean isSaveValid(Fi file){
        return isSaveFileValid(file) || isSaveFileValid(SaveBackupManager.backupFileFor(file));
    }

    private static boolean isSaveFileValid(Fi file){
        try(DataInputStream stream = new DataInputStream(new InflaterInputStream(file.read(bufferSize)))){
            SaveIO.getMeta(stream);
            return true;
        }catch(Throwable e){
            return false;
        }
    }
}
