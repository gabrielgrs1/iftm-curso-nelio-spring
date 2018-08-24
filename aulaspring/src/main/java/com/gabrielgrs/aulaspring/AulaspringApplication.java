package com.gabrielgrs.aulaspring;

import com.gabrielgrs.aulaspring.domain.Categoria;
import com.gabrielgrs.aulaspring.domain.Cidade;
import com.gabrielgrs.aulaspring.domain.Estado;
import com.gabrielgrs.aulaspring.domain.Produto;
import com.gabrielgrs.aulaspring.repositories.CategoriaRepository;
import com.gabrielgrs.aulaspring.repositories.CidadeRepository;
import com.gabrielgrs.aulaspring.repositories.EstadoRepository;
import com.gabrielgrs.aulaspring.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class AulaspringApplication implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    @Autowired
    public AulaspringApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(AulaspringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.0);
        Produto p2 = new Produto(null, "Impressora", 800.0);
        Produto p3 = new Produto(null, "Mouse", 80.0);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Collections.singletonList(p2));

        p1.getCategorias().addAll(Collections.singletonList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Collections.singletonList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "Sao Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "Sao Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Collections.singletonList(c1));
        est1.getCidades().addAll(Arrays.asList(c1, c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

    }
}
