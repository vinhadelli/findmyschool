package nathanelucas.findmyschool;

/**
 * Created by nathan.vinhadelli on 04/09/2017.
 */

import java.util.List;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import nathanelucas.findmyschool.Activities.EscolaActivity;
import nathanelucas.findmyschool.Resposta_API.Escola;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Escola> escolas;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Escola escola) {
        escolas.add(position, escola);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        escolas.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Escola> myDataset) {
        escolas = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String escola = escolas.get(position).getNome();
        final String endereco = escolas.get(position).getEndereco().getBairro();
        if (escolas.isEmpty())
        {
            holder.txtHeader.setText("Não há Resultados!");
        }
        else {
            holder.txtHeader.setText(escola);
            holder.txtHeader.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //remove(position);
                    passToEscola(v, position);
                }
            });

            holder.txtFooter.setText(endereco);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return escolas.size();
    }

    public void passToEscola(View view, int position){
        Intent i = new Intent(view.getContext(), EscolaActivity.class);
        i.putExtra("nome", escolas.get(position).getNome());
        i.putExtra("bairro", escolas.get(position).getEndereco().getBairro());
        i.putExtra("municipio", escolas.get(position).getEndereco().getMunicipio());
        i.putExtra("cep", escolas.get(position).getEndereco().getCep());
        i.putExtra("telefone", escolas.get(position).getTelefone());
        i.putExtra("categoria", escolas.get(position).getCategoriaEscolaPrivada());
        i.putExtra("lati",escolas.get(position).getLatitude());
        i.putExtra("longi",escolas.get(position).getLongitude());
        view.getContext().startActivity(i);
    }

}