package com.eficaztech.bibcons;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Midia")
public class Midia {
    @Id
    private Integer midiaId;
    private String titulo;
    private String subtitulo;
    private String descricao;
    private String disponiveis;

    public String getDisponiveis() {
        if (disponiveis.equals("0")) return "Nenhum exemplar disponível";
        if (disponiveis.equals("1")) return "1 exemplar disponível";
        return disponiveis + " exemplares disponíveis";
    }

}
