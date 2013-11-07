package br.com.achoufestas.android.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import br.com.achoufestas.android.activities.ActivityLayer;
import br.com.achoufestas.android.exception.GenericException;
import br.com.achoufestas.android.exception.SemConexaoException;
import br.com.achoufestas.android.threads.message.EnvelopeMessage;
import br.com.achoufestas.android.util.ConexaoUtil;
import br.com.achoufestas.android.util.HttpClient;
import br.com.achoufestas.lib.entidades.EventoApp;
import br.com.achoufestas.lib.messages.ListagemEventosMessage;
import br.com.achoufestas.lib.messages.LoginMessage;

import com.google.gson.Gson;

public class AcessoServidor<T> extends Thread {

	private static final String SERVER = "http://192.168.1.3:8080/achoufestas-webservices/achouFestaResource/";
	public static final String URL_LOGIN = SERVER + "login";
	public static final String URL_BUSCA_POR_COORDENADA = SERVER + "busca_coordenada";
	public static final String URL_BUSCA_POR_NOME = SERVER + "busca_nome";
	public static final String URL_MARCAR_EVENTO = SERVER + "marcar_evento";
	public static final String URL_DESMARCAR_EVENTO = SERVER + "desmarcar_evento";
	public static final String URL_CADASTRAR = SERVER + "cadastrar";

	private ActivityLayer activity;
	private Object envio;
	private T retorno;
	private String url;
	private EnvelopeMessage envelope = new EnvelopeMessage();

	static Gson gson = new Gson();

	private boolean mock = false;

	public AcessoServidor(ActivityLayer activity, Object envio, String url, T tipoRetorno) {

		this.activity = activity;
		this.envio = envio;
		this.url = url;
		retorno = tipoRetorno;

	}

	@Override
	public void run() {
		super.run();
		try {
			postToServer();
		} catch (Exception e) {
			e.printStackTrace();
			envelope.setException(new GenericException());
		}
		activity.setReturn(envelope);
	}

	private void postToServer() throws ClientProtocolException, IOException {

		if (mock) {
			if (url.equals(URL_LOGIN)) {

				LoginMessage eventos = new LoginMessage();

				List<EventoApp> ev = new ArrayList<EventoApp>();

				EventoApp ev1 = new EventoApp();
				ev1.setDescricao("Loreiupson");
				ev1.setNome("Festa 1");

				EventoApp ev2 = new EventoApp();
				ev2.setDescricao("Loreiupson");
				ev2.setNome("Festa 2");

				ev.add(ev1);
				ev.add(ev2);

				eventos.setEventos(ev);

				envelope.setOriginalMessage(eventos);
			}
			if (url.equals(URL_BUSCA_POR_COORDENADA)) {
				ListagemEventosMessage eventos = new ListagemEventosMessage();

				List<EventoApp> ev = new ArrayList<EventoApp>();

				EventoApp ev1 = new EventoApp();
				ev1.setDescricao("Loreiupson");
				ev1.setNome("Festa 111111111");

				EventoApp ev2 = new EventoApp();
				ev2.setDescricao("Loreiupson");
				ev2.setNome("Festa 222222");

				ev.add(ev1);
				ev.add(ev2);

				eventos.setEventos(ev);
				envelope.setOriginalMessage(eventos);
			}
		} else

		if (ConexaoUtil.isOnline(activity)) {

			DefaultHttpClient httpclient = new HttpClient();

			HttpPost httppostreq = new HttpPost(url);

			StringEntity se = new StringEntity(gson.toJson(envio));

			se.setContentType("application/json;");
			se.setContentEncoding("UTF-8");

			httppostreq.setEntity(se);

			// Resposta do servidor
			HttpResponse httpresponse = httpclient.execute(httppostreq);

			final int statusCode = httpresponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				envelope.setException(new SemConexaoException());
			} else {

				HttpEntity responseEntity = httpresponse.getEntity();
				InputStream resposta = responseEntity.getContent();
				Reader reader = new InputStreamReader(resposta);

				envelope.setOriginalMessage(gson.fromJson(reader, retorno.getClass()));
			}

		} else {
			envelope.setException(new SemConexaoException());
		}

	}

}
