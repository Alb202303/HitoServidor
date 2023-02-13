package com.base.hitoindividualdwes.Tema;

import com.base.hitoindividualdwes.model.Tema;
import com.base.hitoindividualdwes.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Integer> {
    @Query("SELECT t FROM Tema t WHERE t.idtema = :idtema")
    public Tema findTemaById(@Param("idtema") Integer idtema);

@Query("SELECT t FROM Tema t WHERE t.username=:username")
    public Tema findTemaByUsername(@Param("username") Users users);


}





