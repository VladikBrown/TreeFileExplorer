package application.util;

import application.model.Context;
import application.model.FileInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileManagerUtil {

    public static File[] getListRoots(Context context) {
        File[] fl;
        if (context.getCurrDirFile() == null) {
            context.setCurrDirFile(new File("./"));
        }
        if (context.getCurrDirName().equals("This PC")) {
            return fl = File.listRoots();
        } else {
            return fl = context.getCurrDirFile().listFiles();
        }
    }

    public static List<FileInfo> toFileInfoList(File[] files, String extension) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        ObservableList<FileInfo> list;
        FileInfo[] st = new FileInfo[files.length];
        for (int i = 0; i < files.length; i++) {
            //TODO вынести метод в parseFileName
            StringBuilder s1 = new StringBuilder();
            String s2 = null;
            String s3 = null;
            String s4 = null;
            try {
                if (FileManagerUtil.IsDrive(files[i])) {
                    s1.append(files[i].getAbsolutePath());
                } else {
                    s1.append(files[i].getName());
                    s2 = s1.substring(s1.indexOf("."));
                    s1.delete(s1.indexOf("."), s1.length());
                }
                s3 = FileManagerUtil.calculateSize(files[i]);
                s4 = sdf.format(files[i].lastModified());
            } catch (Exception x) {
                System.out.println("Exception detected in tableView strings: " + x.getMessage());
            }
            if (s2.equals(extension)) {
                st[i] = new FileInfo(s1.toString(), s2, s3, s4);
            }
        }

        return list = FXCollections.observableArrayList(st);
    }

    public static List<FileInfo> toFileInfoList(File[] files) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        ObservableList<FileInfo> list;
        FileInfo[] st = new FileInfo[files.length];
        for (int i = 0; i < files.length; i++) {
            StringBuilder s1 = new StringBuilder();
            String s2 = null;
            String s3 = null;
            String s4 = null;
            try {
                if (FileManagerUtil.IsDrive(files[i])) {
                    s1.append(files[i].getAbsolutePath());
                } else {
                    s1.append(files[i].getName());
                    s2 = s1.substring(s1.indexOf("."));
                    s1.delete(s1.indexOf("."), s1.length());
                }
                s3 = FileManagerUtil.calculateSize(files[i]);
                s4 = sdf.format(files[i].lastModified());
            } catch (Exception x) {
                System.out.println("Exception detected in tableView strings: " + x.getMessage());
            }
            st[i] = new FileInfo(s1.toString(), s2, s3, s4);
        }

        return list = FXCollections.observableArrayList(st);
    }

    public static String calculateSize(File f) {
        String s;
        long sizeInByte = 0;
        Path path;
        if (IsDrive(f)) {
            return Long.toString(f.getTotalSpace() / (1024 * 1024 * 1024)) + "GB";
        }

        path = Paths.get(f.toURI());
        //sizeInByte = f.getTotalSpace(); // terrible idea cz sob subfolder e 199GB, 99GB esob dekhay >_<
        try {
            sizeInByte = Files.size(path);//at least works ^_^
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sizeInByte < (1024)) {
            s = Long.toString(sizeInByte) + "B";
            return s;
        } else if (sizeInByte >= (1024) && sizeInByte < (1024 * 1024)) {
            long sizeInKb = sizeInByte / 1024;
            s = Long.toString(sizeInKb) + "KB";
            return s;
        } else if (sizeInByte >= (1024 * 1024) && sizeInByte < (1024 * 1024 * 1024)) {
            long sizeInMb = sizeInByte / (1024 * 1024);
            s = Long.toString(sizeInMb) + "MB";
            return s;
        } else if (sizeInByte >= (1024 * 1024 * 1024)) {
            long sizeInGb = sizeInByte / (1024 * 1024 * 1024);
            s = Long.toString(sizeInGb) + "GB";
            return s;
        }

        return null;
    }

    public static boolean IsDrive(File f) {
        File[] sysroots = File.listRoots();
        for (int i = 0; i < sysroots.length; i++) {
            if (f.equals(sysroots[i])) return true;
        }
        return false;
    }

    public static int FilesHiddensCount(File dir) {
        int count = 0;
        File[] fl = dir.listFiles();
        //System.out.println(fl.length);
        for (int i = 0; i < fl.length; i++) {
            try {
                if (fl[i].isHidden() || fl[i].isFile()) count++;
            } catch (Exception x) {
                System.out.println("Exception at prototype1, fileexplorer CountDir: " + x.getMessage());
            }
        }
        return count;
    }
}
