package photohost.project.modul.file;

import java.io.File;

/**
 * Created by root on 24.5.16.
 */
public class LocalFile {
    public static void DeleteFileFromLocal(String source){
        new File(source).delete();
    }
}
