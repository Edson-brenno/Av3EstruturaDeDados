module com.views.av3_estrutura_de_dados {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.views.av3_estrutura_de_dados to javafx.fxml;
    exports com.views.av3_estrutura_de_dados;

    opens com.controller.av3_estrutura_de_dados to javafx.fxml;
    exports com.controller.av3_estrutura_de_dados to javafx.fxml;
}