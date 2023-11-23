package com.tecnm.you2be.DAO;

import com.tecnm.you2be.connection.MySQLConnection;
import com.tecnm.you2be.models.Usuario;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsuarioDao extends MySQLConnection implements Dao<Usuario> {

    Connection conn = getConnection();

    private String table = "usuario";

    @Override
    public Optional<Usuario> findById(int id) {
        Optional<Usuario> optionalUsuario = Optional.empty();
        String query = "select * from " + table + " where id_" + table + " = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPrimerApellido(rs.getString("primer_apellido"));
                usuario.setSegundoApellido(rs.getString("segundo_apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNacimiento(rs.getDate("nacimiento"));
                optionalUsuario = Optional.of(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalUsuario;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarioList = FXCollections.observableArrayList();
        String query = "select * from " + table;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPrimerApellido(rs.getString("primer_apellido"));
                usuario.setSegundoApellido(rs.getString("segundo_apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNacimiento(rs.getDate("nacimiento"));
                usuarioList.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarioList;
    }

    @Override
    public boolean save(Usuario record) {
        String query = "insert into " + table + " (nombre, primer_apellido, segundo_apellido, email, password, nacimiento) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNombre());
            ps.setString(2, record.getPrimerApellido());
            ps.setString(3, record.getSegundoApellido());
            ps.setString(4, record.getEmail());
            ps.setString(5, record.getPassword());
            ps.setDate(6, new java.sql.Date(record.getNacimiento().getTime()));
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Usuario record) {
        String query = "update " + table + " set nombre=?, primer_apellido=?, segundo_apellido=?, email=?, password=?, nacimiento=? where id_" + table + " = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, record.getNombre());
            ps.setString(2, record.getPrimerApellido());
            ps.setString(3, record.getSegundoApellido());
            ps.setString(4, record.getEmail());
            ps.setString(5, record.getPassword());
            ps.setDate(6, new java.sql.Date(record.getNacimiento().getTime()));
            ps.setInt(7, record.getIdUsuario());
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
