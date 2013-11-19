package br.com.achoufestas.android.activities;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.achoufestas.android.R;
import br.com.achoufestas.android.exception.GenericException;
import br.com.achoufestas.android.service.AcessoServidor;
import br.com.achoufestas.android.threads.message.EnvelopeMessage;
import br.com.achoufestas.lib.entidades.EventoApp;
import br.com.achoufestas.lib.entidades.UsuarioApp;
import br.com.achoufestas.lib.messages.DefaultMessage;
import br.com.achoufestas.lib.messages.MarcacaoEventoMessage;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class EventoActivity extends ActivityLayer implements OnClickListener {

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
		TextView data = (TextView) findViewById(R.id.evento_txt_data);

		button = (Button) findViewById(R.id.btnEventGoing);
		button.setOnClickListener(this);

		ImageView imagem = (ImageView) findViewById(R.id.evento_iv_flayer);

		UrlImageViewHelper.setUrlDrawable(imagem, evento.getFotoUrl());

		if (user.getEventosMarcados() != null
				&& user.getEventosMarcados().contains(evento)) {
			button.setText(R.string.going);
		}

		nome.setText(evento.getNome());
		local.setText(evento.getLocal());
		description.setText(evento.getDescricao());
		data.setText(evento.getDataEvento());
	}

	@Override
	public void setReturn(Object object) {
		Looper.prepare();

		EnvelopeMessage message = (EnvelopeMessage) object;

		if (message.getException() != null) {
			mensagemAviso(message.getException().getMessageResource());

		} else {
			DefaultMessage dmessage = (DefaultMessage) message
					.getOriginalMessage();
			if (dmessage.getException() != null) {
				mensagemAviso(new GenericException().getMessageResource());
			} else {
				button.setText(R.string.going);
				button.refreshDrawableState();
			}
		}
		Looper.loop();
	}

	public void onClick(View v) {
		Button botao = (Button) v;

		AcessoServidor<DefaultMessage> thread = null;
		MarcacaoEventoMessage message = new MarcacaoEventoMessage();

		message.setIdEvento(evento.getIdEvento());
		message.setIdUsuario(user.getIdUsuario());

		if (botao.getText().toString().equals("Ir")) {
			thread = new AcessoServidor<DefaultMessage>(this, message,
					AcessoServidor.URL_MARCAR_EVENTO, new DefaultMessage());
		} else {
			thread = new AcessoServidor<DefaultMessage>(this, message,
					AcessoServidor.URL_DESMARCAR_EVENTO, new DefaultMessage());
		}
		mostrarLoading(this);
		thread.start();
	}

}
