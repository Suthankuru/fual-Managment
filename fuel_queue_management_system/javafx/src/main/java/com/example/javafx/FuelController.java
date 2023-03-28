package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.example.javafx.Main.*;

public class FuelController {

    @FXML
    private TextArea Fuel_Queue_Text_Area;

    @FXML
    private TextField search_field;
    


    @FXML
    protected void Fuel_Queue() {
        String data="";
        for (int x = 1; x < 31; x++) {
            if (Queue[x].Que.equals("empty")) {
                data+="Queue " + x + " is empty\n";
            } else {
                data+="\n---------------------------------------------";
                data+="\nVehicle " + x + " is occupied by : " + Queue[x].Que;
                data+="\nFirst name of Vehicle owner : " + Main.Name1[x].fName;
                data+="\nSurname of Vehicle owner : " + Name2[x].sName;
                data+="\nNo of Liters : " + No_of_liter[x].literCount;
                data+="\n---------------------------------------------\n";
            }
        }
        Fuel_Queue_Text_Area.setText(data);
    }

    @FXML
    protected void  Search_Cus() {
        String data = "";
        boolean Cus_Available = false;
        Fuel_Queue_Text_Area.setText("");
        String Name1 = search_field.getText();
        for (int i = 0; i< 31; i++){
            for (int c = 0; c< 31; c++){
                if (Queue[c].Que.equals(Name1)) {
                    data+="\n---------------------------------------------";
                    data+="\nVehicle " + c + " is occupied by : " + Queue[c].Que;
                    data+="\nFirst name of Vehicle owner : " + Main.Name1[c].fName;
                    data+="\nSurname of Vehicle owner : " + Name2[c].sName;
                    data+="\nNo of Liters : " + No_of_liter[c].literCount;
                    data+="\n---------------------------------------------\n";
                    Cus_Available = true;
                }
            }
        }
        if (!Cus_Available){
            data += "Customer Not Found.....";
        }
        Fuel_Queue_Text_Area.setText(data);
    }
}