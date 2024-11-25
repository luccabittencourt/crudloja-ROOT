package com.work.projetoLoja.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDAO {

    @Autowired
    JdbcTemplate jdbc;

    // Inserir um novo produto
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, descricao) VALUES (?, ?, ?)";
        jdbc.update(sql, produto.getNome(), produto.getPreco(), produto.getDescricao());
    }

    // Atualizar um produto existente
    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, descricao = ? WHERE id = ?";
        jdbc.update(sql, produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getId());
    }

    // Obter um produto por ID
    public Produto obterProduto(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        Map<String, Object> resultado = jdbc.queryForMap(sql, id);
        Produto produto = new Produto();
        produto.setId((int) resultado.get("id"));
        produto.setNome((String) resultado.get("nome"));
        produto.setPreco(((BigDecimal) resultado.get("preco")).doubleValue()); // Conversão para Double
        produto.setDescricao((String) resultado.get("descricao"));
        return produto;
    }

    // Obter todos os produtos
    public List<Map<String, Object>> obterTodosProdutos() {
        String sql = "SELECT * FROM produto";
        return jdbc.queryForList(sql);
    }

    // Deletar um produto por ID
    public void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        jdbc.update(sql, id);
    }
}
