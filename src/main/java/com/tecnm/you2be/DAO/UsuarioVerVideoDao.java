package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.CardVideo;
import com.tecnm.you2be.models.Usuario;
import com.tecnm.you2be.models.UsuarioVerVideo;
import com.tecnm.you2be.models.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioVerVideoDao extends MySQLConnection implements Dao<UsuarioVerVideo> {


    private Connection conn = getConnection();
    private String table = "usuario_ver_video";

    @Override
    public Optional<UsuarioVerVideo> findById(int id) {
        Optional<UsuarioVerVideo> optionalUsuarioVerVideo = Optional.empty();
        String query = "select * from " + table + " where id_usurio_video = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                UsuarioVerVideo usuarioVerVideo = new UsuarioVerVideo();
                usuarioVerVideo.setIdUsuarioVideo(rs.getInt("id_usurio_video"));
                usuarioVerVideo.setIdUsuario(rs.getInt("id_usuario"));
                usuarioVerVideo.setIdVideo(rs.getInt("id_video"));
                usuarioVerVideo.setStatus(rs.getString("status"));
                optionalUsuarioVerVideo = Optional.of(usuarioVerVideo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalUsuarioVerVideo;
    }

    @Override
    public List<UsuarioVerVideo> findAll() {
        List<UsuarioVerVideo> usuarioVerVideoList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                UsuarioVerVideo usuarioVerVideo = new UsuarioVerVideo();
                usuarioVerVideo.setIdUsuarioVideo(rs.getInt("id_usurio_video"));
                usuarioVerVideo.setIdUsuario(rs.getInt("id_usuario"));
                usuarioVerVideo.setIdVideo(rs.getInt("id_video"));
                usuarioVerVideo.setStatus(rs.getString("status"));
                usuarioVerVideoList.add(usuarioVerVideo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarioVerVideoList;
    }

    @Override
    public boolean save(UsuarioVerVideo record) {
        String query = "insert into " + table + " (id_usuario, id_video, status) values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, record.getIdUsuario());
            ps.setInt(2, record.getIdVideo());
            ps.setString(3, record.getStatus());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean update(UsuarioVerVideo record) {

        String query = "update " + table + " set id_usuario=?, id_video=?, status=? where id_usurio_video = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, record.getIdUsuario());
            ps.setInt(2, record.getIdVideo());
            ps.setString(3, record.getStatus());
            ps.setInt(4, record.getIdUsuarioVideo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from " + table + " where id_usurio_video = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUpdate(Usuario usr, CardVideo vd, UsuarioVerVideo record){
        String query = "select uvv.id_usurio_video from usuario_ver_video uvv where uvv.id_usuario = ? and uvv.id_video = ?";

        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, usr.getIdUsuario());
            ps.setInt(2, vd.getIdVideo());

            if( ps.execute() ){
                update(record);
            }
            else save(record);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
