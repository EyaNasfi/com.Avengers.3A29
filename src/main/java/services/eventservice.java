
package services;

import models.event;
import utils.projetpi;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class eventservice implements crud<event> {
    private Connection connection;

    public eventservice() {
        connection = projetpi.getInstance().getConnection();
    }

    @Override
    public List<event> afficher() throws SQLException {
        String req = "SELECT * FROM event";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<event> list = new ArrayList<>();
        while (res.next()) {
            event e = new event();
            e.setId(res.getInt("id"));
            e.setName(res.getString("name"));
            e.setLieu(res.getString("lieu"));
            e.setDate(res.getDate("date").toLocalDate());
            e.setHeure(res.getTime("heure").toLocalTime());
            list.add(e);
        }
        return list;
    }





    @Override
    public void add(event event) throws SQLException {
        String sql = "insert into event (name,lieu, date, heure) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, event.getName());
        preparedStatement.setString(2, event.getLieu());
        preparedStatement.setDate(3, Date.valueOf(event.getDate()));
        preparedStatement.setTime(4, Time.valueOf(event.getHeure()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(event event) throws SQLException {
        String sql = "update event set lieu = ?, name = ?, date = ?, heure = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, event.getLieu());
        preparedStatement.setString(2, event.getName());
        preparedStatement.setDate(3, Date.valueOf(event.getDate()));
        preparedStatement.setTime(4, Time.valueOf(event.getHeure()));
        preparedStatement.setInt(5, event.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void delete(event eventToDelete) throws SQLException {
        try {
            // Vérifiez d'abord si eventToDelete n'est pas null
            if (eventToDelete != null) {
                String sql = "DELETE FROM event WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, eventToDelete.getId());
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Error: eventToDelete is null");
            }
        } catch (SQLException e) {
            // Imprimez les détails de l'exception pour le débogage
            e.printStackTrace();
            throw e; // Rejetez l'exception après avoir imprimé les détails
        }
    }


    @Override
    public List<event> getAll() throws SQLException {
        String sql = "select * from event";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<event> events = new ArrayList<>();
        while (rs.next()) {
            event e;
            e = new event(
            );
            events.add(e);
        }
        return events;
    }


    @Override
    public event getById(int id) throws SQLException {
        String sql = "SELECT lieu, name, date, heure FROM event WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String lieu = resultSet.getString("lieu");
            String name = resultSet.getString("name");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            LocalTime heure = resultSet.getTime("heure").toLocalTime();

            return new event();
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }


    @Override
    public void modifier(event eventToModify) throws SQLException {
        try {
            if (eventToModify != null) {
                String sql = "UPDATE event SET lieu = ?, name = ?, date = ?, heure = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, eventToModify.getLieu());
                preparedStatement.setString(2, eventToModify.getName());
                preparedStatement.setDate(3, Date.valueOf(eventToModify.getDate()));
                preparedStatement.setTime(4, Time.valueOf(eventToModify.getHeure()));
                preparedStatement.setInt(5, eventToModify.getId());
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Error: eventToModify is null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}