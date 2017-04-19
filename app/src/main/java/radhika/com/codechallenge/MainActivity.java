package radhika.com.codechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;
import java.util.ArrayList;

import radhika.com.codechallenge.dto.Comment;
import radhika.com.codechallenge.service.ISocketActivity;
import radhika.com.codechallenge.service.ServerAsyncTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ISocketActivity {

    /**
     * Declaring the varaibles*/
    EditText etComment,etName;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views and get the id from xml
        etName = (EditText) findViewById(R.id.etName);
        etComment = (EditText) findViewById(R.id.etComment);
        btSubmit = (Button) findViewById(R.id.btSubmit);

        btSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(etName.length() == 0 || etComment.length()==0)
            Toast.makeText(this,getResources().getString(R.string.error_empty_fields),Toast.LENGTH_LONG).show();
        else
        {
            Comment comment = new Comment();
            comment.setName(etName.getText().toString());
            comment.setComments(etComment.getText().toString());
            ServerAsyncTask serverAsyncTask = new ServerAsyncTask(this);
            //Start the AsyncTask execution
            serverAsyncTask.execute(new Comment[] {comment});
        }
    }

    /*
    * called when the object is sent suucessfully*/
    @Override
    public void onSocketMessage(String value) {

        if(value.equals("success"))
        {
            Toast.makeText(this,getResources().getString(R.string.success_msg),Toast.LENGTH_LONG).show();
            etName.getText().clear();
            etComment.getText().clear();
        }
        else
            Toast.makeText(this,getResources().getString(R.string.error_server),Toast.LENGTH_LONG).show();


    }
}
