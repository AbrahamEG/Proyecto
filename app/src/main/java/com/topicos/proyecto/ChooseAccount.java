package com.topicos.proyecto;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.view.View;


/**
 * Created by USER on 06/05/2018.
 */

public class ChooseAccount {

    private static String control;

    protected static void getAccounts(View app, Activity act){
        Account[] accounts= AccountManager.get(app.getContext()).getAccounts();
        /*if(accounts!=null){
            String[] s=null;
            for(Account a:accounts){
                if(a.type.equalsIgnoreCase("com.google")){
                    s=addString(s,a.name);
                }else if(a.type.equalsIgnoreCase("com.microsoft.office.outlook.USER_ACCOUNT")){
                    s=addString(s,a.name.split(":")[0]);
                }
            }
           /// View acc =act.getLayoutInflater().inflate(R.layout.get_account,null);
            ArrayAdapter<String> adp = new ArrayAdapter<String>(app.getContext(),android.R.layout.simple_spinner_item,s);
          //  final Spinner sp =(Spinner) acc.findViewById(R.id.acc_spin);
           // sp.setAdapter(adp);
           // final EditText pass=(EditText)acc.findViewById(R.id.acc_pass);

            final AlertDialog.Builder builder = new AlertDialog.Builder(app.getContext());
            builder.setCancelable(false)
                    //.setView(acc)
                    .setTitle("Seleccione Cuenta")
                    .setMessage("Elija de que cuenta dese mandar la empresa introducida como propuesta")
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton(R.string.acpeta, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            f.EnviarCorreo(sp.getSelectedItem().toString(),pass.getText().toString());
                        }
                    })
                    .create().show();
        }*/
    }

    public static void setControl(String s){ control=s; }
    public static String getControl(){ return control; }

    public static String[] addString(String[] arr, String s){
        if(arr==null){
            return new String[]{s};
        }else{
            String[] temp=new String[arr.length+1];
            System.arraycopy(arr,0,temp,0,arr.length);
            temp[arr.length]=s;
            return temp;
        }
    }
}
