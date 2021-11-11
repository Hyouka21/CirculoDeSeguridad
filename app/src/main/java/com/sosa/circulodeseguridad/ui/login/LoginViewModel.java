package com.sosa.circulodeseguridad.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.sosa.circulodeseguridad.entidades.Token;
import com.sosa.circulodeseguridad.entidades.Usuario;
import com.sosa.circulodeseguridad.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuario;
    private MutableLiveData<Token> tokenMD;
    private MutableLiveData<Boolean> mensaje;

    private MutableLiveData<Boolean> llamar;
    private Context context;
    private boolean estado = false;
    private int contador = 0;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        usuario = new MutableLiveData<>();
        mensaje = new MutableLiveData<>();

        llamar = new MutableLiveData<>();
        tokenMD = new MutableLiveData<>();

    }
    public MutableLiveData<Token> getTokenMD() {
        return tokenMD;
    }
    public MutableLiveData<Usuario> getUsuario() {
        return usuario;
    }

    public MutableLiveData<Boolean> getMensaje() {
        return mensaje;
    }

    public void token(Token token){

        Call<Usuario> callProp = ApiClient.getMyApiClient().obtenerUsuario("Bearer "+token.getToken());
        callProp.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {

                    Usuario p = response.body();

                    if (p != null) {

                        usuario.postValue(p);

                    } else {

                    }
                }else{
                    mensaje.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void iniciar(String email , String clave){
        Call<Token> callTok = ApiClient.getMyApiClient().login(email,clave);

        callTok.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sp = context.getSharedPreferences("datos",0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer " + response.body().getToken());
                    editor.commit();
                    tokenMD.postValue(response.body());

                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void inicioAutomatico() {
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        if(token!=null) {
            Call<Usuario> callProp = ApiClient.getMyApiClient().obtenerUsuario(token);
            callProp.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {

                        Usuario p = response.body();

                        if (p != null) {

                            usuario.postValue(p);

                        } else {

                        }
                    }else {

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}