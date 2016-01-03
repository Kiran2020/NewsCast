package com.example.narayankoirala.androidreside;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Narayan Koirala on 10/11/2015.
 */
public class LoginFragment extends Fragment {
    String KEY_NAME = "name";
    String KEY_PASSWORD = "password";
    String getUrl = "http://endeavour.site50.net/JSON/signup.php";
    RequestQueue requestQueue;
    boolean success;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;
    ProgressDialog progressDialog;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _emailText = (EditText) view.findViewById(R.id.input_email);
        _passwordText = (EditText) view.findViewById(R.id.input_password);
        _loginButton = (Button) view.findViewById(R.id.btn_login);
        requestQueue = Volley.newRequestQueue(getActivity());
        _signupLink = (TextView) view.findViewById(R.id.link_signup);
        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
        _loginButton.setAlpha((float) 0.7);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Taking In...");
                progressDialog.show();
                //  login();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            }


        });

    }

    public void login() {
        final String passwords = _passwordText.getText().toString().trim();

        final String emails = _emailText.getText().toString().trim();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getUrl, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    JSONArray datas = response.getJSONArray("signup");
                    for (int i = 0; i < datas.length(); i++) {
                        JSONObject data = datas.getJSONObject(i);
                        String serverEmail = data.getString("email");
                        String serverName = data.getString("name");
                        String serverPaassword = data.getString("password");
                        if (emails.equals(serverEmail) && passwords.equals(serverPaassword)) {
                            progressDialog.dismiss();
                            //   Intent intent = new Intent(getContext(), MainActivity.class);
                            //      intent.putExtra("name", serverName);
                            //    intent.putExtra("email",serverEmail);
                            //    startActivity(intent);
                            break;
                        }
                    }

                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "ERROR JSON" + e, Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}


