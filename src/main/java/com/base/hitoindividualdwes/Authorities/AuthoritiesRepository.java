package com.base.hitoindividualdwes.Authorities;

import com.base.hitoindividualdwes.model.Authorities;
import com.base.hitoindividualdwes.model.Comentario;
import com.base.hitoindividualdwes.model.Tema;
import com.base.hitoindividualdwes.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

    @Query("SELECT a FROM Authorities a WHERE a.username= :username")
    public List<Authorities> borrarU(@Param("username") Users users
    );
}
