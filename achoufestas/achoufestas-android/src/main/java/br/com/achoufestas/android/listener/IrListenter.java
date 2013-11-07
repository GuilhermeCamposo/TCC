package br.com.achoufestas.android.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.achoufestas.android.R;
import br.com.achoufestas.android.activities.ActivityLayer;
import br.com.achoufestas.android.service.AcessoServidor;
import br.com.achoufestas.lib.entidades.EventoApp;
import br.com.achoufestas.lib.entidades.UsuarioApp;
import br.com.achoufestas.lib.messages.DefaultMessage;
import br.com.achoufestas.lib.messages.MarcacaoEventoMessage;

public class IrListenter implements OnClickListener {

	private EventoApp evento;
	private UsuarioApp user;
	private ActivityLayer tela;

	public IrListenter(ActivityLayer tela, EventoApp evento, UsuarioApp usuario) {
		this.evento = evento;
		user = usuario;
		this.tela = tela;
	}

	public void onClick(View v) {
		Button botao = (Button) v;
		AcessoServidor<DefaultMessage> thread = null;
		MarcacaoEventoMessage message = new MarcacaoEventoMessage();
		message.setIdEvento(evento.getIdEvento());
		message.setIdUsuario(user.getIdUsuario());
		

		if (botao.getText().toString().equals("Ir")) {
			thread = new AcessoServidor<DefaultMessage>(tela, message, AcessoServidor.URL_MARCAR_EVENTO, new DefaultMessage());
			botao.setText(R.string.going);
		} else {
			thread = new AcessoServidor<DefaultMessage>(tela, message, AcessoServidor.URL_DESMARCAR_EVENTO, new DefaultMessage());
			botao.setText(R.string.go);
		}
		thread.start();

	}
}
