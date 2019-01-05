import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import java.io.IOException;

public class ListViewController {
    private wordsclass selectedWord;
    @FXML private Label wordlabel;
    @FXML private Label freqlabel;

    //this is called from Controller class
    public void initData(wordsclass word)
    {
        selectedWord=word;
        wordlabel.setText(selectedWord.getWord());
        freqlabel.setText(Integer.toString(selectedWord.getFreq()));
    }

    public void buttonClick(ActionEvent event) throws IOException {
        Parent sceneparent= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene=new Scene(sceneparent);
        Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}
