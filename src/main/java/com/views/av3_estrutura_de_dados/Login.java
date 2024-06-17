package com.views.av3_estrutura_de_dados;

import com.models.av3_estrutura_de_dados.Entities.Listas.NosListas.NoCliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.models.av3_estrutura_de_dados.Entities.Listas.ListaClientes;
import com.controller.av3_estrutura_de_dados.LoginController;
import com.models.av3_estrutura_de_dados.Entities.Listas.Enum.TipoClienteEnum;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.PilhaProdutos;
import com.models.av3_estrutura_de_dados.Entities.Pilhas.NosPilhas.NoPilhaProduto;

import com.models.av3_estrutura_de_dados.Entities.Arvores.NosAvores.NoAvoreCompraCliente;
import com.models.av3_estrutura_de_dados.Entities.Arvores.ArvoreComprasCliente;

import com.models.av3_estrutura_de_dados.Entities.Filas.NosFilas.NoFilaAvaliacaoPedido;
import com.models.av3_estrutura_de_dados.Entities.Filas.FilaAvaliacaoPedido;

import java.io.IOException;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            ListaClientes listaCliente = new ListaClientes();
            listaCliente.adicionarCliente("Edson Brenno", "teste@gmail.com", "123456",
                    TipoClienteEnum.VENDEDOR);
            listaCliente.adicionarCliente("Cliente", "cliente@gmail.com", "123456",
                    TipoClienteEnum.CONSUMIDOR);
            PilhaProdutos pilhaProdutos = new PilhaProdutos();
            NoPilhaProduto t1;
            pilhaProdutos.emplilharProduto("p1", "  p1", 1L,null,10.65);
            pilhaProdutos.emplilharProduto("p2", "p2", 1L,null,14.65);
            pilhaProdutos.emplilharProduto("p3", "p3", 1L,null,15.65);
            pilhaProdutos.emplilharProduto("p4", "p4", 1L,null,16.65);

            ArvoreComprasCliente arvoreComprasCliente = new ArvoreComprasCliente();
            arvoreComprasCliente.adicionarCompraCliente("p1", 10.65, 1L, 2L);
            arvoreComprasCliente.adicionarCompraCliente("p2", 15.65, 1L, 2L);
            arvoreComprasCliente.adicionarCompraCliente("p3", 14.65,1L,2L);
            arvoreComprasCliente.adicionarCompraCliente("p4", 9,1L,2L);
            arvoreComprasCliente.adicionarCompraCliente("p5", 5,1L,3L);

            arvoreComprasCliente.obterTodosPedidosCliente(2);
            NoFilaAvaliacaoPedido t2 = null;
            FilaAvaliacaoPedido filaAvaliacaoPedido = new FilaAvaliacaoPedido();
            filaAvaliacaoPedido.enfileiraPedidoAvaliar("t1", "brenno", 2L);
            filaAvaliacaoPedido.enfileiraPedidoAvaliar("t2", "brenno", 2L);
            filaAvaliacaoPedido.enfileiraPedidoAvaliar("t3", "brenno", 2L);

            FXMLLoader loader = new FXMLLoader(Login.class.getResource("Login-view.fxml"));
            Scene scene = new Scene(loader.load());
            LoginController controller = (LoginController) loader.getController();
            controller.setListaClientes(listaCliente);
            controller.setPilhaProdutos(pilhaProdutos);
            controller.setArvoreComprasCliente(arvoreComprasCliente);
            stage.setTitle("Os mano");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
