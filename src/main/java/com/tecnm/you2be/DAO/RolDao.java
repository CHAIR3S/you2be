package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RolDao extends MySQLConnection implements Dao<Rol> {


    private Connection conn = getConnection();
    private String table = "rol";

    @Override
    public Optional<Rol> findById(int id) {
        Optional<Rol> optionalRol = Optional.empty();
        String query = "select * from " + table + " where id_rol = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setRol(rs.getString("rol"));
                optionalRol = Optional.of(rol);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalRol;
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> rolList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setRol(rs.getString("rol"));
                rolList.add(rol);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rolList;
    }

    @Override
    public boolean save(Rol record) {
        String query = "insert into " + table + " (rol) values (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Rol record) {
        String query = "update " + table + " set rol=? where id_rol = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getRol());
            ps.setInt(2, record.getIdRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from " + table + " where id_rol = ?";
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
