package br.com.achoufestas.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.achoufestas.android.R;
import br.com.achoufestas.android.R.id;
import br.com.achoufestas.android.R.layout;
import br.com.achoufestas.android.R.string;
import br.com.achoufestas.android.service.AcessoServidor;
import br.com.achoufestas.android.threads.message.EnvelopeMessage;
import br.com.achoufestas.lib.entidades.UsuarioApp;
import br.com.achoufestas.lib.messages.LoginMessage;

public class AchouFestas extends ActivityLayer implements OnClickListener {
	
	Activity instance;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);
		instance = this;

		Button btnLogar = (Button) findViewById(R.id.btnLogar);
		btnLogar.setOnClickListener(this);

		Button btnCadastrar = (Button) findViewById(R.id.login_btn_cadastrar);
		
		btnCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(instance, CadastroUsuarioActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		//TODO remove mock
		EditText senha = (EditText) findViewById(R.id.textSenha);
		EditText login = (EditText) findViewById(R.id.textLogin);
		senha.setText("123");
		login.setText("admin");
		//
		
	}


	public void onClick(View v) {

		String senha = ((EditText) findViewById(R.id.textSenha)).getText().toString();
		String login = ((EditText) findViewById(R.id.textLogin)).getText().toString();

		if ((senha == null || login == null) || senha.trim().equals("") || login.trim().equals("")) {
			mensagemAviso(R.string.login_incompleto);
		} else {
			logarUsuario(login, senha);
		}

	}

	private void logarUsuario(String login, String senha) {
		UsuarioApp usuario = new UsuarioApp();
		usuario.setEmail(login);
		usuario.setSenha(senha);

		AcessoServidor<LoginMessage> thread = new AcessoServidor<LoginMessage>(this, usuario, AcessoServidor.URL_LOGIN, new LoginMessage());
		thread.start();
		mostrarLoading(this);
	}

	@Override
	public void setReturn(Object object) {
		Looper.prepare();
		fecharLoading();
		try {
			EnvelopeMessage message = (EnvelopeMessage) object;

			if (message.getException() != null) {
				mensagemAviso(message.getException().getMessageResource());
			} else {

				Intent intent = new Intent(this, HomeActivity.class);
				intent.putExtra("message", (LoginMessage) message.getOriginalMessage());
				startActivity(intent);
				finish();
			}
		} catch (Exception e) {
			mensagemAviso(R.string.excecao_generica);
			e.printStackTrace();
		}
		Looper.loop();
	}

}
