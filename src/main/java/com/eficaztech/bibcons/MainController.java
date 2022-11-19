package com.eficaztech.bibcons;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Value("${app.versao}")
    private String versao;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private MonografiaRepository monografiaRepository;

    @Autowired
    private MidiaRepository midiaRepository;

    @Autowired
    private EdicaoRepository edicaoRepository;

    @GetMapping
    public ModelAndView index(Model model, String filtro, String tipo) {
        return filtrar(filtro, "Título");
    }

    @GetMapping("p")
    public ModelAndView get(Model model, String filtro, String tipo) {
        return filtrar(filtro, "Título");
    }

    @PostMapping("p")
    public ModelAndView post(Model model, String filtro, String tipo) {
        return filtrar(filtro, tipo);
    }

    private ModelAndView filtrar(String filtro, String tipo) {
        filtro = filtro == null || "".equals(filtro.trim()) ? "" : filtro.trim();
        val padrao = "###XYZ###";

        List<Livro> livros = new ArrayList<Livro>();
        List<Monografia> monografias = new ArrayList<Monografia>();
        List<Midia> midias = new ArrayList<Midia>();
        List<ArtigoEdicao> artigos =new ArrayList<ArtigoEdicao>();

        if (!filtro.equals("")) {

            // por titulo
            if (tipo.equals("Título")) {

                livros = livroRepository.filtrarPorTitulo("%"+filtro.toUpperCase()+"%");
                monografias = monografiaRepository.filtrarPorTitulo("%"+filtro.toUpperCase()+"%");
                midias = midiaRepository.filtrarPorTitulo("%"+filtro.toUpperCase()+"%");
                artigos =edicaoRepository.filtrarPorTitulo("%"+filtro.toUpperCase()+"%");

            }

            // por autor
            if (tipo.equals("Autor")) {

                livros = livroRepository.filtrarPorAutor("%"+filtro.toUpperCase()+"%");
                monografias = monografiaRepository.filtrarPorAutor("%"+filtro.toUpperCase()+"%");
                artigos =edicaoRepository.filtrarPorAutor("%"+filtro.toUpperCase()+"%");

            }

            // por titulo
            if (tipo.equals("Assunto")) {

                livros = livroRepository.filtrarPorAssunto("%"+filtro.toUpperCase()+"%");
                monografias = monografiaRepository.filtrarPorAssunto("%"+filtro.toUpperCase()+"%");
                midias = midiaRepository.filtrarPorAssunto("%"+filtro.toUpperCase()+"%");
                artigos =edicaoRepository.filtrarPorAssunto("%"+filtro.toUpperCase()+"%");

            }

        }

        val mv = new ModelAndView("index");
        mv.addObject("versao", versao);
        mv.addObject("filtro", filtro);
        mv.addObject("tipo", tipo);
        mv.addObject("ehTitulo", tipo.equals("Título"));
        mv.addObject("ehAutor", tipo.equals("Autor"));
        mv.addObject("ehAssunto", tipo.equals("Assunto"));
        mv.addObject("livros", livros);
        mv.addObject("temLivros", livros.size() > 0);
        mv.addObject("naoTemLivros", livros.size() == 0 && !filtro.equals(""));
        mv.addObject("monografias", monografias);
        mv.addObject("temMonografias", monografias.size() > 0);
        mv.addObject("naoTemMonografias", monografias.size() == 0 && !filtro.equals(""));
        mv.addObject("artigos", artigos);
        mv.addObject("temArtigos", artigos.size() > 0);
        mv.addObject("naoTemArtigos", artigos.size() == 0 && !filtro.equals(""));
        mv.addObject("midias", midias);
        mv.addObject("temMidias", midias.size() > 0);
        mv.addObject("naoTemMidias", midias.size() == 0 && !filtro.equals(""));
        return mv;
    }

}
