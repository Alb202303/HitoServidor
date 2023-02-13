package com.base.hitoindividualdwes.Usuario;

import com.base.hitoindividualdwes.model.Authorities;
import com.base.hitoindividualdwes.model.Comentario;
import com.base.hitoindividualdwes.model.Tema;
import com.base.hitoindividualdwes.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Users, String> {

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    public Users findUsersByName(@Param("username") String username);

    @Query("SELECT u FROM Authorities u WHERE u.username= :username")
    public List <Users> deleteUsersBy(@Param("username")Users username);


}

