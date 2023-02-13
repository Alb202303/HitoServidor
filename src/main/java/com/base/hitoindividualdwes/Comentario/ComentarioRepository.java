package com.base.hitoindividualdwes.Comentario;

import com.base.hitoindividualdwes.model.Comentario;
import com.base.hitoindividualdwes.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    @Query("SELECT c FROM Comentario c WHERE c.idtema=?1")
    public List <Comentario> mostrarComentarios(@Param("tema")Tema tema);

    @Query("SELECT c FROM Comentario c WHERE c.idtema=?1")
    public List <Comentario> borrar(@Param("tema")Tema tema);
}
