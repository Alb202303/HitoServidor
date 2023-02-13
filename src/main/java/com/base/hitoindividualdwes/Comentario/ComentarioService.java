package com.base.hitoindividualdwes.Comentario;

import com.base.hitoindividualdwes.model.Comentario;
import com.base.hitoindividualdwes.model.Tema;
import com.base.hitoindividualdwes.model.Users;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class ComentarioService {

    private ComentarioRepository comentarios;

    public ComentarioService(ComentarioRepository comentarios) {
        this.comentarios = comentarios;
    }

    //public Optional<Comentario> listaComentarios(Integer idtema) {
       // return comentarios.findById(idtema);
    //}
    public List <Comentario> listaComentarios(Tema tema) {return comentarios.mostrarComentarios(tema);}



    public void guardarComentario(Comentario comentario) {
        comentarios.save(comentario);
    }

    //public void borrarComentarios(Integer idtema){
        //comentarios.deleteById(idtema);
    //}

    public void borrarComentarios(Tema tema) {
        List<Comentario> comentario = comentarios.borrar(tema);
        comentarios.deleteAll(comentario);
    }
}
