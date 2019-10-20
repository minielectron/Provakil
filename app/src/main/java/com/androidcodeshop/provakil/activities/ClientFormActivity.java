package com.androidcodeshop.provakil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidcodeshop.provakil.R;
import com.androidcodeshop.provakil.data.ClientDataList;
import com.androidcodeshop.provakil.datamodels.AddressType;
import com.androidcodeshop.provakil.datamodels.ClientDetailsModel;
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
import electrophile.mutils.MiniValidationUtils;

public class ClientFormActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String IS_REFRESH = "is refresh";
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
    @BindView(R.id.contact_type_spinner)
    Spinner contactTypeSpinner;
    private ClientDetailsModel mClientDetails;
    private String selectedAddreesType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_form);
        ButterKnife.bind(this);
        setStateSpinners();
        realtimeValidationOnEditText();
        submitBtn.setOnClickListener(this);
    }

    private void realtimeValidationOnEditText() {
        firstNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!MiniValidationUtils.isValidName(firstNameEt.getText().toString())) {
                    firstNameEt.setError("Please provide valid name");
                    firstNameEt.requestFocus();
                } else firstNameEt.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lastNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(lastNameEt.getText()))
                    lastNameEt.setError(null);
                else if (!MiniValidationUtils.isValidName(lastNameEt.getText().toString())) {
                    lastNameEt.setError("Please provide valid name");
                    lastNameEt.requestFocus();
                } else lastNameEt.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addressLineOneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(addressLineOneEt.getText().length() > 0)
                    addressLineOneTil.setError(null);
            }
        });

        pinEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!MiniValidationUtils.isValidPincode(pinEt.getText().toString())){
                    pinEt.setError("Provide valid pin");
                    pinEt.requestFocus();
                }else pinEt.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    /**
     * This will set the both state and operational state spinner from states.json file
     */
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

        homeTypeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home_rb:
                        selectedAddreesType = "Home";
                        break;
                    case R.id.office_rb:
                        selectedAddreesType = "Office";
                        break;
                }
            }
        });
    }


    /**
     * This Method will read the JSON file and convert that to string and return json string.
     */
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

        mClientDetails = new ClientDetailsModel();
        // Get All the simple data from client form
        String firstName = String.valueOf(firstNameEt.getText()); // should be mandatory
        String lastName = String.valueOf(lastNameEt.getText());
        String clientCode = String.valueOf(clientCodeEt.getText()); // should be mandatory
        String addressLineOne = String.valueOf(addressLineOneEt.getText());
        String addressLineTwo = String.valueOf(addressLineTwoEt.getText());
        String city = String.valueOf(cityEt.getText());
        String pin = String.valueOf(pinEt.getText());
        String contactNumber = String.valueOf(contactNumberEt.getText());
        // Validate everything
        if (!MiniValidationUtils.isValidName(firstName)) {
            firstNameEt.setError("Please provide the valid name");
            firstNameEt.requestFocus();
        } else if (TextUtils.isEmpty(clientCode)) {
            clientCodeEt.setError("Please provide the client code");
            clientCodeEt.requestFocus();
        } else if (TextUtils.isEmpty(addressLineOne)) {
            addressLineOneEt.setError("Please provide the address");
            addressLineOneEt.requestFocus();
        } else if (!MiniValidationUtils.isValidPincode(pin)) {
            pinEt.setError("Please provide valid pincode");
            pinEt.requestFocus();
        } else if (!MiniValidationUtils.isValidPhone(contactNumber)) {
            contactNumberEt.setError("Provide valid contact number");
            contactNumberEt.requestFocus();
        } else {

            if (!TextUtils.isEmpty(lastName)) {
                if (!MiniValidationUtils.isValidName(lastName)) {
                    lastNameTil.setError("Please provide the valid name");
                    lastNameEt.requestFocus();
                    return;
                }
            }
            // get other data and validate
            String state = "";
            if (statesSpinner.getSelectedItemPosition() > 0) {
                state = (String) statesSpinner.getSelectedItem();
            } // state is not mandatory so not reverting back to user, if they didn't select

            AddressType contactType = null;
            if (contactTypeSpinner.getSelectedItemPosition() > 0) {
                contactType = new AddressType(contactTypeSpinner.getSelectedItemPosition() - 1, (String) contactTypeSpinner.getSelectedItem());
            } else {
                Toast.makeText(this, "Please select the contact type", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = homeTypeRg.getCheckedRadioButtonId();
            AddressType addressType;
            switch (id) {
                case R.id.home_rb:
                    addressType = new AddressType(0, selectedAddreesType);
                    break;
                case R.id.office_rb:
                    addressType = new AddressType(0, selectedAddreesType);
                    break;
                default:
                    addressType = new AddressType(0, "Home");
            }

            String operationalState = "";
            if (operationalStateSpinner.getSelectedItemPosition() > 0) {
                operationalState = (String) operationalStateSpinner.getSelectedItem();
            }// not mandatory

            String extraDetails = String.valueOf(extraDetailsEt.getText());

            mClientDetails.setmFirstName(firstName);
            mClientDetails.setmLastName(lastName);
            mClientDetails.setmClientCode(clientCode);
            mClientDetails.setmAddressLineOne(addressLineOne);
            mClientDetails.setmAddressLineTwo(addressLineTwo);
            mClientDetails.setmCity(city);
            mClientDetails.setmPin(pin);
            mClientDetails.setmState(state);
            mClientDetails.setmAddressType(addressType);
            mClientDetails.setmContactType(contactType);
            mClientDetails.setmContactNumber(contactNumber);
            mClientDetails.setmOperationalState(operationalState);
            mClientDetails.setmExtraDetails(extraDetails);

            ClientDataList.getStoredData().add(0, mClientDetails);
            Intent intent = new Intent(this, ClientListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(IS_REFRESH, "yes");
            startActivity(intent);

        }

    }
}
