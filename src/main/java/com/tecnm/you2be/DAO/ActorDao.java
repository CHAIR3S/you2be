package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Actor;
import com.tecnm.you2be.models.Rol;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ActorDao extends MySQLConnection implements Dao<Actor> {
    Connection conn = getConnection();

    private String table = "actor";


    @Override
    public Optional<Actor> findById(int id) {
        Optional<Actor> optionalActor = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() )
            {
                Actor object = new Actor();
                object.setIdActor(rs.getInt("id_actor"));
                object.setNombre(rs.getString("nombre"));
                object.setPrimerApellido(rs.getString("primer_apellido"));
                object.setSegundoApellido(rs.getString("segundo_apellido"));
                object.setIdRol(rs.getInt("id_rol"));
                optionalActor = Optional.of(object);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalActor;
    }

    @Override
    public List<Actor> findAll() {

        List<Actor> objectList = FXCollections.observableArrayList();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Actor object = new Actor();
                object.setIdActor(rs.getInt("id_actor"));
                object.setNombre(rs.getString("nombre"));
                object.setPrimerApellido(rs.getString("primer_apellido"));
                object.setSegundoApellido(rs.getString("segundo_apellido"));
                object.setIdRol(rs.getInt("id_rol"));
                objectList.add(object);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objectList;
    }

    @Override
    public boolean save(Actor record) {

        String query = "insert into " + table +
                " (nombre, primer_apellido, segundo_apellido, id_rol)" +
                " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNombre());
            ps.setString(2, record.getPrimerApellido());
            ps.setString(3, record.getSegundoApellido());
            ps.setInt(4, record.getIdRol());
            ps.execute();

            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Actor record) {

        String query = "update " + table + " set nombre=?, primer_apellido=?, segundo_apellido=?, id_rol=?  where id_" + table +" = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNombre());
            ps.setString(2, record.getPrimerApellido());
            ps.setString(3, record.getSegundoApellido());
            ps.setInt(4, record.getIdRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {

        String query = "delete from "+ table +" where id_" + table + " = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
