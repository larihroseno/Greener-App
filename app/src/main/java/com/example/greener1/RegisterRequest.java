package com.example.greener1;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//Allow us to make a request to the register.php file on the server and get response as String
public class RegisterRequest extends StringRequest {

    //specify the URL that the register.php file are
    //static final because is not gonna change, and string because return an url
private static final String REGISTER_REQUEST_URL="https://greenerapp.000webhostapp.com/Register.php";

private Map<String, String> params;

public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener){
//pass data to volley
// when volley finish the request it informs the listener
    super(Method.POST, REGISTER_REQUEST_URL, listener,null);



    //pass information to the request
    // creating params
    params= new HashMap<>();
    params.put("name", name);
    params.put("username", username);
    params.put("password", password);
    //here we add string to convert from integer to string
    params.put("age", age + "");

// When the request is execute volley with call params and get params will return params which is filled with dat above

}
 @Override
 public Map<String, String> getParams() {
     return params;
 }





}
