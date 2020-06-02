package application.presenter;

import application.model.Context;
import application.util.FileManagerUtil;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class TreeController {
    Context context;

    public TreeController(Context context) {
        this.context = context;
    }

    public TreeItem<String>[] TreeCreate(File dir) {
        TreeItem<String>[] A = null;
        File[] fl = dir.listFiles();
        int n = fl.length - FileManagerUtil.FilesHiddensCount(dir);
        A = new TreeItem[n];
        int pos = 0;
        for (File file : fl) {
            if (!file.isFile() && !file.isHidden() && file.isDirectory() && n == 0) {
                A[pos] = new TreeItem<>(file.getName(), new ImageView(new Image("image/folder10.png", 20, 20, true, true)));//ClassLoader.getSystemResourceAsStream
                pos++;
            } else if (!file.isFile() && !file.isHidden() && file.isDirectory() && n > 0) {
                A[pos] = new TreeItem<>(file.getName(), new ImageView(new Image("image/folder20.png", 20, 20, true, true)));
                try {
                    A[pos].getChildren().addAll(TreeCreate(file));
                    pos++;
                } catch (Exception x) {
                    System.out.println("Exception x in treecreate at " + file.getAbsolutePath() + " " + x.getMessage());
                }
            }
        }

        return A;
    }

    public String FindAbsolutePath(TreeItem<String> item, String s) {
        if ((item.getParent() == null) || (item.getParent().getValue().equals("This PC"))) {
            return s;
        } else {
            String dir = item.getParent().getValue();
            dir = dir + "\\" + s;
            return FindAbsolutePath(item.getParent(), dir);
        }
    }

    public void сreateTreeView(TreeView<String> treeview) {
        File[] sysroots = File.listRoots();
        TreeItem<String> ThisPc = new TreeItem<>("This PC", new ImageView(new Image("image/pc.png")));
        TreeItem<String>[] drives = new TreeItem[sysroots.length];
        for (int i = 0; i < sysroots.length; i++) {
            drives[i] = new TreeItem<>(sysroots[i].getAbsolutePath(), new ImageView(new Image("image/thumb_Hard_Drive.png")));
            try {
                drives[i].getChildren().addAll(TreeCreate(sysroots[i]));
            } catch (NullPointerException x) {
                System.out.println("Exeption x detected: " + x.fillInStackTrace() + drives[i].toString());
            } finally {
                ThisPc.getChildren().add(drives[i]);
            }
        }
        treeview.setRoot(ThisPc);
    }
}
