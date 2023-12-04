package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.EstadoCuenta;
import com.tecnm.you2be.models.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoDao extends MySQLConnection implements Dao<Video>  {

    private Connection conn = getConnection();
    private String table = "video";

    @Override
    public Optional<Video> findById(int id) {
        Optional<Video> optionalVideo = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Video video = new Video();
                video.setIdVideo(rs.getInt("id_video"));
                video.setTitulo(rs.getString("titulo"));
                video.setDescripcion(rs.getString("descripcion"));
                video.setLink(rs.getString("link"));
                video.setTipo(rs.getString("tipo"));
                video.setPrecio(rs.getBigDecimal("precio"));
                video.setIdCanal(rs.getInt("id_canal"));
                optionalVideo = Optional.of(video);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalVideo;
    }

    @Override
    public List<Video> findAll() {
        List<Video> videoList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Video video = new Video();
                video.setIdVideo(rs.getInt("id_video"));
                video.setTitulo(rs.getString("titulo"));
                video.setDescripcion(rs.getString("descripcion"));
                video.setLink(rs.getString("link"));
                video.setTipo(rs.getString("tipo"));
                video.setPrecio(rs.getBigDecimal("precio"));
                video.setIdCanal(rs.getInt("id_canal"));
                videoList.add(video);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return videoList;
    }

    @Override
    public boolean save(Video record) {
        String query = "insert into " + table + " (titulo, descripcion, link, tipo, precio, id_canal) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getTitulo());
            ps.setString(2, record.getDescripcion());
            ps.setString(3, record.getLink());
            ps.setString(4, record.getTipo());
            ps.setBigDecimal(5, record.getPrecio());
            ps.setInt(6, record.getIdCanal());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Video record) {
        String query = "update " + table + " set titulo=?, descripcion=?, link=?, tipo=?, precio=?, id_canal=? where id_" + table + " = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getTitulo());
            ps.setString(2, record.getDescripcion());
            ps.setString(3, record.getLink());
            ps.setString(4, record.getTipo());
            ps.setBigDecimal(5, record.getPrecio());
            ps.setInt(6, record.getIdCanal());
            ps.setInt(7, record.getIdVideo());
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

    public List<Video> masVistos(int id){

        List<Video> videoList = new ArrayList<>();
        String query = "SELECT v.* " +
                "FROM video AS v " +
                "JOIN ( " +
                "    SELECT id_video " +
                "    FROM usuario_ver_video " +
                "    WHERE id_usuario = ? " +
                "    GROUP BY id_video " +
                "    ORDER BY COUNT(*) DESC " +
                "    LIMIT 10 " +
                ") AS subquery ON v.id_video = subquery.id_video;";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Video video = new Video();
                video.setIdVideo(rs.getInt("id_video"));
                video.setTitulo(rs.getString("titulo"));
                video.setDescripcion(rs.getString("descripcion"));
                video.setLink(rs.getString("link"));
                video.setTipo(rs.getString("tipo"));
                video.setPrecio(rs.getBigDecimal("precio"));
                video.setIdCanal(rs.getInt("id_canal"));
                videoList.add(video);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return videoList;

    }


    public List<Video> mejorEvaluados(){

        List<Video> videoList = new ArrayList<>();
        String query = "SELECT v.* " +
                "FROM video AS v " +
                "JOIN ( " +
                "    SELECT id_video, COUNT(*) AS views_count " +
                "    FROM usuario_ver_video " +
                "    WHERE status = 'FAVORITOS' " +
                "    GROUP BY id_video " +
                "    ORDER BY views_count DESC " +
                "    LIMIT 10 " +
                ") AS subquery ON v.id_video = subquery.id_video;";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Video video = new Video();
                video.setIdVideo(rs.getInt("id_video"));
                video.setTitulo(rs.getString("titulo"));
                video.setDescripcion(rs.getString("descripcion"));
                video.setLink(rs.getString("link"));
                video.setTipo(rs.getString("tipo"));
                video.setPrecio(rs.getBigDecimal("precio"));
                video.setIdCanal(rs.getInt("id_canal"));
                videoList.add(video);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return videoList;

    }


    public List<EstadoCuenta> estadoCuenta(int id){

        List<EstadoCuenta> estadoCuentaList = new ArrayList<>();
        String query = "SELECT v.*, " +
                "       (SELECT SUM(v2.precio) " +
                "        FROM video AS v2 " +
                "        INNER JOIN usuario_buy_video AS ubv2 ON v2.id_video = ubv2.id_video " +
                "        WHERE ubv2.id_usuario = ? AND v2.tipo = 'PAID') AS total_price " +
                "FROM video AS v " +
                "INNER JOIN usuario_buy_video AS ubv ON v.id_video = ubv.id_video " +
                "WHERE ubv.id_usuario = ? AND v.tipo='PAID';";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EstadoCuenta estadoCuenta = new EstadoCuenta();
                estadoCuenta.setIdVideo(rs.getInt("id_video"));
                estadoCuenta.setTitulo(rs.getString("titulo"));
                estadoCuenta.setDescripcion(rs.getString("descripcion"));
                estadoCuenta.setTipo(rs.getString("tipo"));
                estadoCuenta.setPrecio(rs.getBigDecimal("precio"));
                estadoCuenta.setPrecioTotal(rs.getBigDecimal("total_price"));
                estadoCuentaList.add(estadoCuenta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estadoCuentaList;

    }


}
