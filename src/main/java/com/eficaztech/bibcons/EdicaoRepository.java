package com.eficaztech.bibcons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EdicaoRepository extends JpaRepository<Edicao, Integer> {

    @Query(value = """
    select distinct e.edicaoId, ae.titulo, ae.subtitulo, ae.pagina, ae.autores, ae.assuntos, ae.resumo,
    e.volume, e.numero, e.periodo, p.titulo as tituloPeriodico, p.subtitulo as subtituloPeriodico,
    (select count(*) from ExemplarEdicao el where edicaoId = e.edicaoId and clienteId is null) as disponiveis
    from ArtigoEdicao ae
    left join Edicao e on e.edicaoId = ae.edicaoId
    left join Periodico p on p.periodicoId = e.periodicoId
    where (upper(ae.titulo) like :filtro or upper(ae.subtitulo) like :filtro) and (e.ativo = 'S') order by ae.titulo""", nativeQuery = true)
    List<ArtigoEdicao> filtrarPorTitulo(String filtro);

    @Query(value = """
    select distinct e.edicaoId, ae.titulo, ae.subtitulo, ae.pagina, ae.autores, ae.assuntos, ae.resumo,
    e.volume, e.numero, e.periodo, p.titulo as tituloPeriodico, p.subtitulo as subtituloPeriodico,
    (select count(*) from ExemplarEdicao el where edicaoId = e.edicaoId and clienteId is null) as disponiveis
    from ArtigoEdicao ae
    left join Edicao e on e.edicaoId = ae.edicaoId
    left join Periodico p on p.periodicoId = e.periodicoId
    where (upper(ae.autores) like :filtro) and (e.ativo = 'S') order by ae.titulo""", nativeQuery = true)
    List<ArtigoEdicao> filtrarPorAutor(String filtro);

    @Query(value = """
    select distinct e.edicaoId, ae.titulo, ae.subtitulo, ae.pagina, ae.autores, ae.assuntos, ae.resumo,
    e.volume, e.numero, e.periodo, p.titulo as tituloPeriodico, p.subtitulo as subtituloPeriodico,
    (select count(*) from ExemplarEdicao el where edicaoId = e.edicaoId and clienteId is null) as disponiveis
    from ArtigoEdicao ae
    left join Edicao e on e.edicaoId = ae.edicaoId
    left join Periodico p on p.periodicoId = e.periodicoId
    where (upper(ae.assuntos) like :filtro or upper(ae.resumo) like :filtro) and (e.ativo = 'S') order by ae.titulo""", nativeQuery = true)
    List<ArtigoEdicao> filtrarPorAssunto(String filtro);

}
