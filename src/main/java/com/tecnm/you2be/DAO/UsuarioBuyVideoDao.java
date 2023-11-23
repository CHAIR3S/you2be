package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.UsuarioBuyVideo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioBuyVideoDao extends MySQLConnection implements Dao<UsuarioBuyVideo> {

    private Connection conn = getConnection();
    private String table = "usuario_buy_video";

    @Override
    public Optional<UsuarioBuyVideo> findById(int id) {
        Optional<UsuarioBuyVideo> optionalUsuarioBuyVideo = Optional.empty();
        String query = "select * from " + table + " where id_compra = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                UsuarioBuyVideo usuarioBuyVideo = new UsuarioBuyVideo();
                usuarioBuyVideo.setIdCompra(rs.getInt("id_compra"));
                usuarioBuyVideo.setDescuento(rs.getBigDecimal("descuento"));
                usuarioBuyVideo.setIdUsuario(rs.getInt("id_usuario"));
                usuarioBuyVideo.setIdVideo(rs.getInt("id_video"));
                usuarioBuyVideo.setIdPago(rs.getInt("id_pago"));
                optionalUsuarioBuyVideo = Optional.of(usuarioBuyVideo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalUsuarioBuyVideo;
    }

    @Override
    public List<UsuarioBuyVideo> findAll() {
        List<UsuarioBuyVideo> usuarioBuyVideoList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                UsuarioBuyVideo usuarioBuyVideo = new UsuarioBuyVideo();
                usuarioBuyVideo.setIdCompra(rs.getInt("id_compra"));
                usuarioBuyVideo.setDescuento(rs.getBigDecimal("descuento"));
                usuarioBuyVideo.setIdUsuario(rs.getInt("id_usuario"));
                usuarioBuyVideo.setIdVideo(rs.getInt("id_video"));
                usuarioBuyVideo.setIdPago(rs.getInt("id_pago"));
                usuarioBuyVideoList.add(usuarioBuyVideo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarioBuyVideoList;
    }

    @Override
    public boolean save(UsuarioBuyVideo record) {
        String query = "insert into " + table + " (descuento, id_usuario, id_video, id_pago) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBigDecimal(1, record.getDescuento());
            ps.setInt(2, record.getIdUsuario());
            ps.setInt(3, record.getIdVideo());
            ps.setInt(4, record.getIdPago());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(UsuarioBuyVideo record) {
        String query = "update " + table + " set descuento=?, id_usuario=?, id_video=?, id_pago=? where id_compra = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBigDecimal(1, record.getDescuento());
            ps.setInt(2, record.getIdUsuario());
            ps.setInt(3, record.getIdVideo());
            ps.setInt(4, record.getIdPago());
            ps.setInt(5, record.getIdCompra());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from " + table + " where id_compra = ?";
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
