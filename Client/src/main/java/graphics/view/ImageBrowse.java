package graphics.view;

import javafx.stage.FileChooser;

import java.io.File;

public class ImageBrowse {
    public static String getImage () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG file (*.jpg)", "*.jpg"));
        File file = fileChooser.showOpenDialog(null);
        return file.getPath();
    }
}
