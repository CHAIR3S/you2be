package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Tarjeta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarjetaDao extends MySQLConnection implements Dao<Tarjeta>  {


    private Connection conn = getConnection();
    private String table = "tarjeta";


    @Override
    public Optional<Tarjeta> findById(int id) {
        Optional<Tarjeta> optionalTarjeta = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setIdTarjeta(rs.getInt("id_tarjeta"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setCvv(rs.getString("cvv"));
                tarjeta.setTipo(rs.getString("tipo"));
                optionalTarjeta = Optional.of(tarjeta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalTarjeta;
    }

    @Override
    public List<Tarjeta> findAll() {
        List<Tarjeta> tarjetaList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setIdTarjeta(rs.getInt("id_tarjeta"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setCvv(rs.getString("cvv"));
                tarjeta.setTipo(rs.getString("tipo"));
                tarjetaList.add(tarjeta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tarjetaList;
    }

    @Override
    public boolean save(Tarjeta record) {
        String query = "insert into " + table + " (numero, cvv, tipo) values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNumero());
            ps.setString(2, record.getCvv());
            ps.setString(3, record.getTipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Tarjeta record) {
        String query = "update " + table + " set numero=?, cvv=?, tipo=? where id_" + table + " = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNumero());
            ps.setString(2, record.getCvv());
            ps.setString(3, record.getTipo());
            ps.setInt(4, record.getIdTarjeta());
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
