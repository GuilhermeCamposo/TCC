package br.com.achoufestas.android.exception;

import br.com.achoufestas.android.R;

public class GenericException extends DefaultException {

	@Override
	public int getMessageResource() {
		return R.string.excecao_generica;
	}

}
