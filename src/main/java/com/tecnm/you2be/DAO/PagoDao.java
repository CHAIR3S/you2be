package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Pago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PagoDao extends MySQLConnection implements Dao<Pago> {

    private Connection conn = getConnection();
    private String table = "pago";

    @Override
    public Optional<Pago> findById(int id) {

        Optional<Pago> optionalPago = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setMetodo(rs.getString("metodo"));
                pago.setMonto(rs.getDouble("monto"));
                pago.setIdTarjeta(rs.getInt("id_tarjeta"));
                optionalPago = Optional.of(pago);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalPago;
    }

    @Override
    public List<Pago> findAll() {

        List<Pago> subscripcionList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setMetodo(rs.getString("metodo"));
                pago.setMonto(rs.getDouble("monto"));
                pago.setIdTarjeta(rs.getInt("id_tarjeta"));
                subscripcionList.add(pago);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subscripcionList;
    }

    @Override
    public boolean save(Pago record) {
        String query = "insert into " + table + " (metodo, monto, id_tarjeta) values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getMetodo());
            ps.setDouble(2, record.getMonto());
            ps.setInt(3, record.getIdTarjeta()); // Include idUsuario in INSERT
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Pago record) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
