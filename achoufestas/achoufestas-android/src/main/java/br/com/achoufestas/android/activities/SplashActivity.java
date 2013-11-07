package br.com.achoufestas.android.activities;

import br.com.achoufestas.android.R;
import br.com.achoufestas.android.R.anim;
import br.com.achoufestas.android.R.id;
import br.com.achoufestas.android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity  implements Runnable {
   
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.splash_screen);
       ImageView myImageView= (ImageView)findViewById(R.id.imageLogoSplash);
       Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.animacao_splash);
       myImageView.startAnimation(myFadeInAnimation);
       Handler h = new Handler();
		h.postDelayed(SplashActivity.this, 2000);
		
   }

	public void run() {
		startActivity(new Intent(this, AchouFestas.class));
		finish();		
	}
}
