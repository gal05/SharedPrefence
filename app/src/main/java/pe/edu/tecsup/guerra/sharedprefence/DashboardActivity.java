package pe.edu.tecsup.guerra.sharedprefence;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import pe.edu.tecsup.guerra.sharedprefence.Models.User;
import pe.edu.tecsup.guerra.sharedprefence.repository.UserRepository;

public class DashboardActivity extends AppCompatActivity {


    private static final String TAG = DashboardActivity.class.getSimpleName();

    // SharedPreferences

    private SharedPreferences sharedPreferences;

    private TextView usernameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        usernameText = (TextView)findViewById(R.id.fullname_text);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);

        usernameText.setText(user.getFullname());
    }

    public void callLogout(View view){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended

        finish();
    }
    public void tonext(View view){

        startActivity(new Intent(this, MyPreferencesActivity.class));
        finish();

    }

    }

