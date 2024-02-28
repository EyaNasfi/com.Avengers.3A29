package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.imageio.IIOParam;
import javax.imageio.IIOParamController;

public class Itemq {
    @FXML
    private VBox itemC;

    @FXML
    private Label quest;


    @FXML
    private Button op1;

    @FXML
    private Button op2;

    @FXML
    private Button op3;

    public int opchoi(){
        if (op1.isPressed() )
        {
            return 1;
        } else if (op2.isPressed()) {
            return 2;
        } else if (op3.isPressed()) {
            return 3;
        }
        return 0;
    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/questint.fxml"));
   interfacequest n = loader.getController();


}

