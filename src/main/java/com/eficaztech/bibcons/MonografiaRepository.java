package com.eficaztech.bibcons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonografiaRepository extends JpaRepository<Monografia, Integer> {
    @Query(value = """
    select l.monografiaId, l.titulo, l.subtitulo, l.autores, l.assuntos, l.classificacao, l.cutter, l.volume, l.ano, e.nome as editora, 
    (select count(*) from ExemplarMonografia el where monografiaId = l.monografiaId and ativo = 'S' and clienteId is null) as disponiveis 
    from Monografia l left join Editora e on e.editoraId = l.editoraId 
    where (upper(l.titulo) like :filtro or upper(l.subtitulo) like :filtro or upper(l.autores) like :filtro or upper(l.assuntos) like :filtro) 
    and (l.ativo = 'S') 
    order by l.titulo""", nativeQuery = true)
    List<Monografia> filtrar(String filtro);
}
