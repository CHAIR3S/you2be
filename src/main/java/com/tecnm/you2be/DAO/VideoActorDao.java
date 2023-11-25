package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.VideoActor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoActorDao extends MySQLConnection implements Dao<VideoActor> {

    Connection conn = getConnection();
    private String table = "video_actor";

    @Override
    public Optional<VideoActor> findById(int id) {
        Optional<VideoActor> optionalVideoActor = Optional.empty();
        String query = "select * from " + table + " where id_video_actor = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                VideoActor videoActor = new VideoActor();
                videoActor.setIdVideoActor(rs.getInt("id_video_actor"));
                videoActor.setIdVideo(rs.getInt("id_video"));
                videoActor.setIdActor(rs.getInt("id_actor"));
                optionalVideoActor = Optional.of(videoActor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalVideoActor;
    }

    @Override
    public List<VideoActor> findAll() {
        List<VideoActor> videoActorList = new ArrayList<>();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                VideoActor videoActor = new VideoActor();
                videoActor.setIdVideoActor(rs.getInt("id_video_actor")); // Assuming there's a unique ID 'id_video_actor'
                videoActor.setIdVideo(rs.getInt("id_video"));
                videoActor.setIdActor(rs.getInt("id_actor"));
                videoActorList.add(videoActor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return videoActorList;
    }

    @Override
    public boolean save(VideoActor record) {
        String query = "insert into " + table + " (id_video, id_actor) values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, record.getIdVideo());
            ps.setInt(2, record.getIdActor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(VideoActor record) {
        String query = "update " + table + " set id_video=?, id_actor=? where id_video_actor = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, record.getIdVideo());
            ps.setInt(2, record.getIdActor());
            ps.setInt(3, record.getIdVideoActor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from " + table + " where id_video_actor = ?";
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
