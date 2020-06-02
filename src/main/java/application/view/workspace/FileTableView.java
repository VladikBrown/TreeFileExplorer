package application.view.workspace;

import application.model.FileInfo;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FileTableView implements FileView<TableView<FileInfo>> {
    private final TableView<FileInfo> tableView = new TableView<>();
    private final TableColumn<FileInfo, String> name = new TableColumn<>("Name");
    private final TableColumn<FileInfo, String> extension = new TableColumn<>("Extension");
    private final TableColumn<FileInfo, String> date = new TableColumn<>("Date");
    private final TableColumn<FileInfo, String> size = new TableColumn<>("Size");

    public FileTableView() {
        configureTable();
    }

    private void configureTable() {
        tableView.setFixedCellSize(20);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setMinWidth(300);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        extension.setCellValueFactory(new PropertyValueFactory<>("extension"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.getColumns().addAll(name, extension, date, size);
    }

    @Override
    public TableView<FileInfo> getView() {
        return tableView;
    }

    @Override
    public Node asNode() {
        return tableView;
    }
}
