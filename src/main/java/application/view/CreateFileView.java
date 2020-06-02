package application.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

public class CreateFileView {
    private final TextField textField = new TextField(" ");
    private final Button createButton = new Button("Create");
    private AnchorPane anchorPane = new AnchorPane();
    private final ToolBar toolBar = new ToolBar(createButton, anchorPane);

    public CreateFileView() {
        AnchorPane.setTopAnchor(textField, 0.0);
        AnchorPane.setBottomAnchor(textField, 0.0);
        AnchorPane.setLeftAnchor(textField, 10.0);
        AnchorPane.setRightAnchor(textField, 10.0);
        anchorPane.getChildren().addAll(textField);
        createButton.setPrefSize(100, 40);
        textField.setPrefSize(250, 40);
        toolBar.setOrientation(Orientation.HORIZONTAL);
    }

    public void setOnCreateButton(EventHandler<ActionEvent> eventEventHandler) {
        createButton.setOnAction(eventEventHandler);
    }

    public String getFileName() {
        return textField.getText();
    }

    public Node asNode() {
        return toolBar;
    }
}
