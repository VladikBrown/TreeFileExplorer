package application.view.workspace;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

public class FileListView implements FileView<FlowPane> {
    private FlowPane flowPane = new FlowPane();

    public FileListView() {
        flowPane.setOrientation(Orientation.VERTICAL);
    }

    @Override
    public FlowPane getView() {
        return flowPane;
    }

    @Override
    public Node asNode() {
        return flowPane;
    }
}
