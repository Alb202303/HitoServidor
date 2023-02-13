package com.base.hitoindividualdwes.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Entity
@NamedQuery(name="Comentario.findAll", query="Select C FROM Comentario C")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idcomentario", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer idcomentario;

    @ManyToOne
    @JoinColumn(name = "username")
    private Users username;

    @ManyToOne
    @JoinColumn(name="idtema")
    private Tema idtema;

    @Column(name = "texto")
    private String texto;

    public Comentario(Integer idcomentario, Users username, Tema idtema, String texto) {
        this.idcomentario = idcomentario;
        this.username = username;
        this.idtema = idtema;
        this.texto = texto;
    }

    public Comentario() {
    }
    public Integer getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(Integer idcomentario) {
        this.idcomentario = idcomentario;
    }




    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    public Tema getIdtema() {
        return idtema;
    }

    public void setIdtema(Tema idtema) {
        this.idtema = idtema;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
