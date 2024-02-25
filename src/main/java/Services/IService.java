package Services;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import models.*;

import java.sql.SQLException;
import java.util.List;

public interface IService <T> {

    void add1(T t) throws SQLException;



    //void add1(T t) throws SQLException;

  //  void add1(quiz q, formation f, user u) throws SQLException;

    //void add1(Reclamation reclamation) throws SQLException;

    void update(T t, int id) throws SQLException;

    void delete(int id) throws SQLException;

    public ObservableList<T> getAll() throws SQLException;

    T getById(int id) throws  SQLException;

}
