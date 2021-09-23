package com.example.hi.gamedapchuot;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Begin extends AppCompatActivity {
    ImageView btnPlay, btnHightScore, btnAbout, btn_sound_controll, btnweapon;
    Dialog dialog;
    List<ScoreOBJS> list;
    Adapter_Score adapter_score;
    GridView grid;
    ImageView img1,img2;
    private static boolean isPlaySound = true;
    String[] name = {
            "Thor Hammer",
            "Tông lào huyền thoại",
    } ;
    int[] imageId = {
            R.drawable.thor2,
            R.drawable.tonglao2,
    };
    int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        //khởi động nhạc nền
        //gọi đến service
        startService(new Intent(Begin.this, servicerunmp3.class));

        btn_sound_controll = findViewById(R.id.btn_sound_controll);
        btnPlay = findViewById(R.id.btn_play);
        btnHightScore = findViewById(R.id.btn_HightScore);
        btnAbout = findViewById(R.id.btn_About);
        btnweapon=findViewById(R.id.btn_weapon);
        grid=findViewById(R.id.grid);
        img1=findViewById(R.id.img_vk);
        img2=findViewById(R.id.img_vk2);
        list = new ArrayList<>();
        adapter_score = new Adapter_Score(list);

        btn_sound_controll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaySound) {
                    stopService(new Intent(Begin.this, servicerunmp3.class));
                    btn_sound_controll.setImageResource(R.drawable.turnoff_sound);
                    isPlaySound=false;
                } else {
                    startService(new Intent(Begin.this, servicerunmp3.class));
                    btn_sound_controll.setImageResource(R.drawable.btn_turnon_sound);
                    isPlaySound=true;
                }
            }
        });

        btnPlay.setOnClickListener((View v) -> {
            Intent intent=new Intent(Begin.this,MainActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("number",k);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        //hiện postup
        btnHightScore.setOnClickListener((View v) -> {
            View view = getLayoutInflater().inflate(R.layout.dialog_hight_score, null);

            ImageView imageViewClose = view.findViewById(R.id.btn_close);
            RecyclerView recyclerView = view.findViewById(R.id.recycler_higtScore);

            //cấu hình của recyclerview
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Begin.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter_score);
            //lấy dữ liệu đổ ra recyclerview
            list.clear();
            list.addAll(HightScoreController.getHightScore());
            //cấu hình cho dialog
            dialog = new Dialog(Begin.this);
            //set view cho dialog
            dialog.setContentView(view);
            //cho phép đóng dialog khi ấn ra ngoài dialog
            dialog.setCancelable(true);
            //hiển thị dialog
            dialog.show();
            for (int i = 0; i < 10; i++) {
                //refresh layout
                adapter_score.notifyItemChanged(i);
            }
            imageViewClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        });

        btnAbout.setOnClickListener((View v) -> {
            View view = getLayoutInflater().inflate(R.layout.dialog_about, null);
            ImageView imageViewClose = view.findViewById(R.id.btn_close);
            dialog = new Dialog(Begin.this);
            dialog.setContentView(view);
            dialog.setCancelable(true);
            dialog.show();
            imageViewClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        });

        AtomicBoolean i= new AtomicBoolean(false);
        btnweapon.setOnClickListener((View v) -> {
            if(!i.get()){
                grid.setVisibility(View.VISIBLE);
                i.set(true);

            }
            else{
                grid.setVisibility(View.GONE);
                i.set(false);
            }
        });

        CustomGrid adapter = new CustomGrid(Begin.this, name, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(Begin.this, "You choose " +name[+position], Toast.LENGTH_SHORT).show();
            grid.setVisibility(View.GONE);
            i.set(false);
            k=position;
        }
        });
    }
}
