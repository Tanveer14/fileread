import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.lang.NullPointerException;

public class Controller implements Initializable {
    public Stage window=new Stage();
    public Label label;
    public Button actionbutton;
    public TableView<wordsclass> table1;
    public TableColumn<wordsclass,String> wordCol;
    public TableColumn<wordsclass,Integer> freqCol;
    public String[] filewords=new String[1000];
    public wordsclass []w;
    public static ObservableList<wordsclass> list=FXCollections.observableArrayList();
    public File file;
    @FXML
    Button choose;
    public void filechooser() throws NullPointerException
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        file= fileChooser.showOpenDialog(window);

        //Setting the text in label
        label.setText(file.getName());

        if(label.getText()==null)actionbutton.setDisable(true);
        else actionbutton.setDisable(false);
    }

    public wordsclass[] FindOut(File file)
    {
        String s= new String();
        int i=0;
        wordsclass []word=new wordsclass[1000];
        for (i=0;i<1000;i++) {
            word[i]=new wordsclass();
        }
        i=0;

        //scanning from a file
        try{
            Scanner scan=new Scanner(file);
            scan.useDelimiter("[^A-Za-z0-9]+");
            while(scan.hasNext()) {

                //string[i]=s;
                int temp = 0;
                int k=0;
                s= scan.next();
                for(k=0;k<i;k++)
                {

                    temp=0;
                    if(s.equalsIgnoreCase(word[k].getWord())){
                        temp=1;

                        break;
                    }
                }
                if (temp==0){
                    word[i].setWord(s);
                    word[i].setFreq(word[i].getFreq()+1);
                    i++;
                }
                else {
                    word[k].setFreq(word[k].getFreq()+1);
                }

                // System.out.println(temp);
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR");
        }

        return word;
    }

    public void organize()
    {
        w=FindOut(file);
        list=getList(w);
        writeInTable();
    }

    public ObservableList<wordsclass> getList(wordsclass word[])
    {
        int i=0;
        ObservableList<wordsclass> list1= FXCollections.observableArrayList();
        while(word[i].getWord()!=null){
           list1.add(word[i]);
            i++;

        }
        return list1;
    }

    public void writeInTable()
    {
        wordCol.setCellValueFactory(new PropertyValueFactory<>("word"));
        freqCol.setCellValueFactory(new PropertyValueFactory<>("freq"));
        table1.setItems(list);

    }


    //veery much important
    public void knowmorebutton(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("listview.fxml"));
        Parent newsceneparent=loader.load();

        Scene scene=new Scene(newsceneparent);

        ListViewController controller=loader.getController();
        controller.initData(table1.getSelectionModel().getSelectedItem());

        Stage window=  (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}



/*Parent sceneparent= FXMLLoader.load(getClass().getResource("listview.fxml"));
        Scene scene1=new Scene(sceneparent);
        Stage window=  (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();*/