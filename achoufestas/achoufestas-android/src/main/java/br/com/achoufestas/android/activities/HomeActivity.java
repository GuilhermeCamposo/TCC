package br.com.achoufestas.android.activities;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.com.achoufestas.android.R;
import br.com.achoufestas.android.service.AcessoServidor;
import br.com.achoufestas.android.threads.message.EnvelopeMessage;
import br.com.achoufestas.android.util.GeoLocationConf;
import br.com.achoufestas.lib.entidades.Coordenada;
import br.com.achoufestas.lib.entidades.EventoApp;
import br.com.achoufestas.lib.entidades.UsuarioApp;
import br.com.achoufestas.lib.messages.ListagemEventosMessage;
import br.com.achoufestas.lib.messages.LoginMessage;

public class HomeActivity extends ActivityLayer implements LocationListener {

	private List<EventoApp> eventos;
	private ListView listView;
	private Button btnProx, btnBuscaNome;
	private HomeActivity instance;
	private UsuarioApp user;

	LocationManager locationManager;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home);

		Bundle extras = getIntent().getExtras();
		LoginMessage message = (LoginMessage) extras.getSerializable("message");
		btnProx = (Button) findViewById(R.id.btnNear);
		btnBuscaNome = (Button) findViewById(R.id.btnSearchName);
		user = message.getUsuario();
		instance = this;

		eventos = message.getEventos();
		listView = (ListView) findViewById(R.id.lista_eventos);

		atualizarListView();

		btnProx.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				
				if (locationManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					mostrarLoading(instance);
					locationManager
							.requestLocationUpdates(
									LocationManager.GPS_PROVIDER,
									GeoLocationConf.MINIMUM_TIME_BETWEEN_UPDATES,
									GeoLocationConf.MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
									instance);
					locationManager
							.requestLocationUpdates(
									LocationManager.NETWORK_PROVIDER,
									GeoLocationConf.MINIMUM_TIME_BETWEEN_UPDATES,
									GeoLocationConf.MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
									instance);

				} else {
					mensagemAviso(R.string.gps_inativo);
				}
			}
		});

		btnBuscaNome.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				String busca = ((EditText) findViewById(R.id.editTextName))
						.getText().toString();

				AcessoServidor<ListagemEventosMessage> thread = new AcessoServidor<ListagemEventosMessage>(
						instance, busca, AcessoServidor.URL_BUSCA_POR_NOME,
						new ListagemEventosMessage());
				thread.start();
			}
		});

	}

	/**
	 * Apos uma leitura da localizacao a envia para o server
	 * 
	 * @param localizacao
	 *            obtida
	 */
	private void enviarCoordenada(Location location) {
		Coordenada coordenda = new Coordenada();
		coordenda.setLatitude(location.getLatitude());
		coordenda.setLongitude(location.getLongitude());

		AcessoServidor<ListagemEventosMessage> thread = new AcessoServidor<ListagemEventosMessage>(
				instance, coordenda, AcessoServidor.URL_BUSCA_POR_COORDENADA,
				new ListagemEventosMessage());
		thread.start();
	}

	/**
	 * atualiza a lista de eventos
	 */
	private void atualizarListView() {
		try {
			EventoAdapter adapter = new EventoAdapter(instance,
					android.R.layout.simple_list_item_1, eventos);
			listView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setReturn(Object object) {

		ListagemEventosMessage list;
		fecharLoading();
		boolean erro = false;

		Looper.prepare();

		EnvelopeMessage message = (EnvelopeMessage) object;

		if (message.getException() != null) {
			mensagemAviso(message.getException().getMessageResource());

		} else {
			if (message.getOriginalMessage() instanceof ListagemEventosMessage) {
				list = (ListagemEventosMessage) message.getOriginalMessage();
				if (list.getException() != null) {
					mensagemAviso(R.string.excecao_generica);
					erro = true;
				} else {
					eventos = list.getEventos();
				}
			}
		}
		Looper.loop();

		if (!erro) {
			atualizarListView();
			listView.refreshDrawableState();
		}

	}

	public UsuarioApp getUser() {
		return user;
	}

	// TODO melhorar a captação da coordenada
	@Override
	public void onLocationChanged(Location location) {
		locationManager.removeUpdates(instance);
		enviarCoordenada(location);
	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
