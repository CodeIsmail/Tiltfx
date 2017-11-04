package com.idealorb.tiltfx;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idealorb.tiltfx.dbproperties.Currency;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ismail on 11/4/2017.
 */

class CurrencyAdapter extends ArrayAdapter{


    static class ViewHolder {
        public TextView currencyTextView;
        public TextView bitcoinValueTextView;
        public ImageView exchangeRateStateImageView;
    }

    public CurrencyAdapter(Context context, ArrayList<Currency> currenries) {
        super(context, 0, currenries);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View listItemView = convertView;

        // reuse views
        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.currency_view, parent, false);

            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.currencyTextView = listItemView
                    .findViewById(R.id.currency_textview);
            viewHolder.bitcoinValueTextView = listItemView
                    .findViewById(R.id.bitcoin_value_textview);
            viewHolder.exchangeRateStateImageView = listItemView
                    .findViewById(R.id.exchange_rate_state);


            listItemView.setTag(viewHolder);
        }

        //fill data
        ViewHolder holder = (ViewHolder) listItemView.getTag();
        Currency  currency = (Currency) getItem(position);
        Log.v("CurrencyFX", currency.getCurrencyTagName());



        // Check if the existing view is being reused, otherwise inflate the view

        holder.currencyTextView.setText(currency.getCurrencyTagName());

        //format exchange rate to be in decimal place
        String exchangeRate = String.format(Locale.getDefault(),
                "%10.2f", currency.getBitcoinExchangeRate());
        holder.bitcoinValueTextView.setText(exchangeRate);

        holder.exchangeRateStateImageView.setImageResource(R.drawable.increase);

        return listItemView;
    }
}
