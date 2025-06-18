package com.example.soninhotech.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class SplashActivity extends AppCompatActivity {


    private static final long SPLASH_DISPLAY_LENGTH = 3000; // Duração da splash screen em milissegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Instala a splash screen. Esta linha DEVE vir antes de super.onCreate()
        // e antes de qualquer setContentView() ou manipulação de UI.
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        // NÃO É NECESSÁRIO chamar setContentView(R.layout.activity_splash) se você está usando
        // o tema da splash screen. O drawable já é o fundo da janela.

        // Usa um Handler para atrasar o início da próxima Activity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
            @Override
            public void run() {
                // Cria uma Intent para iniciar a Activity principal do seu app
                Intent mainIntent = new Intent(SplashActivity.this, login_activity.class); // <<--- SUBSTITUA MainActivity.class pela sua Activity principal
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish(); // Finaliza a SplashActivity para que ela não possa ser acessada pelo botão Voltar
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}