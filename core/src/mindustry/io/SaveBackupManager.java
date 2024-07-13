package mindustry.io;

import arc.files.Fi;

public class SaveBackupManager {
    public static Fi backupFileFor(Fi file){
        return file.sibling(file.name() + "-backup." + file.extension());
    }
}
