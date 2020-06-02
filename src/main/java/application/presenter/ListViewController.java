package application.presenter;

import application.model.Context;
import application.model.FileInfo;
import application.util.FileManagerUtil;
import application.view.workspace.FileListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.io.File;

public class ListViewController implements WorkSpace {
    Context context;
    FileListView fileListView;

    public ListViewController(Context context, FileListView fileListView) {
        this.context = context;
        this.fileListView = fileListView;
    }

    @Override
    public void show() {
        fileListView.getView().getChildren().clear();
        ObservableList<FileInfo> list;
        File[] fl = FileManagerUtil.getListRoots(context);
        list = FXCollections.observableArrayList(FileManagerUtil.toFileInfoList(fl));
        for (var x : list) {
            fileListView.getView().getChildren().add(new Text(x.getName() + x.getExtension()));
        }
    }

    @Override
    public void show(String extension) {
        fileListView.getView().getChildren().clear();
        ObservableList<FileInfo> list;
        File[] fl = FileManagerUtil.getListRoots(context);
        list = FXCollections.observableArrayList(FileManagerUtil.toFileInfoList(fl, extension));
        for (var x : list) {
            fileListView.getView().getChildren().add(new Text(x.getName() + x.getExtension()));
        }
    }
}
