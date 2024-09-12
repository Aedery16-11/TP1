package com.example.tp1entrainement;

import com.example.tp1entrainement.model.Message;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnMessage;
    @FXML
    private Button btnRecevoir;
    @FXML
    private AnchorPane ap1;
    @FXML
    private ListView lvExpediteur;
    @FXML
    private ListView lvDestinataire;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button btnEnvoyerMessage;
    @FXML
    private AnchorPane ap2;
    @FXML
    private ComboBox cboDestinataires;
    @FXML
    private TreeView tvDestinaires;
    private HashMap<String, ArrayList<Message>> maMessagerie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maMessagerie = new HashMap<>();
        ap1.toFront();
        //Page 1
        lvExpediteur.getItems().addAll("Enzo", "Noa", "Lilou", "Milo");
        lvDestinataire.getItems().addAll("Enzo", "Noa", "Lilou", "Milo");

        //Page 2
        cboDestinataires.getItems().addAll("Enzo", "Noa", "Lilou", "Milo");
        cboDestinataires.getSelectionModel().selectFirst();

    }

    @FXML
    public void btnMessageClicked(Event event) {
        ap1.toFront();
    }

    @FXML
    public void btnRecevoirClicked(Event event) {
        ap2.toFront();
    }

    @FXML
    public void btnEnvoyerMessageClicked(Event event) {
        String destinataire = lvDestinataire.getSelectionModel().getSelectedItem().toString();
        String expediteur = lvExpediteur.getSelectionModel().getSelectedItem().toString();
        String contenuDuMessage = txtMessage.getText();
        Message message = new Message(expediteur, destinataire, contenuDuMessage);
        if (!maMessagerie.containsKey(destinataire))
        {
            ArrayList<Message> lesMessages = new ArrayList<>();
            lesMessages.add(message);
            maMessagerie.put(destinataire, lesMessages);
        }
        else
        {
            maMessagerie.get(destinataire).add(message);
        }
        if (txtMessage.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir votre message");
            alert.setHeaderText("Erreur de saisie");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Message envoyé");
            alert.setHeaderText("Messagerie");
            alert.showAndWait();
        }
    } //c'est bon

    @FXML
    public void cboDestinatairesClicked(Event event) {
        TreeItem<String> root = new TreeItem<>("Tous les messages");
        root.setExpanded(true);
        String destinataire = cboDestinataires.getSelectionModel().getSelectedItem().toString();
        int i=0;
        if (!maMessagerie.containsKey(destinataire))
        {
            TreeItem<String> noMessage = new TreeItem<>("Pas de message");
            noMessage.setExpanded(true);
            root.getChildren().add(noMessage);
        }
        else
        {
            for (Message message : maMessagerie.get(destinataire))
            {
                i++;
                TreeItem<String> noeudNumMessage = new TreeItem<>("Message n° " + i);
                TreeItem<String> noeudExpediteur = new TreeItem<>("De ==> " + message.getExpediteur());
                TreeItem<String> noeudContenuDuMessage = new TreeItem<>("Message ===> " + message.getContenuDuMessage());

                root.getChildren().add(noeudNumMessage);
                noeudNumMessage.getChildren().add(noeudExpediteur);
                noeudNumMessage.getChildren().add(noeudContenuDuMessage);
            }
        }
        tvDestinaires.setRoot(root);
//        int a = 1;
    }

    @FXML
    public void tvDestinairesClicked(Event event) {
    }
}