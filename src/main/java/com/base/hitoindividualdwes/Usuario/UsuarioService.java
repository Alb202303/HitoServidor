package com.base.hitoindividualdwes.Usuario;

import com.base.hitoindividualdwes.model.Authorities;
import com.base.hitoindividualdwes.model.Comentario;
import com.base.hitoindividualdwes.model.Tema;
import com.base.hitoindividualdwes.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class UsuarioService {

    @Autowired
    private DataSource dataSource;
    private UsuarioRepository usuarios;

    public UsuarioService(UsuarioRepository usuarios) {
        this.usuarios = usuarios;
    }

    public Users buscarUsuario(String username) {return usuarios.findUsersByName(username);}

    public Optional<Users> buscarUser(String username){
        return usuarios.findById(username);
    }


    public void guardarUsuario(Users user) {
        usuarios.save(user);
    }

    public List<Users> listaUsuarios() {
        return usuarios.findAll();
    }

public void eliminarUsuario(String username){
        usuarios.deleteById(username);
}




    public void assignRole(String username, String role) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO authorities(username, authority) VALUES (?,?)", username, role);
    }

}
