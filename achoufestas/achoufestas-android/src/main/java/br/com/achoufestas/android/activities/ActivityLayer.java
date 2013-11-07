package br.com.achoufestas.android.activities;

import br.com.achoufestas.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

public abstract class ActivityLayer extends Activity {

	private ProgressDialog mDialog;

	protected void mensagemAviso(int resourceId) {
		String mensagem = getResources().getString(resourceId);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(mensagem);
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	/**
	 * mostra uma mensagem com um icone de carregando. utilizado para telas com
	 * processos demorados
	 * 
	 * @param context
	 */
	protected final void mostrarLoading(Context context) {
		mDialog = new ProgressDialog(context);
		mDialog.setMessage(getResources().getString(R.string.mensagem_carregando));
		mDialog.setCancelable(false);
		mDialog.show();
	}

	/**
	 * fecha a tela de carregando
	 */
	protected final void fecharLoading() {
		if (mDialog != null)
			mDialog.dismiss();
	}

	/**
	 * método padrão para o retorno de dados das threads responsáveis pelo
	 * acesso a internet
	 * 
	 * @param object
	 */
	public abstract void setReturn(Object object);

}
