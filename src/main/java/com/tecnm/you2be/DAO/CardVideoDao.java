package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Canal;
import com.tecnm.you2be.models.CardVideo;
import com.tecnm.you2be.models.Usuario;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class CardVideoDao extends MySQLConnection implements Dao<CardVideo> {
    Connection conn = getConnection();


    @Override
    public Optional<CardVideo> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<CardVideo> findAll() {
        List<CardVideo> lisVideo = FXCollections.observableArrayList();
        String query = "select * from video";

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                CardVideo cd = new CardVideo();

                cd.setIdVideo(rs.getInt("id_video"));
                cd.setTipo(rs.getString("tipo"));
                String[] links = rs.getString("link").split(" ");

                cd.setLinkVideo(links[0]);
                cd.setLinkImage(links[1]);


                cd.setTitulo(rs.getString("titulo"));
                cd.setDescripcion(rs.getString("descripcion"));
                cd.setPrecio(rs.getDouble("precio"));

                lisVideo.add(cd);
            }


            return lisVideo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(CardVideo record) {
        return false;
    }

    @Override
    public boolean update(CardVideo record) {
        return false;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }



    public List<CardVideo> getAllMyVideos(Usuario usuario, String textInVideo) throws SQLException {

        List<CardVideo> listVideos = FXCollections.observableArrayList();

        String queryUser = "select link, tipo, descripcion, v.id_video, titulo, nombre, precio from canal c join video v " +
                "on v.id_canal = c.id_canal where c.id_usuario = " + usuario.getIdUsuario() + "and v.titulo like '%" + textInVideo + "%'";

        Statement statement = conn.createStatement();
        ResultSet rsu = statement.executeQuery(queryUser);

        while (rsu.next()) {

            CardVideo cd = new CardVideo();

            System.out.println("valio verga la query de los videos");
            cd.setIdVideo(rsu.getInt("id_video"));
            cd.setTipo(rsu.getString("tipo"));
            String[] links = rsu.getString("link").split(" ");

            cd.setLinkVideo(links[0]);
            cd.setLinkImage(links[1]);


            cd.setTitulo(rsu.getString("titulo"));
            cd.setDescripcion(rsu.getString("descripcion"));
            cd.setPrecio(rsu.getDouble("precio"));
            cd.setCanal(rsu.getString("nombre"));

            listVideos.add(cd);
        }

        return listVideos;
    }

    public List<CardVideo> getAllMyVideos(Usuario usuario ) throws SQLException {

        List<CardVideo> listVideos = FXCollections.observableArrayList();

        String queryUser = "select link, tipo, descripcion, v.id_video, titulo, nombre, precio from canal c join video v " +
                "on v.id_canal = c.id_canal where c.id_usuario = " + usuario.getIdUsuario();

        Statement statement = conn.createStatement();
        ResultSet rsu = statement.executeQuery(queryUser);

        while (rsu.next()) {

            CardVideo cd = new CardVideo();

            System.out.println("valio verga la query de los videos");
            cd.setIdVideo(rsu.getInt("id_video"));
            cd.setTipo(rsu.getString("tipo"));
            String[] links = rsu.getString("link").split(" ");

            cd.setLinkVideo(links[0]);
            cd.setLinkImage(links[1]);


            cd.setTitulo(rsu.getString("titulo"));
            cd.setDescripcion(rsu.getString("descripcion"));
            cd.setPrecio(rsu.getDouble("precio"));
            cd.setCanal(rsu.getString("nombre"));

            listVideos.add(cd);
        }

        return listVideos;
    }

    public List<CardVideo> getAllMyFavoriteVideos(Usuario usuario) throws SQLException {
        List<CardVideo> listVideos = FXCollections.observableArrayList();

        String queryUser = "select v.id_video, v.tipo, v.link, v.titulo, v.descripcion, v.precio, c.nombre  " +
                "from usuario_ver_video uvv join video v on v.id_video = uvv.id_video " +
                "join canal c on c.id_usuario = "+ usuario.getIdUsuario() +
                " where uvv.id_usuario = "+usuario.getIdUsuario()+" and status = 'favoritos'";

        Statement statement = conn.createStatement();
        ResultSet rsu = statement.executeQuery(queryUser);

        while( rsu.next() ){



            CardVideo cd = new CardVideo();

            cd.setIdVideo(rsu.getInt("id_video"));
            cd.setTipo(rsu.getString("tipo"));
            String[] links = rsu.getString("link").split(" ");

            cd.setLinkVideo(links[0]);
            cd.setLinkImage(links[1]);


            cd.setTitulo(rsu.getString("titulo"));
            cd.setDescripcion(rsu.getString("descripcion"));
            cd.setPrecio(rsu.getDouble("precio"));
            cd.setCanal(rsu.getString("nombre"));

            listVideos.add(cd);


        }
        return listVideos;
    }
}
