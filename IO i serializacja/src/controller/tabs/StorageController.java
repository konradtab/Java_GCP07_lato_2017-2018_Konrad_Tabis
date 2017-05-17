package controller.tabs;

import Storage.StudentsStorage;
import controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageController {
    private StudentsStorage storage;
    private transient MainController main;
    @FXML
    TextField fieldImage, fieldLoadImage, fieldDeleteImage;

    public void init(MainController mainController) {
        storage = new StudentsStorage("studentsstorage.bin");
        main = mainController;
    }

    @FXML
    private void addImage() {
        try {
            storage.addImage(fieldImage.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadImage() {
        try {
            int whichOne = Integer.parseInt(fieldLoadImage.getText());
            byte[] image = storage.readImage(whichOne);
            if (image != null) {
                DataOutputStream dos_saveimage = new DataOutputStream(new FileOutputStream("zapisane_obrazy\\" + whichOne + ".jpg"));
                dos_saveimage.write(image);
                dos_saveimage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteImage() {
        try {
            int whichOne = Integer.parseInt(fieldDeleteImage.getText());
            storage.deleteImage(whichOne);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
