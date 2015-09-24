package com.jitse.example.activities;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;

import com.jitse.example.R;
import com.plattysoft.leonids.ParticleSystem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RainActivity extends Activity {

    @InjectView(R.id.btn_red_dots)
    Button mBtnOneShot;

    @InjectView(R.id.btn_emit_beans)
    Button mBtnEmit;

    @InjectView(R.id.btn_emit_flakes)
    Button mBtnFlakes;

    @InjectView(R.id.btn_santa_run)
    Button mBtnSantaRun;

    @InjectView(R.id.btn_emit_shoes)
    Button mBtnEmitShoes;


    ParticleSystem bean1;
    ParticleSystem bean2;

    ParticleSystem snow1;
    ParticleSystem snow2;
    boolean beanToggle = false;
    boolean flakeToggle = false;

    String santaUrl = "http://www.carlswebgraphics.com/christmas/3-animated-santa-1.gif";
    private AnimationDrawable mSantaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);

        ButterKnife.inject(this);
    }



    @OnClick(R.id.btn_red_dots)
    public void startRain() {
        int numParticles = 15;
        new ParticleSystem(this, numParticles, R.drawable.red_dot, 10000)
                .setSpeedRange(0.2f, 0.5f)
                .oneShot(mBtnOneShot, numParticles);
    }

    @OnClick(R.id.btn_emit_beans)
    public void emitBeans() {
        if (!beanToggle) {
            bean1 = new ParticleSystem(this, 80, R.drawable.green_bean, 10000);
            bean2 = new ParticleSystem(this, 80, R.drawable.pink_bean, 10000);

            bean1.setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
                    .setRotationSpeed(144)
                    .setAcceleration(0.00005f, 90)
                    .emit(findViewById(R.id.view_top_right), 8);

            bean2.setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                    .setRotationSpeed(144)
                    .setAcceleration(0.00005f, 90)
                    .emit(findViewById(R.id.view_top_left), 8);
            mBtnEmit.setText("TOGGLE Beans OFF");
        } else {
            bean1.stopEmitting();
            bean2.stopEmitting();
            mBtnEmit.setText("TOGGLE Beans ON");
        }

        beanToggle = !beanToggle;
    }

    @OnClick(R.id.btn_santa_run)
    public void santaRun() {
        int numParticles = 1;
        new ParticleSystem(this, numParticles, R.drawable.santa_animation, 10000)
                .setSpeedModuleAndAngleRange(0.05f, 0.05f, 0, 0)
                .oneShot(mBtnSantaRun, numParticles);
    }

    @OnClick(R.id.btn_emit_flakes)
    public void emitFlakes() {
        if (!flakeToggle) {
            snow1 = new ParticleSystem(this, 80, R.drawable.snowflakes, 10000);
            snow2 = new ParticleSystem(this, 80, R.drawable.snowflakes, 10000);

            snow1.setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
                    .setRotationSpeed(144)
                    .setAcceleration(0.00005f, 90)
                    .emit(findViewById(R.id.view_top_right), 8);

            snow2.setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                    .setRotationSpeed(144)
                    .setAcceleration(0.00005f, 90)
                    .emit(findViewById(R.id.view_top_left), 8);
            mBtnFlakes.setText("TOGGLE Snow Flakes OFF");
        } else {
            snow1.stopEmitting();
            snow2.stopEmitting();
            mBtnFlakes.setText("TOGGLE Snow Flakes ON");
        }

        flakeToggle = !flakeToggle;
    }
    @OnClick(R.id.btn_emit_shoes)
    public void shoeExplosion() {
        new ParticleSystem(this, 1, R.drawable.bk, 10000)
                .setSpeedModuleAndAngleRange(0.1f, 0.1f, 0, 0)
                .setRotationSpeed(150)
                .setAcceleration(0.00015f, 90)
                .oneShot(mBtnEmitShoes, 1);

        new ParticleSystem(this, 1, R.drawable.baker, 10000)
                .setSpeedModuleAndAngleRange(0.1f, 0.1f, 45, 45)
                .setRotationSpeed(150)
                .setAcceleration(0.00015f, 90)
                .oneShot(mBtnEmitShoes, 1);

        new ParticleSystem(this, 1, R.drawable.nb1, 10000)
                .setSpeedModuleAndAngleRange(0.1f, 0.1f, 90, 90)
                .setRotationSpeed(150)
                .setAcceleration(0.00015f, 90)
                .oneShot(mBtnEmitShoes, 1);

        new ParticleSystem(this, 1, R.drawable.nb2, 10000)
                .setSpeedModuleAndAngleRange(0.1f, 0.1f, 135, 135)
                .setRotationSpeed(150)
                .setAcceleration(0.00015f, 90)
                .oneShot(mBtnEmitShoes, 1);

        new ParticleSystem(this, 1, R.drawable.bk, 10000)
                .setSpeedModuleAndAngleRange(0.2f, 0.1f, 180, 180)
                .setRotationSpeed(150)
                .setAcceleration(0.00035f, 90)
                .oneShot(mBtnEmitShoes, 1);

        new ParticleSystem(this, 1, R.drawable.baker, 10000)
                .setSpeedModuleAndAngleRange(0.2f, 0.1f, 225, 225)
                .setRotationSpeed(150)
                .setAcceleration(0.00035f, 90)
                .oneShot(mBtnEmitShoes, 1);

        new ParticleSystem(this, 1, R.drawable.nb1, 10000)
                .setSpeedModuleAndAngleRange(0.2f, 0.1f, 270, 270)
                .setRotationSpeed(150)
                .setAcceleration(0.00035f, 90)
                .oneShot(mBtnEmitShoes, 1);

        new ParticleSystem(this, 1, R.drawable.nb2, 10000)
                .setSpeedModuleAndAngleRange(0.2f, 0.1f, 315, 315)
                .setRotationSpeed(150)
                .setAcceleration(0.00035f, 90)
                .oneShot(mBtnEmitShoes, 1);
    }
}
