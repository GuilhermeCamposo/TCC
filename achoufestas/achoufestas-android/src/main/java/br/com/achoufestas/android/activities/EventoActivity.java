package br.com.achoufestas.android.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.achoufestas.android.R;
import br.com.achoufestas.android.listener.IrListenter;
import br.com.achoufestas.lib.entidades.EventoApp;
import br.com.achoufestas.lib.entidades.UsuarioApp;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class EventoActivity extends ActivityLayer {

	private EventoApp evento;
	private Button button;
	private UsuarioApp user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.evento);
		
		Bundle extras = getIntent().getExtras();
		evento = (EventoApp) extras.getSerializable("evento");
		user = (UsuarioApp) extras.getSerializable("user");

		TextView nome = (TextView) findViewById(R.id.txtEventName);
		TextView local = (TextView) findViewById(R.id.txtEventLocal);
		TextView description = (TextView) findViewById(R.id.txtEventDescription);
		button = (Button) findViewById(R.id.btnEventGoing);
		button.setOnClickListener(new IrListenter(this, evento, user));

		ImageView imagem = (ImageView) findViewById(R.id.evento_iv_flayer);

		UrlImageViewHelper.setUrlDrawable(imagem, evento.getFoto());
		
		nome.setText(evento.getNome());
		local.setText(evento.getLocal());
		description.setText(evento.getDescricao());
	}

	@Override
	public void setReturn(Object object) {
		button.refreshDrawableState();
		
	}

}
