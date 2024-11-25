package com.work.projetoLoja.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDAO produtoDAO;

    // Adicionar um novo produto
    public void adicionarProduto(Produto produto) {
        produtoDAO.inserir(produto);
    }

    // Atualizar um produto existente
    public void atualizarProduto(Produto produto) {
        produtoDAO.atualizar(produto);
    }

    // Buscar um produto por ID
    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.obterProduto(id);
    }

    // Listar todos os produtos
    public List<Map<String, Object>> listarProdutos() {
        return produtoDAO.obterTodosProdutos();
    }

    // Deletar um produto por ID
    public void deletarProduto(int id) {
        produtoDAO.deletar(id);
    }
}
