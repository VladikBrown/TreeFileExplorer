package application.view;

import javafx.scene.control.TreeView;

public class FileTreeVIew {
    private TreeView<String> treeView;

    public FileTreeVIew() {
        treeView = new TreeView<>();
        treeView.setPrefWidth(250);
    }

    public TreeView<String> getTreeView() {
        return this.treeView;
    }
}

