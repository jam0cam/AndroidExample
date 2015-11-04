package com.jitse.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.wallet.FullWallet;
import com.jitse.example.Constants;
import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AndroidPayCompleteActivity extends Activity {

    FullWallet mFullWallet;

    @InjectView(R.id.txt_shipping_address)
    TextView mTxtShipping;

    @InjectView(R.id.txt_billing_address)
    TextView mTxtBilling;

    @InjectView(R.id.txt_cc)
    TextView mTxtCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_pay_complete);
        ButterKnife.inject(this);

        mFullWallet = getIntent().getParcelableExtra(Constants.EXTRA_FULL_WALLET);

        UserAddress shipAddr = mFullWallet.getBuyerShippingAddress();
        mTxtShipping.setText(shipAddr.getName() + "\n" +
                shipAddr.getAddress1() + "\n" +
                shipAddr.getAddress2() + "\n" +
                shipAddr.getLocality() + "," + shipAddr.getAdministrativeArea() + "," + shipAddr.getPostalCode() + "\n" +
                shipAddr.getCountryCode()
        );

        UserAddress billAddr = mFullWallet.getBuyerBillingAddress();
        mTxtBilling.setText(billAddr.getName() + "\n" +
                        billAddr.getAddress1() + "\n" +
                        billAddr.getAddress2() + "\n" +
                        billAddr.getLocality() + "," + billAddr.getAdministrativeArea() + "," + billAddr.getPostalCode() + "\n" +
                        billAddr.getCountryCode()
        );

        mTxtCC.setText(mFullWallet.getProxyCard().getPan());
    }
}
