package com.example.kanna.paynpark;

import java.util.regex.Pattern;

public   class ValidationClass {

    public static boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 10 || phone.length() > 10) {
                 //if(phone.length() != 10) {
                check = false;

                //txtPhone.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }

    public static boolean isValidVehicle(String vehno) {
        boolean check=false;
        if(Pattern.matches("^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$", vehno)) {

                check = true;

        } else {
            check=false;
        }
        return check;
    }
}
