package com.mycompany.supermercado.controller;

import com.mycompany.supermercado.model.ListaSupermercado;
import com.mycompany.supermercado.model.Supermercado;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SupermercadoController {

    public boolean cadastrar(int codigo, String nome, String descricao, float preco, float quantidade, float valor) {
        Supermercado supermercado = new Supermercado(codigo, nome, descricao, preco, quantidade, valor);
        return ListaSupermercado.getInstance().add(supermercado);
    }

    public void preencherTabela(JTable jTabela) {
        DefaultTableModel dtm = (DefaultTableModel) jTabela.getModel();

        dtm.setRowCount(ListaSupermercado.getInstance().size());
        jTabela.setModel(dtm);

        int posicaoLinha = 0;

        for (int i = 0; i < ListaSupermercado.getInstance().size(); i++) {
            jTabela.setValueAt(ListaSupermercado.getInstance().get(i).getCodigo(), posicaoLinha, 0);
            jTabela.setValueAt(ListaSupermercado.getInstance().get(i).getNome(), posicaoLinha, 1);
            jTabela.setValueAt(ListaSupermercado.getInstance().get(i).getDescricao(), posicaoLinha, 2);
            jTabela.setValueAt(ListaSupermercado.getInstance().get(i).getPreco(), posicaoLinha, 3);
            jTabela.setValueAt(ListaSupermercado.getInstance().get(i).getQuantidade(), posicaoLinha, 4);
            jTabela.setValueAt(ListaSupermercado.getInstance().get(i).getValor(), posicaoLinha, 5);
            
            posicaoLinha += 1;
        }
        
    }

    public void excluir(JTable jTabela) {
        if (jTabela.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto na tabela!", "Aviso", 0);
        } else {
            int resposta = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza que deseja excluir o produto?",
                    "Aviso",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                ListaSupermercado.getInstance().remove(jTabela.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso", "Aviso", 1);

                preencherTabela(jTabela);
            }
        }
    }

    public void editar(int codigo, String nome, String descricao, float preco, float quantidade, float valor) {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja atualizar o produto?",
                "Aviso",
                JOptionPane.YES_NO_OPTION
        );
        if (resposta == JOptionPane.YES_OPTION) {
            Supermercado supermercado = new Supermercado(codigo, nome, descricao, preco, quantidade, valor);

            int posicao = pesquisarProduto(codigo);
            ListaSupermercado.getInstance().set(posicao, supermercado);

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso", "Aviso", 1);
        }
    }

    private int pesquisarProduto(int codigo) {

        int posicao = -1;

        for (int i = 0; i < ListaSupermercado.getInstance().size(); i++) {
            if (ListaSupermercado.getInstance().get(i).getCodigo() == codigo) {
                posicao = i;
            }
        }
        return posicao;
    }

    public List<Object> pesquisar(int codigo) {
        boolean achou = false;
        int posicao = -1;

        for (int i = 0; i < ListaSupermercado.getInstance().size(); i++) {
            if (ListaSupermercado.getInstance().get(i).getCodigo() == codigo) {
                achou = true;
                posicao = i;
            }
        }

        if (achou == true) {
            String nome = ListaSupermercado.getInstance().get(posicao).getNome();
            String descricao = ListaSupermercado.getInstance().get(posicao).getDescricao();
            float preco = (float) ListaSupermercado.getInstance().get(posicao).getPreco();
            float quantidade = (float) ListaSupermercado.getInstance().get(posicao).getQuantidade();

            return Arrays.asList(codigo, nome, descricao, preco, quantidade);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Aviso", 0);
            return null;
        }
    }

    public float calcularValorTotalLista() {
        float valorTotal = 0;

        for (Supermercado produto : ListaSupermercado.getInstance()) {
            valorTotal += produto.getValor();
        }

        return valorTotal;
    }

}

