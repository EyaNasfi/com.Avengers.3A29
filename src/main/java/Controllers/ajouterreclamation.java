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

    import javax.imageio.IIOParam;
    import java.awt.event.MouseEvent;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.URL;
    import java.net.URLConnection;
    import java.net.URLEncoder;
    import java.sql.SQLException;
    import java.util.List;
    import java.util.Objects;
// Install the Java helper library from twilio.com/docs/java/install

    import com.twilio.Twilio;
    import com.twilio.rest.api.v2010.account.Message;
    import com.twilio.type.PhoneNumber;

    import java.net.URI;
    import java.util.Arrays;


    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.net.URL;
    import java.net.URLConnection;
    import java.net.URLEncoder;

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
    void initialize() throws SQLException {affichage();}
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
    void update(ActionEvent event) throws SQLException {


        Reclamation r = affiche.getSelectionModel().getSelectedItem();

        String t=titre.getText();
        String d=description.getText();
        Reclamation re=new Reclamation(t,d);
        System.out.println(r.getId());
        rs.update(re,r.getId());
        affichage();
    }
    @FXML
    void quiz(ActionEvent event)  throws IOException {




        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nvquiz.fxml"));

        Parent root = loader.load();
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


    @FXML
  public   void sms(ActionEvent event) {

        sendSms();

    }



        public String sendSms() {
            try {
                // Construct data
                String apiKey = "apikey=" + URLEncoder.encode("yourapiKey", "UTF-8");
                String message = "&message=" + URLEncoder.encode("This is your message", "UTF-8");
                String sender = "&sender=" + URLEncoder.encode("Jims Autos", "UTF-8");
                String numbers = "&numbers=" + URLEncoder.encode("447123456789", "UTF-8");


                // Send data
                String data = "https://api.txtlocal.com/send/?" + "NTk0MjZiNzY1NjZhNTM3MTQ5NjQ0NjM3NzMzOTRjNDY "+21782711 + description.getText() +"eya";
                URL url = new URL(data);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String sResult="";
                while ((line = rd.readLine()) != null) {
                    // Process line...
                    sResult=sResult+line+" ";
                }
                rd.close();

                return sResult;
            } catch (Exception e) {
                System.out.println("Error SMS "+e);
                return "Error "+e;
            }
        }


    public void supp(ActionEvent actionEvent) throws SQLException {
        Reclamation r = affiche.getSelectionModel().getSelectedItem() ;
        System.out.println(r.getId());
        rs.delete(r.getId());
        affichage();
    }
}

