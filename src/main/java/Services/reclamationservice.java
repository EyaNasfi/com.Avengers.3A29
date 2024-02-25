package Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Questions;
import models.Reclamation;
import models.quiz;
import utils.mydb;

import java.sql.*;

public class reclamationservice implements  IService<Reclamation>{
    private Connection connection;
    public reclamationservice() {
        connection = mydb.getInstance().getCnx();
    }









    @Override
    public void add1(Reclamation reclamation) throws SQLException {
        String sql = "INSERT INTO reclamation (titre, description) VALUES (?,?)";
       /* System.out.println(sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);*/
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,reclamation.getTitre());
        ps.setString(2,reclamation.getDescription());
        ps.executeUpdate();

}


    @Override
public void update(Reclamation reclamation,int id) throws SQLException {
    String sql = "UPDATE reclamation SET titre=? , description=? WHERE idrec=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, reclamation.getTitre());
    preparedStatement.setString(2, reclamation.getDescription());
    preparedStatement.setInt(3, id);

    preparedStatement.executeUpdate();
    System.out.println("Reclamation modifiée");
}

   /* @Override
    public void update(Reclamation reclamation,int idrec) throws SQLException {
        String sql= "UPDATE reclamation SET titre=? , description=? WHERE idrec=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, reclamation.getTitre());
        preparedStatement.setString(2, reclamation.getDescription());
        preparedStatement.setInt(3,idrec);

        preparedStatement.executeUpdate();
        System.out.println("Reclamation modifiée");

    }*/

    @Override
    public void delete(int idrec) throws SQLException {
            String sql = "delete from reclamation where idrec =?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,idrec);
            ps.executeUpdate();
    }

    @Override
    public ObservableList<Reclamation> getAll() throws SQLException {
        ObservableList<Reclamation>list = FXCollections.observableArrayList();
        String sql ="SELECT* FROM reclamation";
        Statement stat = connection.prepareStatement(sql);
        ResultSet rs= stat.executeQuery(sql);
        while (rs.next())
        {
            Reclamation rec=new Reclamation();
            rec.setId(rs.getInt("idrec"));
            rec.setTitre(rs.getString("titre"));
            rec.setDescription(rs.getString("description"));
            list.add(rec);
        }
        return list;
    }

   /* @Override
    public List<Reclamation> getAll(Reclamation reclamation) throws SQLException {
        String sql ="SELECT * FROM reclamation ";
        Statement stat = connection.createStatement();
        ResultSet rs= stat.executeQuery(sql);
            List<Reclamation> list=new ArrayList<>();
            while (rs.next())
            {
                Reclamation rec=new Reclamation();
                rec.setId(rs.getInt("id"));
                rec.setTitre(rs.getString("titre"));
                rec.setDescription(rs.getString("description"));
                list.add(rec);
            }
        return list;
    }*/

    @Override
    public Reclamation getById(int idrec) throws SQLException {
        String sql = "SELECT * FROM reclamation WHERE idrec=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idrec);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Reclamation rec = new Reclamation();
           // rec.setId(rs.getInt("idrec"));
            rec.setTitre(rs.getString("titre"));
            rec.setDescription(rs.getString("description"));
            return rec;
        }
        return null;

        //return null;
    }
}


