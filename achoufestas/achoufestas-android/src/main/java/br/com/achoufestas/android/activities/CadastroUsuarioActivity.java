package br.com.achoufestas.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.achoufestas.android.R;
import br.com.achoufestas.android.service.AcessoServidor;
import br.com.achoufestas.android.threads.message.EnvelopeMessage;
import br.com.achoufestas.lib.entidades.UsuarioApp;
import br.com.achoufestas.lib.messages.DefaultMessage;

public class CadastroUsuarioActivity extends ActivityLayer {

	EditText etxtNome, etxtSenha, etxtEmail;
	ActivityLayer instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);
		instance = this;

		Button btnCadastrar = (Button) findViewById(R.id.cadastro_btn_cadastrar);

		btnCadastrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				etxtNome = (EditText) findViewById(R.id.cadastro_etxt_nome);
				etxtSenha = (EditText) findViewById(R.id.cadastro_etxt_senha);
				etxtEmail = (EditText) findViewById(R.id.cadastro_etxt_email);

				UsuarioApp user = new UsuarioApp();

				user.setEmail(etxtEmail.getText().toString());
				user.setNome(etxtNome.getText().toString());
				user.setSenha(etxtSenha.getText().toString());

				AcessoServidor<DefaultMessage> thread = new AcessoServidor<DefaultMessage>(
						instance, user, AcessoServidor.URL_CADASTRAR,
						new DefaultMessage());
				thread.start();
				mostrarLoading(instance);

			}
		});
	}

	@Override
	public void setReturn(Object object) {
		fecharLoading();
		Looper.prepare();

		EnvelopeMessage message = (EnvelopeMessage) object;

		if (message.getException() != null) {
			mensagemAviso(message.getException().getMessageResource());

		} else {

			DefaultMessage retorno = (DefaultMessage) message
					.getOriginalMessage();
			if (retorno.getErro() != null) {
				mensagemAviso(R.string.excecao_generica);
			} else {
				mensagemAviso(R.string.cadastro_sucesso);
				Intent intent = new Intent(this, AchouFestas.class);
				startActivity(intent);
				finish();
			}

		}
		Looper.loop();

	}

}
