package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneroDao extends MySQLConnection implements Dao<Genero> {
    private Connection conn = getConnection();
    private String table = "genero";


    @Override
    public Optional<Genero> findById(int id) {
        Optional<Genero> optionalGenero = Optional.empty();
        String query = "select * from " + table + " where id_genero = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("id_genero"));
                genero.setGenero(rs.getString("genero"));
                optionalGenero = Optional.of(genero);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalGenero;
    }

    @Override
    public List<Genero> findAll() {
        List<Genero> generoList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("id_genero"));
                genero.setGenero(rs.getString("genero"));
                generoList.add(genero);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generoList;
    }

    @Override
    public boolean save(Genero record) {
        String query = "insert into " + table + " (genero) values (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getGenero());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Genero record) {
        String query = "update " + table + " set genero=? where id_genero = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getGenero());
            ps.setInt(2, record.getIdGenero());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from " + table + " where id_genero = ?";
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
