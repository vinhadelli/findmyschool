package nathanelucas.findmyschool.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import nathanelucas.findmyschool.AdapterComments;
import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.UserComment;

public class EscolaCommentsActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private EditText mCommentField;
    private List<UserComment> mListComments = new LinkedList<>();
    private RecyclerView mRecyclerViewComments;
    private AdapterComments mAdapterComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escola_comments);

        String nomeEscola = getIntent().getExtras().getString("nome");

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Escolas").child(nomeEscola);
        mCommentField = (EditText) findViewById(R.id.commentField);

        mRecyclerViewComments = (RecyclerView) findViewById(R.id.commentsList);
        mAdapterComments = new AdapterComments(mListComments, this);
        mRecyclerViewComments.setAdapter(mAdapterComments);
        mRecyclerViewComments.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mListComments.clear();
                for(DataSnapshot eachDataSnapshot : dataSnapshot.getChildren()){

                    mListComments.add(eachDataSnapshot.getValue(UserComment.class));
                }
                mAdapterComments.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }

        });
    }

    @Override
    public void onClick(View v) {

        UserComment us = new UserComment(FirebaseAuth.getInstance().getCurrentUser().getEmail(), mCommentField.getText().toString());

//        mRef.child(mRef.push().getKey())
//                .setValue(new UserComment(FirebaseAuth.getInstance().getCurrentUser().getEmail(), mCommentField.getText().toString()));

        mRef.child(mRef.push().getKey())
                .setValue(us);

        mListComments.add(us);
        mAdapterComments.notifyDataSetChanged();

        mCommentField.setText("");
    }
}
