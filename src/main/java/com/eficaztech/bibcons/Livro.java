package com.eficaztech.bibcons;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Livro")
public class Livro {
    @Id
    private Integer livroId;
    private String titulo;
    private String subtitulo;
    private String autores;
    private String assuntos;
    private String classificacao;
    private String cutter;
    private String editora;
    private String volume;
    private String ano;
    private String disponiveis;

    public String getDisponiveis() {
        if (disponiveis.equals("0")) return "Nenhum exemplar disponível";
        if (disponiveis.equals("1")) return "1 exemplar disponível";
        return disponiveis + " exemplares disponíveis";
    }

    public String getVolume() {
        return volume == null || "".equals(volume) ? "" : "Volume: " + volume;
    }
    public String getEditoraAno() {
        return editora == null || "".equals(editora) ? "" : "Editora: " + editora + ", " + ano;
    }
}
