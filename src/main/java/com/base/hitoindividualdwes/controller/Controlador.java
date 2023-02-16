package com.base.hitoindividualdwes.controller;

import com.base.hitoindividualdwes.Authorities.AuthoritiesService;
import com.base.hitoindividualdwes.Comentario.ComentarioService;
import com.base.hitoindividualdwes.Tema.TemaService;
import com.base.hitoindividualdwes.Usuario.UsuarioService;
import com.base.hitoindividualdwes.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class Controlador {

private TemaService temaService;

private ComentarioService comentarioService;

private UsuarioService usuarioService;

private AuthoritiesService authoritiesService;

@Autowired
private PasswordEncoder passwordEncoder;



public Controlador(TemaService temaService, ComentarioService comentarioService, UsuarioService usuarioService, AuthoritiesService authoritiesService){
    this.temaService=temaService;
    this.comentarioService=comentarioService;
    this.usuarioService=usuarioService;
    this.authoritiesService=authoritiesService;
}



    @RequestMapping("/")
    public ModelAndView peticionRaiz(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        List<Tema> temas=temaService.listaTemas();
        mv.addObject("listadotemas", temaService.listaTemas());

        if (aut!=null){
            mv.addObject("user", "¡Hola "+aut.getName()+"!");
        }else{
            mv.addObject("user", "No has iniciado sesión");

        }

        if (temas.isEmpty()){
            mv.addObject("checkTemas", "No hay ningún tema publicado");
        }else{
            mv.addObject("checkTemas", "Temas publicados:");
        }
        mv.setViewName("inicio");
        return mv;
    }


@RequestMapping("/admin")
public ModelAndView adminPanel(){
    ModelAndView mv=new ModelAndView();
    mv.addObject("listadotemas", temaService.listaTemas());
    mv.addObject("listadousuarios", usuarioService.listaUsuarios());

    mv.setViewName("admin");
    return mv;

}

    @RequestMapping("/eliminar/tema/{temaid}")
    public ModelAndView eliminarTema(@PathVariable("temaid") int idtema) {
        ModelAndView mv=new ModelAndView();
        Tema tema = temaService.buscarTema(idtema);
        comentarioService.borrarComentarios(tema);

        temaService.borrarTema(idtema);
        mv.addObject("eliminado", "El tema se ha eliminado correctamente");

        mv.setViewName("temaeliminado");
        return mv;
    }

    @RequestMapping("/eliminar/usuarios/{usernombre}")
    public ModelAndView eliminarTema(@PathVariable("usernombre") String username) {
        ModelAndView mv=new ModelAndView();
        Users user=usuarioService.buscarUsuario(username);

        authoritiesService.borrarU(user);
        usuarioService.eliminarUsuario(username);
        mv.addObject("eliminado", "El usuario se ha eliminado correctamente");
        mv.setViewName("temaeliminado");

        return mv;
    }


    @RequestMapping("/login")
    public ModelAndView peticionSesion(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut!=null){
            mv.addObject("aviso", "Ya has iniciado sesión. Eres "+aut.getName());
            mv.setViewName("errorlogout");
        }else{
            mv.setViewName("login");
        }

        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView peticionRegistro() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("login", new Users());
        mv.setViewName("registro");
        return mv;
    }

    @RequestMapping("/exitologin")
    public ModelAndView exitologin(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("aviso", "¡Bienvenido de vuelta, "+aut.getName()+"!");
        mv.setViewName("exitologin");
        return mv;
    }

    @RequestMapping("/exitologout")
    public ModelAndView exitologout(Authentication aut) {
        ModelAndView mv = new ModelAndView();

            mv.addObject("aviso", "Te echaremos de menos ¡Vuelve pronto!");
            mv.setViewName("exitologout");

        return mv;
    }

    @RequestMapping("/login?error=true")
    public ModelAndView errorlogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("errorlogin");
        return mv;
    }
    @RequestMapping("/registro_completado")
    public ModelAndView peticionGuardar(Users user) {
        ModelAndView mv = new ModelAndView();
        Optional<Users> usuarioBuscado = usuarioService.buscarUser(user.getUsername());

        if (usuarioBuscado.isPresent()) {
            mv.addObject("aviso", "Este usuario ya está registrado");
            mv.setViewName("errorRegister");

        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(1);
            usuarioService.guardarUsuario(user);
            usuarioService.assignRole(user.getUsername(), "USER");
            mv.addObject("aviso", "¡Bienvenido " + user.getUsername() + "! Ya puedes iniciar sesión");


            mv.setViewName("registro_completado");


        }
        return mv;
    }



    @RequestMapping("/denegado")
    public ModelAndView Denegado(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("denegado");
        return mv;
    }

    @RequestMapping("/creartema")
    public ModelAndView addTema(Authentication aut){
    ModelAndView mv=new ModelAndView();

    Tema t=new Tema();
    t.setTitulo("");
    t.setDescripcion("");

    mv.addObject("tema", t);
    mv.setViewName("creartema");

    return mv;
    }

    @RequestMapping("/temaguardado")
    public ModelAndView peticionañadida(Tema t, Authentication aut){
        ModelAndView mv=new ModelAndView();
        String username=aut.getName();

        Users user= usuarioService.buscarUsuario(username);

            t.setUsername(user);
            temaService.guardarTema(t);
            mv.setViewName("/exitotema");

            return mv;



    }

    @RequestMapping("/comentar")
    public ModelAndView comentar(Comentario c){
        ModelAndView mv=new ModelAndView();
        c.setTexto("");
        mv.setViewName("tema");
        return mv;
    }

    @RequestMapping("/comentarioguardado/tema/{temaid}")
    public ModelAndView comentarioañadido(Comentario c, Authentication aut, @PathVariable("temaid") Integer idtema){
        ModelAndView mv=new ModelAndView();
        String username=aut.getName();
        Tema tema = temaService.buscarTema(idtema);
        Users user= usuarioService.buscarUsuario(username);

        c.setIdtema(tema);
        c.setUsername(user);

        comentarioService.guardarComentario(c);

        mv.setViewName("comentarioguardado");
        return mv;
    }

    @GetMapping("/vertema/tema/{temaid}")
    public ModelAndView verTema(@PathVariable("temaid") Integer idtema) {
        ModelAndView mv=new ModelAndView();
        Tema tema = temaService.buscarTema(idtema);
        List<Comentario> comentarios=comentarioService.listaComentarios(tema);
        mv.addObject("tema", tema);
        mv.addObject("listacomentarios", comentarios);
        mv.addObject("comentario", new Comentario());

        mv.setViewName("tema");
        return mv;
    }







    @RequestMapping("/perfil")
    public ModelAndView mostrarPerfil(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        String username = aut.getName();
        Users user = usuarioService.buscarUsuario(username);
        Tema tema = temaService.buscarMisTemas(user);

        if (aut == null) {
            mv.setViewName("denegado");
        } else {

            mv.addObject("temas", tema);

            mv.addObject("user", aut.getName());
            mv.setViewName("perfil");
        }


        return mv;
    }


}
