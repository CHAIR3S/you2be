package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
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
}
