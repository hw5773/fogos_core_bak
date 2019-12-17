package FogOSStore;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentStore {

    private static final String TAG = "FogOSStore";
    private static final String targetDir = "/Download";
    private File[] files;
    private String dirPath;

    public ContentStore(String path){
        Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize FogOSStore");
        dirPath = path;
        files = getFileList(dirPath);
        Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize FogOSStore");
    }

    private File[] getFileList(String dirPath){
        Logger.getLogger(TAG).log(Level.INFO, "Start: getFileList()");
        File dir = new File(dirPath + targetDir);

        File[] fileList = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("mp4");
            }
        });

        for(File file : fileList){
            Logger.getLogger(TAG).log(Level.INFO, "Result: getFileList() " + file.getName());
        }

        Logger.getLogger(TAG).log(Level.INFO, "Finish: getFileList()");
        return fileList;
    }

    public File[] getFileList(){
        return files;
    }
}
