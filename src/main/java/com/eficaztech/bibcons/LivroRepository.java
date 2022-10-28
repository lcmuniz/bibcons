package com.eficaztech.bibcons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    @Query(value = """
    select l.livroId, l.titulo, l.subtitulo, l.autores, l.assuntos, l.classificacao, l.cutter, l.volume, l.ano, e.nome as editora, 
    (select count(*) from ExemplarLivro el where livroId = l.livroId and ativo = 'S' and clienteId is null) as disponiveis 
    from Livro l left join Editora e on e.editoraId = l.editoraId 
    where (upper(l.titulo) like :filtro or upper(l.subtitulo) like :filtro or upper(l.autores) like :filtro or upper(l.assuntos) like :filtro) 
    and (l.ativo = 'S') 
    order by l.titulo""", nativeQuery = true)
    List<Livro> filtrar(String filtro);
}
