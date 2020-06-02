package application.model;

import javafx.beans.property.SimpleStringProperty;


/**
 * Created by Fahim on 4/19/2017.
 */
public class FileInfo {
    private SimpleStringProperty name;
    private SimpleStringProperty extension;
    private SimpleStringProperty size;
    private SimpleStringProperty date;

    public FileInfo(String name, String extension, String size, String date) {
        super();
        this.name = new SimpleStringProperty(name);
        this.extension = new SimpleStringProperty(extension);
        this.size = new SimpleStringProperty(size);
        this.date = new SimpleStringProperty(date);
    }

    //public Integer getId(){ return id.get(); }
    public String getExtension() {
        return extension.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getSize() {
        return size.get();
    }

    public String getName() {
        return name.get();
    }
}
