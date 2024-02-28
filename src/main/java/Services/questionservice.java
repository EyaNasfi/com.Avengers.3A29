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
        preparedStatement.setString(4,questions.getAnswer());
        preparedStatement.setString(5,questions.getQuestion());
        preparedStatement.setInt(6,questions.getIdquiz());
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
        String sql ="SELECT questions.idquest,questions.op1,questions.op2,questions.op3,questions.answer,questions.question,quiz.nom,quiz.nbrquest,quiz.idquiz from questions INNER JOIN quiz on questions.idquiz=quiz.idquiz WHERE questions.iduser=1";
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
            q.setNom(rs.getString("nom"));
            q.setNbrquest(rs.getInt("nbrquest"));
            q.setIdquiz(rs.getInt("idquiz"));

            //q.setUser.g("1");//q.setDescription(rs.getString("description"));
            list.add(q);
        }
        return list;
    }
    @Override
    public ObservableList<Questions> get() throws SQLException {
        ObservableList<Questions>list = FXCollections.observableArrayList();
        String sql ="SELECT questions.idquest,questions.op1,questions.op2,questions.op3,questions.question,questions.idquiz from questions WHERE questions.iduser=1";
        Statement stat = connection.prepareStatement(sql);
        ResultSet rs= stat.executeQuery(sql);
        while (rs.next())
        {
            Questions q=new Questions();

            q.setIdquest(rs.getInt("idquest"));
            q.setOp1(rs.getString("op1"));
            q.setOp2(rs.getString("op2"));
            q.setOp3(rs.getString("op3"));
          ///  q.setAnswer(rs.getString("answer"));
            q.setQuestion(rs.getString("question"));
          ///  q.setNom(rs.getString("nom"));
          ///  q.setNbrquest(rs.getInt("nbrquest"));
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
