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
    public ModelAndView index(Model model, String filtro) {
        return x(filtro);
    }

    @GetMapping("p")
    public ModelAndView get(Model model, String filtro) {
        return x(filtro);
    }

    @PostMapping("p")
    public ModelAndView post(Model model, String filtro) {
        return x(filtro);
    }

    private ModelAndView x(String filtro) {
        filtro = filtro == null || "".equals(filtro.trim()) ? "" : filtro.trim();
        val padrao = "###XYZ###";
        val livros = filtro.equals("") ? livroRepository.filtrar(padrao) : livroRepository.filtrar("%"+filtro.toUpperCase()+"%");
        val monografias = filtro.equals("") ? monografiaRepository.filtrar(padrao) : monografiaRepository.filtrar("%"+filtro.toUpperCase()+"%");
        val midias = filtro.equals("") ? midiaRepository.filtrar(padrao) : midiaRepository.filtrar("%"+filtro.toUpperCase()+"%");
        val artigos = filtro.equals("") ? edicaoRepository.filtrar(padrao) : edicaoRepository.filtrar("%"+filtro.toUpperCase()+"%");
        val mv = new ModelAndView("index");
        mv.addObject("versao", versao);
        mv.addObject("filtro", filtro);
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
