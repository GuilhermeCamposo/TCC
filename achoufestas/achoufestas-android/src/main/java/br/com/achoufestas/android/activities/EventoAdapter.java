package br.com.achoufestas.android.activities;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.achoufestas.android.R;
import br.com.achoufestas.android.listener.IrListenter;
import br.com.achoufestas.lib.entidades.EventoApp;
import br.com.achoufestas.lib.entidades.UsuarioApp;

public class EventoAdapter extends ArrayAdapter<EventoApp> {

	private List<EventoApp> items;
	private Context context;
	private View view;
	private UsuarioApp user;
	private ActivityLayer tela;

	public int getCount() {
		return items.size();
	}

	public EventoAdapter(Activity atividade, int textViewResourceId, List<EventoApp> items) {
		super(atividade.getApplicationContext(), textViewResourceId, items);
		user = ((HomeActivity) atividade).getUser();
		this.items = items;
		tela = (ActivityLayer) atividade;
		this.context = atividade.getApplicationContext();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		view = convertView;

		if (view == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R.layout.evento_adapter, null);
		}

		EventoApp evento = items.get(position);

		if (evento != null) {
			TextView local = (TextView) view.findViewById(R.id.lblLocal);
			TextView nome = (TextView) view.findViewById(R.id.lblNome);
			Button botao = (Button) view.findViewById(R.id.btnIr);
			ImageView imagem = (ImageView) view.findViewById(R.id.ivFlayer);

			//UrlImageViewHelper.setUrlDrawable(imagem, evento.getFoto());
			
			imagem.setOnClickListener(new ImageListener(evento));
			botao.setOnClickListener(new IrListenter(tela, evento, user));
			
			if(user.getEventosMarcados() != null && user.getEventosMarcados().contains(evento)){
				botao.setText(R.string.going);
			}
			
			nome.setText(evento.getNome());
			local.setText(evento.getLocal());

		}
		return view;
	}

	private class ImageListener implements OnClickListener {

		private EventoApp evento;

		ImageListener(EventoApp evento) {
			this.evento = evento;
		}

		public void onClick(View v) {

			Intent it = new Intent(context, EventoActivity.class);
			it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			it.putExtra("evento", evento);
			it.putExtra("user", user);
			context.startActivity(it);

		}

	}

}
