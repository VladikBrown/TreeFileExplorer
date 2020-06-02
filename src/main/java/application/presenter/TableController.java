package application.presenter;

import application.model.Context;
import application.model.FileInfo;
import application.util.FileManagerUtil;
import application.view.workspace.FileTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.File;

public class TableController implements WorkSpace {
    Context context;
    FileTableView fileTableView;

    TableController(Context context, FileTableView fileTableView) {
        this.context = context;
        this.fileTableView = fileTableView;
        if (context.getCurrDirFile() == null) {
            context.setCurrDirFile(new File("./"));
            context.setCurrDirStr(context.getCurrDirFile().getAbsolutePath());
        }
    }

    private void createTableView(TableView<FileInfo> tableView) {
        ObservableList<FileInfo> list;
        File[] fl = FileManagerUtil.getListRoots(context);
        list = FXCollections.observableArrayList(FileManagerUtil.toFileInfoList(fl));
        tableView.setItems(list);
    }

    public void show() {
        fileTableView.getView().getItems().clear();
        createTableView(fileTableView.getView());
    }

    @Override
    public void show(String extension) {
        fileTableView.getView().getItems().clear();
        ObservableList<FileInfo> list;
        File[] fl = FileManagerUtil.getListRoots(context);
        list = FXCollections.observableArrayList(FileManagerUtil.toFileInfoList(fl, extension));
        fileTableView.getView().setItems(list);
    }
}
