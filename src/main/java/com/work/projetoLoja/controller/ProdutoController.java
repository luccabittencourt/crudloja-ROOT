package com.work.projetoLoja.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.work.projetoLoja.model.Produto;
import com.work.projetoLoja.model.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    // Página inicial para listar produtos
    @GetMapping
    public String listarProdutos(Model model) {
        List<Map<String, Object>> produtos = produtoService.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "produtos/listar"; // Nome da página Thymeleaf
    }

    // Página de formulário para adicionar produto
    @GetMapping("/novo")
    public String mostrarFormularioNovoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/formulario";
    }

    // Adicionar ou editar um produto
    @PostMapping
    public String salvarOuEditarProduto(@ModelAttribute Produto produto) {
        if (produto.getId() != 0) { // Verifica se o produto já existe
            System.out.println("Atualizando produto: " + produto);
            produtoService.atualizarProduto(produto);
        } else {
            System.out.println("Adicionando novo produto: " + produto);
            produtoService.adicionarProduto(produto);
        }
        return "redirect:/produtos";
    }

    // Página de formulário para editar produto
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarProduto(@PathVariable int id, Model model) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        model.addAttribute("produto", produto);
        return "produtos/formulario";
    }

    // Excluir um produto
    @GetMapping("/{id}/deletar")
    public String deletarProduto(@PathVariable int id) {
        produtoService.deletarProduto(id);
        return "redirect:/produtos";
    }
}
