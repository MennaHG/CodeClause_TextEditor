package com.example.text_editor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private TextArea textArea;

    @FXML
    private HBox hbox;
    private Stage stage;
    @FXML
    private Spinner fontsize;
    private final FileChooser fileChooser = new FileChooser();


    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser
                .getExtensionFilters()
                .addAll(
                        new FileChooser.ExtensionFilter("Text", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    public void init(Stage myStage) {
        this.stage = myStage;

    }

    @FXML
    public void exit() {
        if (textArea.getText().isEmpty()) {
            Platform.exit();
            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Exit without saving?",
                ButtonType.NO,
                ButtonType.YES,
                ButtonType.CANCEL
        );

        alert.setTitle("Confirm");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        if (alert.getResult() == ButtonType.NO) {
            save();
            Platform.exit();
        }
    }

    @FXML
    private void save() {
        try {
            fileChooser.setTitle("Save As");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
               // file.renameTo(new File(file.getAbsolutePath()+".txt"));
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(textArea.getText());
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openFile() {
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            textArea.clear();
            readText(file);
        }
    }

    private void readText(File file) {
        String text;

        try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
            while ((text = buffReader.readLine()) != null) {
                textArea.appendText(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void newFile() {
        textArea.clear();
    }

    @FXML
    public void fontSize(ActionEvent e) {
        String choice = ((CheckMenuItem) e.getSource()).getId();

        textArea.setStyle("-fx-font-size: 14px");

    }


//--------------------------------------------------------------------------------
// Controls Bar Functions

    @FXML
    public void boldFont() {
        ToggleButton btn = (ToggleButton) hbox.getChildren().get(0);
        if (btn.isSelected())
            textArea.getStyleClass().add("text-area-bold-text");
        else
            textArea.getStyleClass().remove("text-area-bold-text");
     //   hbox.getChildren().get(0);
       // event.
    //    textArea.getStyleClass().add("text-area-bold-text");
     //   hbox.getChildren().get(0).getStyleClass().add("focused" );
      //  button.setStyle("-fx-background-color:#000080; -fx-color:#FFFFFF");
//        removeStyleClass(hbox.getChildren().get(1),"text-area-italic-text");
//        removeStyleClass(hbox.getChildren().get(2),"text-area-underline-text");

    }

    public void removeStyleClass(Node node , String s){
        if(node.getStyleClass().contains(s))
            node.getStyleClass().remove(s);
    }
    @FXML
    public void italicFont(){
        ToggleButton btn = (ToggleButton) hbox.getChildren().get(1);
        if (btn.isSelected())
            textArea.getStyleClass().add("text-area-italic-text");
        else
            textArea.getStyleClass().remove("text-area-italic-text");
     //   textArea.getStyleClass().add("text-area-italic-text");
       // hbox.getChildren().get(1).getStyleClass().add("focused" );

    }

    @FXML
    public void underline(){
        ToggleButton btn = (ToggleButton) hbox.getChildren().get(2);
        if (btn.isSelected())
            textArea.getStyleClass().add("text-area-underline-text");
        else
            textArea.getStyleClass().remove("text-area-underline-text");
    }

    @FXML
    public void leftalign(){

        textArea.getStyleClass().removeAll("text-area-right","text-area-center","text-area-justify");

        textArea.getStyleClass().add("text-area-left");
    }

    @FXML
    public void rightalign(){
        textArea.getStyleClass().removeAll("text-area-left","text-area-center","text-area-justify");
        textArea.getStyleClass().add("text-area-right");
    }
    @FXML
    public void centeralign(){
        textArea.getStyleClass().removeAll("text-area-left","text-area-right","text-area-justify");
        textArea.getStyleClass().add("text-area-center");
    }
    @FXML
    public void justifyalign(){
//        textArea.getStyleClass().removeAll("text-area-left","text-area-right","text-area-center");

        textArea.getStyleClass().add("text-area-justify");
    }

    @FXML
    public void setFontsize(){
        String spinnerval = fontsize.getValue().toString();
        textArea.setFont(Font.font(Double.parseDouble(spinnerval)));
    }


    @FXML
    public void undo(){
        textArea.undo();
    }

    @FXML
    public void redo(){
        textArea.redo();
    }

    @FXML
    public void copy(){
        textArea.copy();
    }

    @FXML
    public void cut(){
        textArea.cut();
    }

    @FXML
    public void paste(){
        textArea.paste();
    }

    @FXML
    public void delete(){
        textArea.replaceText(textArea.getSelection(),"");
    }
    @FXML
    public void selectAll(){
        textArea.selectAll();
    }


}