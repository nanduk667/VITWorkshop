package com.example.intodarkness.vitworkshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationForm extends AppCompatActivity {

    EditText rno;
    EditText name;
    TextView displayTemp;
    MyDBHandler dbHandler;
    public static TextView schoolName;
    public static TextView workshopName;
    public static EditText studName;
    public static EditText rollNo;
    public static String taginfoReg;
    public static String schoolNameVar;
    public static String workshopNameVar;
    public static String studNameVar;
    public static String rollNoVar;
    // public String stud;
    // public int i;

    public static final String FIREBASE_URL = "https://vitworkshop.firebaseio.com/";
    DataToFirebase dataToFirebase;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        schoolName = (TextView) findViewById(R.id.schoolName);
        workshopName = (TextView) findViewById(R.id.workshopName);
        studName = (EditText) findViewById(R.id.edit_name);
        rollNo = (EditText) findViewById(R.id.edit_rno);
        //take values from carviews here and do all the things

        taginfoReg = MainActivity.tagInfo;

        if (taginfoReg.contains("SITE")) {

            schoolName.setText("SITE");
            schoolNameVar = "SITE";
            //  i = 1;

        } else if (taginfoReg.contains("SELECT")) {
            schoolName.setText("SELECT");
            schoolNameVar = "SELECT";
            //   i = 1;

        } else if (taginfoReg.contains("SAS")) {
            schoolName.setText("SAS");
            schoolNameVar = "SAS";
            // i = 1;
        }
        setWorkshopName();
        // System.out.println(schoolName + " "+ workshopName);
       /* displayTemp = (TextView)findViewById(R.id.textView3);
        dbHandler = new MyDBHandler(this,null,null,1);*/
        // displayText();


    }

    public void setWorkshopName() {
        workshopNameVar = taginfoReg.substring(taginfoReg.indexOf(" "));
        workshopName.setText(workshopNameVar);
    }

    public void addToFirebase(View view) {

        myFirebaseRef = new Firebase(FIREBASE_URL);
        Firebase newURL;
        // stud = "student" + i++;
        studNameVar = studName.getText().toString();
        rollNoVar = rollNo.getText().toString();

        newURL = myFirebaseRef.child(schoolNameVar).child(workshopNameVar);
        if (!(rollNoVar.matches("") || studNameVar.matches(""))) {
            if (checkPattern())  //check reg expression
             {
            checkPattern();
            if (checkNetwork()) { //if internet

                dataToFirebase = new DataToFirebase(studNameVar, rollNoVar);
                //   newURL.child(stud).setValue(dataToFirebase);
                newURL.child(rollNoVar).setValue(dataToFirebase);
                //  newURL.push().setValue(dataToFirebase);
                studName.setText("");
                rollNo.setText("");
                Toast.makeText(getApplicationContext(), "Successfully Registered! ", Toast.LENGTH_SHORT).show();
            } else //if no Internet
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("No Internet connectivity, Registration will be completed on the next app run!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
             } else //if checkPattern returns false
             {
                 rollNo.setError("Please enter in the said format!");
            }
        } else {
            if (rollNoVar.matches("")) {
                rollNo.setError("This field should not be left blank");
            }
            if (studNameVar.matches("")) {
                studName.setError("This field should not be left blank");
            }
            // Toast.makeText(RegistrationForm.this, "Fields should not be left blank ", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean checkNetwork() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor(); //the exit value of the native process being waited on.
            System.out.println(returnVal);
            System.out.println(p1);
            boolean reachable = (returnVal == 0);
            return reachable; // returns true if internet is available
        } catch (Exception e) {

            System.out.print(e);
        }
        return false;
    }
        /*
        if (ipAddr.equals("")) {
            return false;
        } else {
            return true;
        }*/
        /*ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();*/


    public boolean checkPattern() {
        Pattern pattern = Pattern.compile("^\\d{2}[a-zA-Z]{3}\\d{4}$");
        Matcher matcher = pattern.matcher(rollNoVar);
        if(matcher.find())
        {
            return  true;
        }
        else
           return false;
    }

}
   /* public void addToDB(View view)
    {
        FormDatabase formDatabase = new FormDatabase(rno.getText().toString(),name.getText().toString());
        dbHandler.addData(formDatabase);
        displayText();

    }
    public void displayText()
    {
       String dbString = dbHandler.databaseToString();
        displayTemp.setText(dbString);
        rno.setText("");

    }
    public void deleteFromDB(View view)
    {   displayTemp.setText("this is working");
        String inputText = rno.getText().toString();
        dbHandler.deleteData(inputText);
        displayText();
    }*/

