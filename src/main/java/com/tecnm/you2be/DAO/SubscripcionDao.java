package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Subscripcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscripcionDao extends MySQLConnection implements Dao<Subscripcion> {


    private Connection conn = getConnection();
    private String table = "subscripcion";



    @Override
    public Optional<Subscripcion> findById(int id) {
        Optional<Subscripcion> optionalSubscripcion = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Subscripcion subscripcion = new Subscripcion();
                subscripcion.setIdSubscripcion(rs.getInt("id_subscripcion"));
                subscripcion.setCosto(rs.getBigDecimal("costo"));
                subscripcion.setTipo(rs.getString("tipo"));
                subscripcion.setIdUsuario(rs.getInt("id_usuario"));
                subscripcion.setIdPago(rs.getInt("id_pago"));
                optionalSubscripcion = Optional.of(subscripcion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalSubscripcion;
    }

    @Override
    public List<Subscripcion> findAll() {
        List<Subscripcion> subscripcionList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Subscripcion subscripcion = new Subscripcion();
                subscripcion.setIdSubscripcion(rs.getInt("id_subscripcion"));
                subscripcion.setCosto(rs.getBigDecimal("costo"));
                subscripcion.setTipo(rs.getString("tipo"));
                subscripcion.setIdUsuario(rs.getInt("id_usuario")); // Assume this is the additional field
                subscripcion.setIdPago(rs.getInt("id_pago"));
                subscripcionList.add(subscripcion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subscripcionList;
    }


    @Override
    public boolean save(Subscripcion record) {
        String query = "insert into " + table + " (costo, tipo, id_usuario, id_pago) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBigDecimal(1, record.getCosto());
            ps.setString(2, record.getTipo());
            ps.setInt(3, record.getIdUsuario()); // Include idUsuario in INSERT
            ps.setInt(4, record.getIdPago());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean update(Subscripcion record) {
        String query = "update " + table + " set costo=?, tipo=?, id_usuario=?, id_pago=? where id_" + table + " = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBigDecimal(1, record.getCosto());
            ps.setString(2, record.getTipo());
            ps.setInt(3, record.getIdUsuario()); // Include idUsuario in UPDATE
            ps.setInt(4, record.getIdPago());
            ps.setInt(5, record.getIdSubscripcion());
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
