package application.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;

public class ToolBarView {
    private final ToolBar toolBar = new ToolBar();

    private final Button tableModeButton = new Button("Table");
    private final Button listModeButton = new Button("List");


    private final Button extensionFilterButton = new Button("Filter");
    private final Label url;
    private final Label currentDir;

    public ToolBarView() {
        url = new Label();
        currentDir = new Label();
        toolBar.getItems().addAll(currentDir, tableModeButton, listModeButton, extensionFilterButton);
    }

    public Button getTableModeButton() {
        return tableModeButton;
    }

    public Button getExtensionFilterButton() {
        return extensionFilterButton;
    }


    public Button getListModeButton() {
        return listModeButton;
    }

    public Label getUrl() {
        return url;
    }

    public Label getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir.setText(currentDir);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
}
