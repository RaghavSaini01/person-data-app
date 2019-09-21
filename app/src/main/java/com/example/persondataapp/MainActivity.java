package com.example.persondataapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.persondataapp.Person.personAge;
import static com.example.persondataapp.Person.personName;

public class MainActivity extends AppCompatActivity {

    private TextView jsonText;
    private RequestQueue queue;
    ArrayList<Person>peopleList;
    private MyAdapter myAdapter;
    private  RecyclerView personRecycler;
    private Button sortNames, sortAges;

    private String url = "https://raw.githubusercontent.com/patrickfeltes/sample-hackillinois/master/people.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonText = findViewById(R.id.jsonText);
        queue = Volley.newRequestQueue(this);

        sortNames = findViewById(R.id.sortNames);
        sortAges = findViewById(R.id.sortAges);

        personRecycler = findViewById(R.id.personrecycler);

        jsonText.setText("People's information will appear in the Recycler Below");

        jsonParse();

        sortNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(peopleList, personName);
                setUpRecycler();

                /*Toast toDoName = Toast.makeText(getApplicationContext(), "This will sort names alphabetically once done",
                        Toast.LENGTH_SHORT);

                toDoName.show();*/
            }
        });

        sortAges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(peopleList, personAge);
                setUpRecycler();
                /*Toast toDoAges = Toast.makeText(getApplicationContext(), "This will sort ages in ascending order once done",
                        Toast.LENGTH_SHORT);

                toDoAges.show();*/
            }
        });

    }

    private void jsonParse() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            peopleList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                Person human = new Person("", 0, "");
                                JSONObject person = response.getJSONObject(i);

                                human.setName(person.getString("first_name") + " " + person.getString("last_name"));
                                human.setAge(person.getInt("age"));
                                human.setSchool(person.getString("school"));

                                peopleList.add(human);
                            }

                            setUpRecycler();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jsonText.setText("That didn't work!");

            }
        });

        queue.add(jsonArrayRequest);
    }

    private void setUpRecycler() {
        myAdapter = new MyAdapter(this, peopleList);
        personRecycler.setAdapter(myAdapter);
        personRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }

}
