package mindustry.io;

import arc.Events;
import arc.files.Fi;
import arc.struct.StringMap;
import arc.util.io.FastDeflaterOutputStream;
import mindustry.game.EventType;

import java.io.DataOutputStream;
import java.io.OutputStream;

import static mindustry.Vars.bufferSize;
import static mindustry.io.SaveIO.header;
import static mindustry.io.SaveIO.versionArray;

public class SaveFileWrite {
    public static void write(Fi file, StringMap tags){
        write(new FastDeflaterOutputStream(file.write(false, bufferSize)), tags);
    }

    public static void write(Fi file){
        write(file, null);
    }

    public static void write(OutputStream os, StringMap tags){
        try(DataOutputStream stream = new DataOutputStream(os)){
            Events.fire(new EventType.SaveWriteEvent());
            SaveVersion ver = versionArray.peek();

            stream.write(header);
            stream.writeInt(ver.version);
            if(tags == null){
                ver.write(stream);
            }else{
                ver.write(stream, tags);
            }
        }catch(Throwable e){
            throw new RuntimeException(e);
        }
    }
}
