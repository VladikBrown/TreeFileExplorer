package application.view.workspace;


import javafx.scene.Node;

public interface FileView<T extends Node> {

    T getView();

    Node asNode();
}
