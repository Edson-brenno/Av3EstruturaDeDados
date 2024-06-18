package com.models.av3_estrutura_de_dados.Entities.Arvores;

import com.models.av3_estrutura_de_dados.Entities.Arvores.NosAvores.NoAvoreCompraCliente;
import com.models.av3_estrutura_de_dados.Entities.Filas.FilaAvaliacaoPedido;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;

public class ArvoreComprasCliente {
    private NoAvoreCompraCliente raiz;

    public ArvoreComprasCliente() {
        this.raiz = null;
    }

    public NoAvoreCompraCliente getRaiz() {
        return this.raiz;
    }


    // Verifica se arvore esta vazia
    private boolean arvoreEstaVazia(){
        return this.raiz == null;
    }

    // Adiciona uma nova compra na arvore
    public void adicionarCompraCliente(String nomeProduto, double precoProduto, Long idVendedor, Long idCliente) {
        NoAvoreCompraCliente novoNo = new NoAvoreCompraCliente(nomeProduto, precoProduto, idVendedor, idCliente);
        if (this.arvoreEstaVazia()) {
            this.raiz = novoNo;
        }else{
            NoAvoreCompraCliente atual = this.raiz;

            while(true){
                if(novoNo.getPrecoProduto() < atual.getPrecoProduto()){
                    if(atual.getEsquerda() != null){
                        atual = atual.getEsquerda();
                    }else{
                        atual.setEsquerda(novoNo);
                        break;
                    }
                }else{
                    if(atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(novoNo);
                        break;
                    }
                }
            }
        }
    }

    // Obtem os pedidos em ordem com base no id do cliente comprador
    private void obterPedidosEmOrdem(NoAvoreCompraCliente atual, long idClienteComprador, PilhaProdutos pilhaProdutos){
        if(atual != null){
            obterPedidosEmOrdem(atual.getEsquerda(), idClienteComprador, pilhaProdutos);
            if(atual.getIdCliente() == idClienteComprador){
                System.out.println(atual.getNomeProduto());
                pilhaProdutos.emplilharProduto(atual.getNomeProduto(),"", atual.getIdVendedor(),
                      idClienteComprador  , atual.getPrecoProduto());
            }
            obterPedidosEmOrdem(atual.getDireita(), idClienteComprador, pilhaProdutos);
        }
    }

    // Retorna uma pilha com os produtos do cliente
    public PilhaProdutos obterTodosPedidosCliente(long idClienteComprador){
        PilhaProdutos pilhaProdutos = new PilhaProdutos();
        obterPedidosEmOrdem(this.raiz, idClienteComprador, pilhaProdutos);
        return pilhaProdutos;
    }

    // Busca por ordem e seta o produto como avaliado
    private void setarPedidoAvaliadoEmOrdem(NoAvoreCompraCliente atual,String idCompra){
        if(atual != null){
            setarPedidoAvaliadoEmOrdem(atual.getEsquerda(), idCompra);
            if(atual.getIdCompra().equals(idCompra)){
                atual.setProdutoAvaliadao(true);
            }
            setarPedidoAvaliadoEmOrdem(atual.getDireita(), idCompra);
        }
    }

    // seta o pedido como avaliado
    public void setarPedidoAvaliado(String idCompra){
        NoAvoreCompraCliente atual = this.raiz;
        if(atual != null){
            setarPedidoAvaliadoEmOrdem(atual, idCompra);
        }
    }

    // Busca os pedidos a serem avaliados com base no cliente Consumidos
    private void obterPedidoASerAvaliadoEmOrdem(NoAvoreCompraCliente atual, long idClienteConsumidor,
                                                FilaAvaliacaoPedido filaAvaliacaoPedido, ListaClientes listaClientes){
        if(atual != null){
            obterPedidoASerAvaliadoEmOrdem(atual.getEsquerda(), idClienteConsumidor, filaAvaliacaoPedido, listaClientes);
            if(atual.getIdCliente() == idClienteConsumidor && atual.getProdutoAvaliadao().equals(false)){
                System.out.println(atual.getNomeProduto());
                filaAvaliacaoPedido.enfileiraPedidoAvaliar(atual.getNomeProduto(),
                        listaClientes.obterNomeVendedor(atual.getIdVendedor()), idClienteConsumidor, atual.getIdCompra());
            }
            obterPedidoASerAvaliadoEmOrdem(atual.getDireita(), idClienteConsumidor, filaAvaliacaoPedido, listaClientes);
        }
    }

    // retorna a fila de avaliação
    public FilaAvaliacaoPedido obterPedidosASeremAvaliadosClienteConsumidor(long idClienteConsumidor,
                                                                             ListaClientes listaClientes){
        FilaAvaliacaoPedido filaAvaliacaoPedido = new FilaAvaliacaoPedido();
        this.obterPedidoASerAvaliadoEmOrdem(this.raiz, idClienteConsumidor, filaAvaliacaoPedido, listaClientes);
        return filaAvaliacaoPedido;
    }

    // Obtem pedido em ordem para vendedor
    private void obterPedidosEmOrdemParaVendedor(NoAvoreCompraCliente atual, long idClienteVendedor,
                                                 PilhaProdutos pilhaProdutos){
        if(atual != null){
            obterPedidosEmOrdem(atual.getEsquerda(), idClienteVendedor, pilhaProdutos);
            if(atual.getIdVendedor() == idClienteVendedor){
                pilhaProdutos.emplilharProduto(atual.getNomeProduto(),"", atual.getIdVendedor(),
                        null  , atual.getPrecoProduto());
            }
            obterPedidosEmOrdem(atual.getDireita(), idClienteVendedor, pilhaProdutos);
        }
    }

    // retorna pilha com produtos
    public PilhaProdutos obterTodasVendasClienteVendedor(long idClienteVendedor){
        PilhaProdutos pilhaProdutos = new PilhaProdutos();
        obterPedidosEmOrdemParaVendedor(this.raiz, idClienteVendedor, pilhaProdutos);
        return pilhaProdutos;
    }
}
