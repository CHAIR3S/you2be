package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaDao extends MySQLConnection implements Dao<Categoria> {


    private Connection conn = getConnection();
    private String table = "categoria";


    @Override
    public Optional<Categoria> findById(int id) {
        Optional<Categoria> optionalCategoria = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
                categoria.setIdGenero(rs.getInt("id_genero"));
                optionalCategoria = Optional.of(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalCategoria;
    }

    @Override
    public List<Categoria> findAll() {
        List<Categoria> categoriaList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setCategoria(rs.getString("categoria"));
                categoria.setIdGenero(rs.getInt("id_genero"));
                categoriaList.add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoriaList;
    }

    @Override
    public boolean save(Categoria record) {
        String query = "insert into " + table + " (categoria, id_genero) values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getCategoria());
            ps.setInt(2, record.getIdGenero());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Categoria record) {
        String query = "update " + table + " set categoria=?, id_genero=? where id_" + table + " = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getCategoria());
            ps.setInt(2, record.getIdGenero());
            ps.setInt(3, record.getIdCategoria());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from " + table + " where id_" + table + " = ?";
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
