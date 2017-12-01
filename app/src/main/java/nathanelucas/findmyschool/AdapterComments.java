package nathanelucas.findmyschool;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lsrio on 29/11/2017.
 */

public class AdapterComments extends RecyclerView.Adapter {

    List<UserComment> comments;
    Context context;

    public AdapterComments(List<UserComment> comments, Context context) {

        this.comments = comments;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.comment_layout, parent, false);

        return new HolderComments(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HolderComments holderComments = (HolderComments) holder;

        UserComment userComment = comments.get(position);
        holderComments.email.setText(userComment.getUserMail());
        holderComments.comment.setText(userComment.getCommentText());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    private class HolderComments extends RecyclerView.ViewHolder{

        final TextView email;
        final TextView comment;

        public HolderComments(View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.emailComment);
            comment = itemView.findViewById(R.id.bodyComment);
        }
    }
}
