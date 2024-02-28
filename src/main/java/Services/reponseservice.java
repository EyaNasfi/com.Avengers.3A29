package Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.Questions;
import models.Reclamation;
import models.Reponses;
import utils.mydb;

import java.sql.*;

public class reponseservice implements IService<Reponses> {
    private Connection connection;
    public reponseservice() {
        connection = mydb.getInstance().getCnx();
    }


    @Override
    public void add1(Reponses reponses) throws SQLException {
        String sql = "INSERT INTO reponse (idrec,contenu) VALUES (?,?)";
       /* System.out.println(sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);*/
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1,reponses.getIdrec());
        ps.setString(2,reponses.getContenu());
        ps.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("reponse ajoute");
        alert.showAndWait();

    }

    @Override
    public void update(Reponses reponses, int id) throws SQLException {
        String sql = "UPDATE reponse SET idrec=? , contenu=? WHERE idrep=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, reponses.getIdrec());
        preparedStatement.setString(2, reponses.getContenu());
        preparedStatement.setInt(3, id);

        preparedStatement.executeUpdate();
        System.out.println("reponse modifiée");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("update sucess");
        alert.showAndWait();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from reponse where idrep =?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("reponse supp");
        alert.showAndWait();

    }
/*Statement stat = connection.prepareStatement(sql);
        ResultSet rs= stat.executeQuery(sql);
        while (rs.next())
        {
           Questions q=new Questions();

            //q.setIdquest(rs.getInt("idquest"));
            q.setOp1(rs.getString("op1"));
            q.setOp2(rs.getString("op2"));
            q.setOp3(rs.getString("op3"));
            q.setAnswer(rs.getString("answer"));
            q.setQuestion(rs.getString("question"));
            q.setNom(rs.getString("nom"));
            q.setNbrquest(rs.getInt("nbrquest"));
            q.setIdquiz(rs.getInt("idquiz"));

            //q.setUser.g("1");//q.setDescription(rs.getString("description"));
            list.add(q);
        }
        return list;
    }*/
    @Override
    public ObservableList<Reponses> getAll() throws SQLException {
        ObservableList<Reponses>list = FXCollections.observableArrayList();
        String sql ="SELECT reponse.idrep,reponse.contenu,reclamation.titre,reclamation.description,reclamation.idrec from reponse INNER JOIN reclamation on reponse.idrec=reclamation.idrec WHERE reclamation.iduser=2";
        Statement stat = connection.prepareStatement(sql);
        ResultSet rs= stat.executeQuery(sql);
        while (rs.next())
        {
            Reponses r=new Reponses();
            r.setIdrep(rs.getInt("idrep"));

            r.setContenu(rs.getString("contenu"));
            r.setTitre(rs.getString("titre"));
            r.setDescription(rs.getString("description"));
           r.setIdrec(rs.getInt("idrec"));
           /// r.setIdrep(rs.getInt("idrep"));

            //q.setUser.g("1");//q.setDescription(rs.getString("description"));
            list.add(r);
        }
        return list;
    }

    @Override
    public ObservableList<Questions> get() throws SQLException {
        return null;
    }

    @Override
    public Reponses getById(int id) throws SQLException {
        return null;
    }
}
