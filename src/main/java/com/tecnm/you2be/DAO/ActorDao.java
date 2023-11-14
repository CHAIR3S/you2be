package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Actor;
import com.tecnm.you2be.models.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ActorDao extends MySQLConnection implements Dao<Actor> {
    Connection conn = getConnection();


    @Override
    public Optional<Actor> findById(int id) {
        Optional<Actor> optionalActor = Optional.empty();
        String query = "select * from actor where id_actor = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() )
            {
                Actor actor = new Actor();
                actor.setIdActor(rs.getInt("id_actor"));
                actor.setNombre(rs.getString("nombre"));
                actor.setPrimerApellido(rs.getString("primer_apellido"));
                actor.setSegundoApellido(rs.getString("segundo_apellido"));
                actor.setIdRol(rs.getInt("id_rol"));
                optionalActor = Optional.of(actor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalActor;
    }

    @Override
    public List<Actor> findAll() {
        return null;
    }

    @Override
    public boolean save(Actor record) {
        return false;
    }

    @Override
    public boolean update(Actor record) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
