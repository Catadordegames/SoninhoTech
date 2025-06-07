package com.example.soninhotech.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.data.AppDatabase;
import com.example.soninhotech.data.entity.Bebe;

import java.util.List;

public class selection_peril_activity extends AppCompatActivity {

    private LinearLayout layoutContainer;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_perfil);

        layoutContainer = findViewById(R.id.LinearLayout2);

        // Obter instância do banco de dados
        db = AppDatabase.getInstance(getApplicationContext());

        mostrarBebes();
    }

    private void mostrarBebes() {
        List<Bebe> bebeList = db.bebeDao().getAll();

        if (bebeList != null) {
            for (Bebe bebe : bebeList) {
                if (bebe.foto != null && bebe.foto.exists()) {
                    ImageView imageView = new ImageView(this);

                    // Carregar a imagem do arquivo na ImageView
                    Bitmap bitmap = BitmapFactory.decodeFile(bebe.foto.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);

                    // Configurações opcionais para a ImageView (tamanho, margens, escala)
                    int tamanho = (int) getResources().getDimension(R.dimen.tamanho_foto_bebe); // ou um valor fixo, ex: 200
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tamanho, tamanho);
                    params.setMargins(16, 16, 16, 16);
                    imageView.setLayoutParams(params);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    layoutContainer.addView(imageView);
                }
            }
        }
    }

}
