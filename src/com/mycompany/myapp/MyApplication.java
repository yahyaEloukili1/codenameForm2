package com.mycompany.myapp;


import com.codename1.components.OnOffSwitch;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.UIBuilder;


/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        UIBuilder uIBuilder = new UIBuilder();
        UIBuilder.registerCustomComponent("Picker", Picker.class);
         UIBuilder.registerCustomComponent("OnOffSwitch", OnOffSwitch.class);
        
        Container container1 = uIBuilder.createContainer(theme,"Inscription");
        Container container2 = uIBuilder.createContainer(theme,"Résultats");
        Form f1 = (Form) uIBuilder.findByName("Inscription", container1);
        Form f2 = (Form) uIBuilder.findByName("Résultats", container2);
          
        TextField nom = (TextField) uIBuilder.findByName("nom", container1);
        TextField prenom = (TextField) uIBuilder.findByName("prenom", container1);
        TextField password = (TextField) uIBuilder.findByName("password", container1);
        Picker date =(Picker) uIBuilder.findByName("date", container1);
        OnOffSwitch gender = (OnOffSwitch) uIBuilder.findByName("gender", container1);
        CheckBox cfoot = (CheckBox) uIBuilder.findByName("foot", container1);
        CheckBox clecture = (CheckBox) uIBuilder.findByName("lecture", container1);
       
        CheckBox cnatation = (CheckBox) uIBuilder.findByName("natation", container1);
        CheckBox ctennis = (CheckBox) uIBuilder.findByName("tennis", container1);
        Button inscription = (Button) uIBuilder.findByName("inscription", container1);
      if(f1!=null){
          
            f1.show();
      }else{
          System.out.println("***************************************************");
      }
        
        Label lnom = (Label) uIBuilder.findByName("nom", container2);
        Label lprenom = (Label) uIBuilder.findByName("prenom", container2);
        Label lpwd = (Label) uIBuilder.findByName("password", container2);
        Label ldate = (Label) uIBuilder.findByName("date", container2);
        Label lgender = (Label) uIBuilder.findByName("gender", container2);
        Label lhobbies = (Label) uIBuilder.findByName("hobbies", container2);
        String foot,lecture,natation,tennis;
        inscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             lnom.setText("Nom : "+nom.getText());
               lprenom.setText("Prenom : "+prenom.getText());
              lpwd.setText("Password : "+password.getText());
               ldate.setText("Date : "+date.getDate().toString());
               if(gender.isValue()){
                   lgender.setText("Gender :"+gender.getOn());
               }else{
                   lgender.setText("Gender :"+gender.getOff());
               }
               String resultat = "";
               if(cfoot.isSelected()){
                   resultat = "\n"+cfoot.getText();
               }
                if(clecture.isSelected()){
                   resultat = "\n"+clecture.getText();
               }
                 if(ctennis.isSelected()){
                   resultat = "\n"+ctennis.getText();
               }
                  if(cnatation.isSelected()){
                   resultat = "\n"+cnatation.getText();
               }
                  lhobbies.setText("hobbies :"+resultat);
                  f2.show();
            }
        });
    
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
