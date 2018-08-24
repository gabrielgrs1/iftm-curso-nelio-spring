package com.gabrielgrs.aulaspring;

import com.gabrielgrs.aulaspring.domain.*;
import com.gabrielgrs.aulaspring.domain.enuns.EstadoPagamento;
import com.gabrielgrs.aulaspring.domain.enuns.TipoCliente;
import com.gabrielgrs.aulaspring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class AulaspringApplication implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;


    @Autowired
    public AulaspringApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(AulaspringApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {

        Categoria cat1 = new Categoria("Informática");
        Categoria cat2 = new Categoria("Escritório");

        Produto p1 = new Produto("Computador", 2000.0);
        Produto p2 = new Produto("Impressora", 800.0);
        Produto p3 = new Produto("Mouse", 80.0);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Collections.singletonList(p2));

        p1.getCategorias().addAll(Collections.singletonList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Collections.singletonList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("Sao Paulo");

        Cidade c1 = new Cidade("Uberlandia", est1);
        Cidade c2 = new Cidade("Sao Paulo", est2);
        Cidade c3 = new Cidade("Campinas", est2);

        est1.getCidades().addAll(Collections.singletonList(c1));
        est1.getCidades().addAll(Arrays.asList(c1, c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 = new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "3220834", cli1, c1);
        Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Collections.singletonList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
        Pedido ped1 = new Pedido(simpleDateFormat.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(simpleDateFormat.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, simpleDateFormat.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

    }
}
