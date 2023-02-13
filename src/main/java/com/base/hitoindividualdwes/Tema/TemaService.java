package com.base.hitoindividualdwes.Tema;

import com.base.hitoindividualdwes.model.Tema;
import com.base.hitoindividualdwes.model.Users;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class TemaService {

    private TemaRepository temas;


    public TemaService(TemaRepository temas){
        this.temas=temas;
    }

    public List<Tema> listaTemas() {
        return temas.findAll();
    }

    public Tema buscarTema(Integer idtema) {return temas.findTemaById(idtema);}

public Tema buscarMisTemas(Users username){return temas.findTemaByUsername(username);}


    public void guardarTema(Tema tema) {
        temas.save(tema);
    }

        public void borrarTema(Integer idtema){
        temas.deleteById(idtema);
            }


}
