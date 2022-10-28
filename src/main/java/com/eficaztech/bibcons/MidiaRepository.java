package com.eficaztech.bibcons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MidiaRepository extends JpaRepository<Midia, Integer> {
    @Query(value = """
    select l.midiaId, l.titulo, l.subtitulo, l.descricao, 
    (select count(*) from ExemplarMidia el where midiaId = l.midiaId and ativo = 'S' and clienteId is null) as disponiveis 
    from Midia l 
    where (upper(l.titulo) like :filtro or upper(l.subtitulo) like :filtro or upper(l.descricao) like :filtro) 
    and (l.ativo = 'S') 
    order by l.titulo""", nativeQuery = true)
    List<Midia> filtrar(String filtro);
}
