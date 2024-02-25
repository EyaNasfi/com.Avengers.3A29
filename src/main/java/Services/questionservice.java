package Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Questions;
import models.quiz;
import utils.mydb;

import java.sql.*;

public class questionservice implements IService<Questions> {
    private Connection connection;

    public questionservice() {
        connection = mydb.getInstance().getCnx();
    }







    /* @Override
     public int add0(quiz q) throws SQLException {
         String sql = "INSERT INTO quiz (nom,nbrquest,ID_de_Formation,iduser) VALUES (?,?,1,1)";
         PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         ps.setString(1, q.getNom());
         ps.setString(2, String.valueOf(q.getNbrquest()));
         ps.executeUpdate();

         /*ps.setString(3, String.valueOf(q.getID_de_Formation()));
         ps.setString(4, String.valueOf(q.getIduser()));*/
      /*  ResultSet generatedKeys = ps.getGeneratedKeys();
        int idQuiz;
        if (generatedKeys.next()) {
            idQuiz = generatedKeys.getInt(1);
            System.out.println("ID du quiz inséré : " + idQuiz);
        } else {
            throw new SQLException("Impossible de récupérer l'ID du quiz inséré.");
        }

        generatedKeys.close();
        ps.close();
        return idQuiz;
    }
*/
   /* @Override
    public void add1(Questions questions) throws SQLException {

    }*/

    public void add1(Questions questions) throws SQLException {
       // int idQuiz = add0(questions.getQuiz());
        quizservice qu=new quizservice() {
        };
       /// quiz q=new quiz();
        String sql = "INSERT INTO questions (op1,op2,op3,answer,question,idquiz,iduser) VALUES (?,?,?,?,?,?,1)";
        PreparedStatement ps=connection.prepareStatement(sql);
        quiz q=new quiz();
        ps.setString(1, questions.getOp1());
        ps.setString(2, questions.getOp2());
        ps.setString(3, questions.getOp3());
        ps.setString(4, questions.getAnswer());
        ps.setString(5, questions.getQuestion());
        ps.setInt(6, questions.getIdquiz());
        ps.executeUpdate();

    }




    @Override
    public void update(Questions questions, int id) throws SQLException {
        String sql = "UPDATE questions SET op1=? ,op2=? , op3=? ,answer=?,question=?,idquiz=?,iduser=? WHERE idquest=?";
        // user u= new user();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, questions.getOp1());
        preparedStatement.setString(2, questions.getOp2());
        preparedStatement.setString(3,questions.getOp3());
        preparedStatement.setString(5,questions.getQuestion());
        preparedStatement.setString(4,questions.getAnswer());
        preparedStatement.setInt(6,1);
        preparedStatement.setInt(7,1);
        preparedStatement.setInt(8,id);
        preparedStatement.executeUpdate();
        System.out.println("questionsmodifiée");
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from questions where idquest =?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public ObservableList<Questions> getAll() throws SQLException {
        ObservableList<Questions>list = FXCollections.observableArrayList();
        String sql ="SELECT* FROM questions WHERE iduser=1";
        Statement stat = connection.prepareStatement(sql);
        ResultSet rs= stat.executeQuery(sql);
        while (rs.next())
        {
           Questions q=new Questions();
            q.setIdquest(rs.getInt("idquest"));
            q.setOp1(rs.getString("op1"));
            q.setOp2(rs.getString("op2"));
            q.setOp3(rs.getString("op3"));
            q.setAnswer(rs.getString("answer"));
            q.setQuestion(rs.getString("question"));
            q.setIdquiz(rs.getInt("idquiz"));

            //q.setUser.g("1");//q.setDescription(rs.getString("description"));
            list.add(q);
        }
        return list;
    }

    @Override
    public Questions getById(int id) throws SQLException {
        return null;
    }
}
