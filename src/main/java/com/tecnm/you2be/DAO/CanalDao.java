package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Actor;
import com.tecnm.you2be.models.Canal;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CanalDao extends MySQLConnection implements Dao<Canal> {

    Connection conn = getConnection();

    private String table = "canal";


    @Override
    public Optional findById(int id) {
        Optional<Canal> optionalActor = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() )
            {
                Canal object = new Canal();
                object.setIdCanal(rs.getInt("id_canal"));
                object.setNombre(rs.getString("nombre"));
                object.setIdUsuario(rs.getInt("id_usuario"));
                object.setIdVideo(rs.getInt("id_video"));
                optionalActor = Optional.of(object);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalActor;
    }

    @Override
    public List findAll() {


        List<Canal> objectList = FXCollections.observableArrayList();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Canal object = new Canal();
                object.setIdCanal(rs.getInt("id_canal"));
                object.setNombre(rs.getString("nombre"));
                object.setIdUsuario(rs.getInt("id_usuario"));
                object.setIdVideo(rs.getInt("id_video"));
                objectList.add(object);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objectList;
    }

    @Override
    public boolean save(Canal record) {


        String query = "insert into " + table +
                " (nombre, id_usuario, id_video)" +
                " values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNombre());
            ps.setInt(2, record.getIdUsuario());
            ps.setInt(3, record.getIdVideo());
            ps.execute();

            return true;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Canal record) {


        String query = "update " + table + " set nombre=?, id_usuario=?, id_video=?  where id_" + table +" = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNombre());
            ps.setInt(2, record.getIdUsuario());
            ps.setInt(3, record.getIdVideo());
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
