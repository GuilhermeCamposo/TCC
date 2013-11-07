package br.com.achoufestas.android.exception;

import br.com.achoufestas.android.R;

public class SemConexaoException extends DefaultException {

	@Override
	public int getMessageResource() {
		return R.string.sem_conexao;
	}



	

}
