package application.presenter;

import application.model.Context;
import application.view.CreateFileView;
import application.view.FileTreeVIew;
import application.view.ToolBarView;
import application.view.workspace.FileListView;
import application.view.workspace.FileTableView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class Controller {
    private final FileTreeVIew treeview;
    private final FileTableView tableView;
    private final CreateFileView createFileView;
    private final FileListView fileListView;
    private final TreeController treeController;
    private final TableController tableController;
    private final ListViewController listController;
    private Context context;
    private WorkSpace currentWorkSpaceView;
    private Button btn;
    private Pane secPane;
    private Label label = new Label();


    //передавать ссылку на фабрику
    public Controller(FileTreeVIew treeView, FileTableView tableView, ToolBarView toolBarView, CreateFileView createFileView,
                      FileListView fileListView) {
        this.context = new Context();
        this.createFileView = createFileView;
        this.treeview = treeView;
        this.tableView = tableView;
        this.fileListView = fileListView;
        this.treeController = new TreeController(context);
        this.tableController = new TableController(context, tableView);
        this.listController = new ListViewController(context, fileListView);
        this.currentWorkSpaceView = tableController;

        createFileView.setOnCreateButton(this::handleCreatButtonClicked);
        treeView.getTreeView().setOnMouseClicked(this::handleMouseClicked);

        context.setCurrDirFile(new File("./"));
        context.setCurrDirStr(context.getCurrDirFile().getAbsolutePath());
        toolBarView.setCurrentDir(context.getCurrDirStr());
        treeController.сreateTreeView(treeView.getTreeView());
    }


    //добавить интерфейс файлпринет с методом шоу принимающий лист файлов
    private void handleMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            try {
                TreeItem<String> item = treeview.getTreeView().getSelectionModel().getSelectedItem();
                context.setCurrDirName(item.getValue());
                System.out.println("Selected Text : " + item.getValue());
                context.setCurrDirFile(new File(treeController.FindAbsolutePath(item, item.getValue())));
                context.setCurrDirStr(context.getCurrDirFile().getAbsolutePath());
                label.setText(context.getCurrDirStr());
                currentWorkSpaceView.show();
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }
        }
    }

    private void refreshCurrentDir() {
        TreeItem<String> item = treeview.getTreeView().getSelectionModel().getSelectedItem();
        context.setCurrDirName(item.getValue());
        System.out.println("Selected Text : " + item.getValue());
        context.setCurrDirFile(new File(treeController.FindAbsolutePath(item, item.getValue())));
        context.setCurrDirStr(context.getCurrDirFile().getAbsolutePath());
        label.setText(context.getCurrDirStr());
        currentWorkSpaceView.show();
    }

    public void filter(String extension) {
        TreeItem<String> item = treeview.getTreeView().getSelectionModel().getSelectedItem();
        context.setCurrDirName(item.getValue());
        System.out.println("Selected Text : " + item.getValue());
        context.setCurrDirFile(new File(treeController.FindAbsolutePath(item, item.getValue())));
        context.setCurrDirStr(context.getCurrDirFile().getAbsolutePath());
        label.setText(context.getCurrDirStr());
        System.out.println("extension: " + extension);
        currentWorkSpaceView.show(extension);
    }


    //можно принимать ссылки на вьюхи и взависимости он них выбирать контроллер
    public void setViewMode(ViewMode mode) {
        switch (mode) {
            case Table: {
                this.currentWorkSpaceView = tableController;
                refreshCurrentDir();
                break;
            }
            case List: {
                this.currentWorkSpaceView = listController;
                refreshCurrentDir();
                break;
            }
        }
    }

    ;

    private void handleCreatButtonClicked(ActionEvent actionEvent) {
        if (!createFileView.getFileName().isEmpty() && createFileView.getFileName().contains(".")) {
            File file = new File(context.getCurrDirStr() + "/" + createFileView.getFileName());
            try {
                System.out.println(file.createNewFile());
                refreshCurrentDir();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //тип смена вьюшек
}
