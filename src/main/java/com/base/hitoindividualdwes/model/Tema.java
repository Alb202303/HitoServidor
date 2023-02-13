package com.base.hitoindividualdwes.model;

import jakarta.persistence.*;

@Entity
    @NamedQuery(name="Tema.findAll", query="select t from Tema t ")
    public class Tema {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "idtema", nullable = false)
        private Integer idtema;

        @Column(name="titulo")
        private String titulo;

        @Column(name="descripcion")
        private String descripcion;

        @ManyToOne
        @JoinColumn(name="username")
        private Users username;

        public Tema() {
        }

    public Tema(Integer idtema, String titulo, String descripcion, Users username) {
        this.idtema = idtema;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.username = username;
    }

    public Integer getIdtema() {
            return idtema;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDescripcion() {
            return descripcion;
        }


        public void setIdtema(Integer idtema) {
            this.idtema = idtema;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }
}
