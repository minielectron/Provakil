package com.androidcodeshop.provakil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientFormActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> states;
    private static final String MANDATORY_SPANNABLE_ASTRISK = "*";
    @BindView(R.id.heading_tv)
    TextView headingTv;
    @BindView(R.id.client_details_group)
    LinearLayout clientDetailsGroup;
    @BindView(R.id.first_name_et)
    TextInputEditText firstNameEt;
    @BindView(R.id.first_name_til)
    TextInputLayout firstNameTil;
    @BindView(R.id.last_name_et)
    TextInputEditText lastNameEt;
    @BindView(R.id.last_name_til)
    TextInputLayout lastNameTil;
    @BindView(R.id.client_code_et)
    TextInputEditText clientCodeEt;
    @BindView(R.id.client_code_til)
    TextInputLayout clientCodeTil;
    @BindView(R.id.address_line_one_et)
    TextInputEditText addressLineOneEt;
    @BindView(R.id.address_line_one_til)
    TextInputLayout addressLineOneTil;
    @BindView(R.id.address_line_two_et)
    TextInputEditText addressLineTwoEt;
    @BindView(R.id.address_line_two_til)
    TextInputLayout addressLineTwoTil;
    @BindView(R.id.city_et)
    TextInputEditText cityEt;
    @BindView(R.id.city_til)
    TextInputLayout cityTil;
    @BindView(R.id.states_spinner)
    Spinner statesSpinner;
    @BindView(R.id.pin_et)
    TextInputEditText pinEt;
    @BindView(R.id.pin_til)
    TextInputLayout pinTil;
    @BindView(R.id.address_type_label)
    TextView addressTypeLabel;
    @BindView(R.id.home_rb)
    RadioButton homeRb;
    @BindView(R.id.office_rb)
    RadioButton officeRb;
    @BindView(R.id.home_type_rg)
    RadioGroup homeTypeRg;
    @BindView(R.id.contact_info_label)
    TextView contactInfoLabel;
    @BindView(R.id.contact_number_et)
    EditText contactNumberEt;
    @BindView(R.id.contact_info_llo)
    GridLayout contactInfoLlo;
    @BindView(R.id.advance_details_group)
    LinearLayout advanceDetailsGroup;
    @BindView(R.id.operational_state_spinner)
    Spinner operationalStateSpinner;
    @BindView(R.id.extra_details_et)
    EditText extraDetailsEt;
    @BindView(R.id.submit_btn)
    Button submitBtn;
    private ClientDetailsModel mlientDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_form);
        ButterKnife.bind(this);
        setStateSpinners();
        submitBtn.setOnClickListener(this);
    }

    private void setStateSpinners() {
        states = new ArrayList<>();
        ArrayList<String> operationalStates = new ArrayList<>();
        String stateJson = loadJSONFromAsset();
        try {
            if (stateJson != null) {
                JSONArray array = new JSONArray(stateJson);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject state = array.getJSONObject(i);
                    states.add(state.getString("name"));
                    operationalStates.add(state.getString("name"));
                }
                states.add(0, "Select State");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_background_layout, states);
        ArrayAdapter<String> spinnerOpAdapter = new ArrayAdapter<>(this, R.layout.spinner_background_layout, operationalStates);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_popover_layout);
        spinnerOpAdapter.setDropDownViewResource(R.layout.spinner_popover_layout);
        statesSpinner.setAdapter(spinnerAdapter);
        operationalStates.add(0, "Select Operational State");
        operationalStateSpinner.setAdapter(spinnerOpAdapter);
    }


    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("states.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ClientListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
