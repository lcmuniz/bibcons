package com.eficaztech.bibcons;

public interface ArtigoEdicao {
    String getTitulo();
    String getSubtitulo();
    String getPagina();
    String getAutores();
    String getAssuntos();
    String getResumo();
    String getVolume();
    String getNumero();
    String getPeriodo();
    String getTituloPeriodico();
    String getSubtituloPeriodico();
    String getDisponiveis();

    default String getLabelDisponiveis() {
        if (getDisponiveis().equals("0")) return "Nenhum exemplar disponível";
        if (getDisponiveis().equals("1")) return "1 exemplar disponível";
        return getDisponiveis() + " exemplares disponíveis";
    }

}
