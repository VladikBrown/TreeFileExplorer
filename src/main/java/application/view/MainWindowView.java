package application.view;

import application.presenter.Controller;
import application.presenter.ViewMode;
import application.view.workspace.FileListView;
import application.view.workspace.FileTableView;
import application.view.workspace.FileView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainWindowView {
    private final FileTableView fileTableView;
    private final FileTreeVIew fileTreeVIew;
    private final FileListView fileListView;
    private final ToolBarView toolBar;
    private final CreateFileView createFileView;
    private final Controller controller;

    private final BorderPane borderPane = new BorderPane();
    private final AnchorPane centerAnchor = new AnchorPane(), treeAnchor = new AnchorPane();


    {
        fileListView = new FileListView();
        createFileView = new CreateFileView();
        toolBar = new ToolBarView();
        fileTableView = new FileTableView();
        fileTreeVIew = new FileTreeVIew();
        controller = new Controller(fileTreeVIew, fileTableView, toolBar, createFileView, fileListView);
    }

    public MainWindowView() {
        configureCenter();
        configureToolBar();
    }

    private void configureCenter() {
        AnchorPane.setTopAnchor(fileTreeVIew.getTreeView(), 0.0);
        AnchorPane.setLeftAnchor(fileTreeVIew.getTreeView(), 0.0);
        AnchorPane.setRightAnchor(fileTreeVIew.getTreeView(), 0.0);
        AnchorPane.setBottomAnchor(fileTreeVIew.getTreeView(), 0.0);
        treeAnchor.getChildren().addAll(fileTreeVIew.getTreeView());
        setFileView(fileTableView);
        this.borderPane.setTop(toolBar.getToolBar());
        this.borderPane.setLeft(treeAnchor);
        this.borderPane.setCenter(centerAnchor);
        this.borderPane.setBottom(createFileView.asNode());
    }

    private void setFileView(FileView fileView) {
        AnchorPane.setTopAnchor(fileView.getView(), 0.0);
        AnchorPane.setLeftAnchor(fileView.getView(), 0.0);
        AnchorPane.setRightAnchor(fileView.getView(), 0.0);
        AnchorPane.setBottomAnchor(fileView.getView(), 0.0);
        centerAnchor.getChildren().clear();
        centerAnchor.getChildren().addAll(fileView.getView());
    }

    private void configureToolBar() {
        toolBar.getExtensionFilterButton().setOnAction(actionEvent -> {
            TextField textField = new TextField(" ");
            Button okButton = new Button("Ok");
            toolBar.getToolBar().getItems().addAll(textField, okButton);
            okButton.setOnAction(actionEvent1 -> {
                controller.filter(textField.getText());
                toolBar.getToolBar().getItems().removeAll(textField, okButton);
            });
        });

        toolBar.getTableModeButton().setOnAction(actionEvent -> {
            setFileView(fileTableView);
            controller.setViewMode(ViewMode.Table);
        });
        toolBar.getListModeButton().setOnAction(actionEvent -> {
            setFileView(fileListView);
            controller.setViewMode(ViewMode.List);
        });
    }

    public Parent asParent() {
        return borderPane;
    }
}
