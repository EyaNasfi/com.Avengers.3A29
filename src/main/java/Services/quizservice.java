package Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.quiz;
import utils.mydb;

import java.sql.*;

public abstract class quizservice implements IService<quiz> {

    public quizservice(){
        connection = mydb.getInstance().getCnx();
    }

    private Connection connection;





    @Override
    public void add1(quiz q) throws SQLException {
        String sql = "INSERT INTO quiz (nom,nbrquest,ID_de_Formation,iduser) VALUES (?,?,1,1)";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,q.getNom());
        ps.setString(2, String.valueOf(q.getNbrquest()));

        ps.executeUpdate();
    }





    @Override
    public void update(quiz q, int id) throws SQLException {
        String sql = "UPDATE quiz SET nom=? ,nbrquest=? , ID_de_Formation=? ,iduser=? WHERE idquiz=?";
       // user u= new user();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, q.getNom());
        preparedStatement.setString(2, String.valueOf(q.getNbrquest()));
        preparedStatement.setInt(3,1);
        preparedStatement.setInt(4,1);
        preparedStatement.setInt(5,id);
        preparedStatement.executeUpdate();
        System.out.println("quiz modifiée");
    }



    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from quiz where idquiz =?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();

    }
    @Override
    public ObservableList<quiz> getAll() throws SQLException {
        ObservableList<quiz>list = FXCollections.observableArrayList();
        String sql ="SELECT* FROM quiz WHERE iduser=1 AND ID_de_Formation=1";
        Statement stat = connection.prepareStatement(sql);
        ResultSet rs= stat.executeQuery(sql);
        while (rs.next())
        {
          quiz q=new quiz();
            q.setId(rs.getInt("idquiz"));
           /// u.setId(rs.getInt("iduser"));
            q.setNom(rs.getString("nom"));
            q.setNbrquest(rs.getInt("nbrquest"));
            list.add(q);
        }
        return list;
    }

    @Override
    public quiz getById(int id) throws SQLException {

        return null;
    }
}
