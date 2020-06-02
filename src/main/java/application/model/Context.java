package application.model;

import java.io.File;

public class Context {

    private File currDirFile;
    private String currDirStr;
    private String currDirName;

    public Context() {
    }

    public Context(File currDirFile, String currDirStr, String currDirName) {
        this.currDirFile = currDirFile;
        this.currDirStr = currDirStr;
        this.currDirName = currDirName;
    }

    public File getCurrDirFile() {
        return currDirFile;
    }

    public void setCurrDirFile(File currDirFile) {
        this.currDirFile = currDirFile;
    }

    public String getCurrDirStr() {
        return currDirStr;
    }

    public void setCurrDirStr(String currDirStr) {
        this.currDirStr = currDirStr;
    }

    public String getCurrDirName() {
        return currDirName;
    }

    public void setCurrDirName(String currDirName) {
        this.currDirName = currDirName;
    }

}
