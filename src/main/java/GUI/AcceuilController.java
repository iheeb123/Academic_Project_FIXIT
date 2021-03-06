/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static API.CAM.Capture;
import API.FB;

import entites.Article;
import entites.Posteur;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.Articlegestion;
import service.JobeurService;
import service.PosteurService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AcceuilController implements Initializable {
    final ToggleGroup group = new ToggleGroup();

            
    @FXML
    private Button Btn_SignIn;
    @FXML
    private TextField label_UserName;
    @FXML
    private TextField Label_Password;

    public static int cinlogin;
    @FXML
    private TableView table_article;
    @FXML
    private TableColumn<?, ?> id1;
    @FXML
    private TableColumn<?, ?> nom_art;
    @FXML
    private TableColumn<?, ?> descr_art;
    @FXML
    private TableColumn<?, ?> cat1;
    @FXML
    private TableColumn<?, ?> dat1;
    @FXML
    private TableColumn<?, ?> sou1;
    public static Article A1;
    public static Image m;
    /**
     * Initializes the controller class.
     */
    Articlegestion art = new Articlegestion();
  ArrayList<Article> articles= (ArrayList<Article>)art.afficherArticle();  
  public ObservableList<Article> data = FXCollections.observableArrayList(articles);
    @FXML
    private Label motdepass_oub;
    @FXML
    private RadioButton radio_post;
    @FXML
    private RadioButton radio_job;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radio_job.setToggleGroup(group);
        radio_post.setSelected(true);
        radio_post.setToggleGroup(group);
                
        // TOD
          
     table_article.setItems(data);
     id1.setCellValueFactory(new PropertyValueFactory <>("id"));
     nom_art.setCellValueFactory(new PropertyValueFactory <>("nom_article"));
     descr_art.setCellValueFactory(new PropertyValueFactory <>("descriptionarticle"));
     cat1.setCellValueFactory(new PropertyValueFactory <>("categorie"));
     dat1.setCellValueFactory(new PropertyValueFactory <>("date_article"));
     sou1.setCellValueFactory(new PropertyValueFactory <>("sources"));
    
     
    
         table_article.setOnMouseClicked((MouseEvent event) -> {
         if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
         Article A=new Article();
         A = (Article) table_article.getItems().get(table_article.getSelectionModel().getSelectedIndex());
         A1=A;
         Articlegestion a=new Articlegestion();
             try {
                m=a.get_image(A.getId());
             } catch (SQLException ex) {
                 Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
             }
          Parent root = null;
                System.out.println(A);
                try {
                  root = FXMLLoader.load(getClass().getResource("/fxml/affiche_article.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }
        Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show(); 
                
         }});
    }
    @FXML
    private void Btn_SignIn(ActionEvent event) throws IOException {
     int cin = Integer.parseInt(label_UserName.getText());
     cinlogin=cin;
     String password= Label_Password.getText();
     String role;
     PosteurService p = new PosteurService();
    role=p.login(cin, password);
    if("fault".equals(role))
    {
        JOptionPane.showMessageDialog(null, "Please check your Cin and Password");
    }
    else if("banned".equals(role))
    {
        JOptionPane.showMessageDialog(null, "Your Account has been banned beacuse of "+p.BannirRaison(cin, password));
    }
    else
    {
    Parent root=FXMLLoader.load(getClass().getResource("/fxml/"+role+".fxml"));
        Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();        
    }
    }

    @FXML
    private void Btn_formulaire_posteur(ActionEvent event) throws IOException {
            Parent root=FXMLLoader.load(getClass().getResource("/fxml/InscrirePosteur.fxml"));
        Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void Btn_fromulaire_jobeur(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("/fxml/InscrireJobeur.fxml"));
        Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
    }


    @FXML
    private void Pass_oublie(MouseEvent event) throws IOException {
                   
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/Password.fxml"));
        Stage stage1 = new Stage();
                    
                    Scene scene = new Scene(root);
                    stage1.setScene(scene);
                    /* Stage stag1 = new Stage(root);*/
                    stage1.show();
        
    }

    @FXML
    private void Facebook_login(MouseEvent event) throws IOException {
        FB.login();
        if(PosteurService.pFb || FB.alreadysignfb)
        {
            Parent root=FXMLLoader.load(getClass().getResource("/fxml/Posteur_interface.fxml"));
        Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();  
        }
    }

    
    
    
}
