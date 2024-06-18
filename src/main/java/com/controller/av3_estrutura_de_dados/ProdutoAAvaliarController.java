package com.controller.av3_estrutura_de_dados;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.views.av3_estrutura_de_dados.IndexCliente;
import com.views.av3_estrutura_de_dados.Login;
import com.views.av3_estrutura_de_dados.MeuPedidosClienteConsumidor;
import com.views.av3_estrutura_de_dados.ProdutosAAvaliar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;
import com.models.av3_estrutura_de_dados.Entities.Filas.FilaAvaliacaoPedido;
import com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas.NoFilaAvaliacaoPedido;

import com.views.av3_estrutura_de_dados.util.CarregarPagina;

import com.controller.av3_estrutura_de_dados.interfaces.Controller;

import static com.views.av3_estrutura_de_dados.util.Constraints.setTextFlieldNotaAvaliacao;

public class ProdutoAAvaliarController implements Initializable, Controller {

    @FXML
    private ListaClientes listaClientes;
    @FXML
    private PilhaProdutos pilhaProdutos;
    @FXML
    private ArvoreComprasCliente arvoreComprasCliente;
    @FXML
    private FilaAvaliacaoPedido filaAvaliacaoPedido;
    @FXML
    private NoFilaAvaliacaoPedido produtoASerAvaliado;
    @FXML
    private Label labelNomeUsuario;
    @FXML
    private Label mensagemSemProdutoParaAvaliar;
    @FXML
    private Label labelProduto;
    @FXML
    private Label nomeProduto;
    @FXML
    private Label descricaoAvaliacao;
    @FXML
    private TextArea textAreaDescricao;
    @FXML
    private Label labelNotaProduto;
    @FXML
    private TextField textFieldNotaProduto;
    @FXML
    private Button buttonVoltar,buttonAvaliar, btnDeslogar, buttonPedidosDoCliente;

    @FXML
    // Função a ser executada ao clicar no botao avaliar
    public void onBtnAvaliarAction(ActionEvent event) throws IOException {
        // Se possui produto a ser avaliado
        if(this.produtoASerAvaliado != null){
            if(osCamposDaAvaliacaoEstaoCorretos()){
                // Seta o produto como avaliado na arvore
                this.arvoreComprasCliente.setarPedidoAvaliado(this.produtoASerAvaliado.getIdCompra());

                // Alerta de sucesso
                Alert avaliacaoRealizada = new Alert(Alert.AlertType.INFORMATION);
                avaliacaoRealizada.setTitle("Avaliacao Realizada");
                avaliacaoRealizada.setHeaderText(null);
                avaliacaoRealizada.setContentText("Avaliacao do produto" + this.labelProduto.getText() +
                        " foi Realizada com sucesso");
                avaliacaoRealizada.showAndWait();

                // Reacarrega a pagina
                CarregarPagina.trocarPagina(event, ProdutosAAvaliar.class, "ProdutosAAvaliar.fxml",
                        this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
            }else{
                // Mostra error
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error ao avaliar");
                error.setHeaderText(null);
                error.setContentText("Preencha todos os campos corretamente");
                error.showAndWait();
            }
        }else{
            // Alerta de sucesso
            Alert avaliacaoRealizada = new Alert(Alert.AlertType.INFORMATION);
            avaliacaoRealizada.setTitle("Avaliacao Realizada");
            avaliacaoRealizada.setHeaderText(null);
            avaliacaoRealizada.setContentText("Avaliacao do produto" + this.labelProduto.getText() +
                    " foi Realizada com sucesso");
            avaliacaoRealizada.showAndWait();

            // Reacarrega a pagina
            CarregarPagina.trocarPagina(event, ProdutosAAvaliar.class, "ProdutosAAvaliar.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }

    }

    @FXML
    // Função a ser chamada ao clicar no botao deslogar
    public void onBtnDeslogar(ActionEvent event) throws IOException {
        try {
            // Desloga usuário
            this.listaClientes.deslogarCliente();
            // Carrega pagina do login
            CarregarPagina.trocarPagina(event, Login.class, "Login-view.fxml",
                    this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @FXML
    // Função a ser chamada ao clicar no botao voltar
    public void onBtnVoltarAction(ActionEvent event) throws IOException {
        // Carrega página index do cliente
        CarregarPagina.trocarPagina(event, IndexCliente.class, "IndexCliente-view.fxml",
                this.listaClientes, this.pilhaProdutos, this.arvoreComprasCliente);
    }

    @FXML
    // Função a ser chamada ao clicar no botao de ver meus pedidos
    public void onBtnPedidosDoCliente(ActionEvent event) throws IOException {
        try {
            // Carrega página dos meus pedidos
            CarregarPagina.trocarPagina(event, MeuPedidosClienteConsumidor.class,
                    "MeuPedidosClienteConsumidor-view.fxml", this.listaClientes, this.pilhaProdutos,
                    this.arvoreComprasCliente);

        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
    @Override
    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public void setPilhaProdutos(PilhaProdutos pilhaProdutos) {
        this.pilhaProdutos = pilhaProdutos;
    }

    @Override
    public void getPilhaProdutos() {
        this.pilhaProdutos.mostrarProdutos();
    }

    @Override
    public void setArvoreComprasCliente(ArvoreComprasCliente arvore) {
        this.arvoreComprasCliente = arvore;
    }

    @Override
    public void getArvoreComprasCliente() {
        this.arvoreComprasCliente.obterTodosPedidosCliente(2L);
    }

    @Override
    // Função a ser executada ao carregar o fxml
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // listener do field da nota
        setTextFlieldNotaAvaliacao(this.textFieldNotaProduto);

        // Funções a serem executadas após o carregamento da pagina
        Platform.runLater(this::setarNomeUsuarioNoLabel);
        Platform.runLater(this::setFilaAvaliacaoPedido);
        Platform.runLater(this::getProdutoASerAvaliado);
        Platform.runLater(this::setarVisibilidadeComponentes);
    }

    // seta nome usuário na vire
    private void setarNomeUsuarioNoLabel(){
        this.labelNomeUsuario.setText(listaClientes.usuarioLogado.getNomeCompleto());
    }

    // estancia a fila de avaliação dos produtos
    private void setFilaAvaliacaoPedido(){
        // obtem os produtos que ainda precisam ser avaliados
        this.filaAvaliacaoPedido = this.arvoreComprasCliente.obterPedidosASeremAvaliadosClienteConsumidor(
                this.listaClientes.usuarioLogado.getId(), this.listaClientes
        );
    }

    // Obtem o produto a ser avaliado
    private void getProdutoASerAvaliado(){
        if(this.filaAvaliacaoPedido != null){
            // desenfileira a fila
            this.produtoASerAvaliado = this.filaAvaliacaoPedido.desenfileiraPedidoAvaliar();

            if(this.produtoASerAvaliado == null){
                this.filaAvaliacaoPedido = null;
            }else{
                this.nomeProduto.setText(this.produtoASerAvaliado.getNomeProduto());
            }

        }
    }

    // Seta a visibiliadade do componente de avaliação
    private void setarVisibilidadeComponentes(){
        if(this.produtoASerAvaliado == null){ // Se não possui produto a ser avaliado mostra mensagem sem produtos para avaliar
            this.labelProduto.setVisible(false);
            this.nomeProduto.setVisible(false);
            this.descricaoAvaliacao.setVisible(false);
            this.textAreaDescricao.setVisible(false);
            this.labelNotaProduto.setVisible(false);
            this.textFieldNotaProduto.setVisible(false);
            this.buttonAvaliar.setVisible(false);
        }else{// mostra os componentes
            this.mensagemSemProdutoParaAvaliar.setVisible(false);
        }
    }

    // verifica se os campos de avaliação estao corretos
    private boolean osCamposDaAvaliacaoEstaoCorretos(){
        String nota = this.textFieldNotaProduto.getText();
        return nota.matches("^[0-9]+$") &&
                (Integer.parseInt(nota) > 0 &&
                        Integer.parseInt(nota) < 11);
    }
}
