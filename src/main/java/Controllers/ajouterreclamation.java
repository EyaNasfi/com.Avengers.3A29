package Controllers;


    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.*;
    import javafx.scene.control.*;
    import Services.reclamationservice;
        import Services.IService;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.stage.Stage;
    import models.Reclamation;
    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.layout.AnchorPane;
    import javafx.stage.Stage;
    import java.awt.event.MouseEvent;
    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.List;


public class ajouterreclamation {

    @FXML
    private TextField titre;


    @FXML
    private TableView<Reclamation> affiche;

    // @FXML
    // private TableColumn<Reclamation, Integer> idt;

    @FXML
    private TableColumn<Reclamation, String> titret;

    @FXML
    private TableColumn<Reclamation, String> descriptiont;
    @FXML
    private TextArea description;
    reclamationservice rs = new reclamationservice();
    private Stage stage;
    private Scene scene;
    private Parent root;
    public ajouterreclamation() throws IOException {
    }


    public void affichage() throws SQLException {
        ObservableList<Reclamation> l = rs.getAll();
        // affiche.getItems().clear();
        affiche.setItems(l);
        //idt.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
        titret.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre"));
        descriptiont.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        //rs.update();


    }


    @FXML
    void ajouter(ActionEvent event) throws SQLException {


        try {
            rs.add1((new Reclamation(titre.getText(), description.getText())));
            affichage();
            // rs.getAll(new Reclamation(titre.getText(), description.getText()));
            // rs.getAll(new Reclamation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //""""" idt.setCellFactory(new PropertyValueFactory<Reclamation,Integer>("id"));


    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException {


        Reclamation r = affiche.getSelectionModel().getSelectedItem();
        rs.delete(r.getId());
        affichage();

    }




  /*  @FXML
    void njareb(MouseEvent event) throws SQLException {

    }*/
  @FXML
  void njareb(ActionEvent event) {

  }
    @FXML
    void update(ActionEvent event) throws SQLException {


        Reclamation r = affiche.getSelectionModel().getSelectedItem();

        //update();
        //titre.setText(r.getTitre());
        // String d;
        //description.setText(r.getDescription());
       /* String d= descriptiont.getText();
        String t=titret.getText();

        rs.update(r,r.getId());
        System.out.println(r.getId());
*/
        String t=titre.getText();
        String d=description.getText();
        Reclamation re=new Reclamation(t,d);
        System.out.println(r.getId());
        rs.update(re,r.getId());
        affichage();
    }
    @FXML
    void quiz(ActionEvent event)  throws IOException {





        Parent root = FXMLLoader.load(getClass().getResource("/quiz.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
       scene =new Scene(root);
       stage.setScene(scene);
       stage.show();
     ///  if(event.isConsumed())



    }
    public void select() throws SQLException {


        Reclamation r=affiche.getSelectionModel().getSelectedItem();
        int num=affiche.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){return;}
        titre.setText(String.valueOf(r.getTitre()));
        description.setText(String.valueOf(r.getDescription()));

    }


    public void ajout(ActionEvent actionEvent) {
    }
}

