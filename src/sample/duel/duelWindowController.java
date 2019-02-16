package sample.duel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class duelWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfOnePlayerAttack;

    @FXML
    private TextField tfOnePlayerKrit;

    @FXML
    private TextField tfOnePlayerShansKrit;

    @FXML
    private TextField tfOnePlayerDef;

    @FXML
    private TextField tfOnePlayerShansOglush;

    @FXML
    private TextField tfOnePlayerShansHill;

    @FXML
    private TextField tfTwoPlayerAttack;

    @FXML
    private TextField tfTwoPlayerKrit;

    @FXML
    private TextField tfTwoPlayerShansKrit;

    @FXML
    private TextField tfTwoPlayerDef;

    @FXML
    private TextField tfTwoPlayerShansOglush;

    @FXML
    private TextField tfTwoPlayerShansHill;

    @FXML
    private Button buttonStart;

    @FXML
    private Label labelError;

    @FXML
    void initialize() {
        pressButton();
    }

    DuelPlayerTwo duelPlayerTwo = new DuelPlayerTwo();
    DuelPlayerOne duelPlayerOne = new DuelPlayerOne();
    public int hpOnePlayer = 1000, hpTwoPlayer = 1000;
    public int attackOnePers, attackTwoPers, kritOnePers, kritTwoPers, defOnePers, defTwoPers;

    void pressButton(){

        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setParameters();
            }
        });

    }
    private void setParameters(){


        try {
            duelPlayerOne.attack = Integer.parseInt(tfOnePlayerAttack.getText());
            duelPlayerOne.krit = Integer.parseInt(tfOnePlayerKrit.getText());
            duelPlayerOne.shansKrit = Integer.parseInt(tfOnePlayerShansKrit.getText());
            duelPlayerOne.def = Integer.parseInt(tfOnePlayerDef.getText());
            duelPlayerOne.shansOglush = Integer.parseInt(tfOnePlayerShansOglush.getText());
            duelPlayerOne.shansHill = Integer.parseInt(tfOnePlayerShansHill.getText());

        }catch (Exception e){
            labelError.setText("Можно вводить только числа!!!");
        }



        try{duelPlayerTwo.attack = Integer.parseInt(tfTwoPlayerAttack.getText());
            duelPlayerTwo.krit = Integer.parseInt(tfTwoPlayerKrit.getText());
            duelPlayerTwo.shansKrit = Integer.parseInt(tfTwoPlayerShansKrit.getText());
            duelPlayerTwo.def = Integer.parseInt(tfTwoPlayerDef.getText());
            duelPlayerTwo.shansOglush = Integer.parseInt(tfTwoPlayerShansOglush.getText());
            duelPlayerTwo.shansHill = Integer.parseInt(tfTwoPlayerShansHill.getText());
            firstHit();
        }catch (Exception e){
            labelError.setText("Можно вводить только числа!!!");
        }
    }

    private void firstHit() {

        int firstHit = 0 + (int) (Math.random() * 2);
        System.out.println(firstHit);

            if (firstHit==1){
                onePlayerAttack();
            }else{
                twoPlayerAttack();
            }

    }

    public void onePlayerAttack(){
        for(;((hpOnePlayer>0)&&(hpTwoPlayer>0));){
            attack(duelPlayerOne.attack, duelPlayerOne.shansKrit, duelPlayerOne.krit, duelPlayerOne.def, 1);
            if(hpTwoPlayer>0){
                attack(duelPlayerTwo.attack, duelPlayerTwo.shansKrit, duelPlayerTwo.krit, duelPlayerTwo.def, 2);
            }else{
                System.out.println("Победа игрока 1!");
            }
        }
        if(hpOnePlayer<=0){
            System.out.println("Игрок 2 победил!");
        }else if(hpTwoPlayer<=0){
            System.out.println("Игрок 1 победил!");
        }
    }

    public void twoPlayerAttack(){
        for(;((hpOnePlayer>0)&&(hpTwoPlayer>0));){
            attack(duelPlayerTwo.attack, duelPlayerTwo.shansKrit, duelPlayerTwo.krit, duelPlayerTwo.def, 2);
            if(hpOnePlayer>0){
                attack(duelPlayerOne.attack, duelPlayerOne.shansKrit, duelPlayerOne.krit, duelPlayerOne.def, 1);
            }else{
                System.out.println("Победа игрока 2!");
            }
        }
        if(hpTwoPlayer<=0){
            System.out.println("Игрок 1 победил!");
        }else if(hpOnePlayer<=0){
            System.out.println("Игрок 2 победил!");
        }
    }

    private void attack(int attack, int shansKrit, int krit, int def, int och){
        int pk = 0;
        int itogattack = 0;

        if(attack<=0){
           itogattack = 0;
        }else if(attack == 1){
            itogattack = 1;
        }else {
            itogattack = (attack - (attack / 2) + (int) (Math.random() * attack));
        }


        if(krit <= 0){

        }else if(krit == 1){
            itogattack+=1;
        }else {
            if ((0 + (int) (Math.random() * 100)) <= shansKrit) {
                itogattack += (krit - (krit / 2) + (int) (Math.random() * krit));
                pk = 1;
            }
        }


        if(def <= 0){

        }else if(def == 1){
            itogattack-=1;
        }else{
            if((def >= itogattack) && (pk == 0)){
                System.out.println("Урон полностью заблокирован");
                itogattack = 0;
            }else if((def >= itogattack) && (pk == 1)){
                System.out.println("Крит. урон разбил защиту");

            }else{
                itogattack-=def;
            }
        }

        if(och == 1){
            hpTwoPlayer -= itogattack;
            System.out.println("Игрок 1 ударил игрока 2 на: "+itogattack+". Жизней у игрока 2 осталось: "+hpTwoPlayer);
        }else{
            hpOnePlayer -= itogattack;
            System.out.println("Игрок 2 ударил игрока 1 на: "+itogattack+". Жизней у игрока 1 осталось: "+hpOnePlayer);
        }

    }



}
