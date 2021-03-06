package com.idealorb.tiltfx;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Locale;

public class ConverterActivity extends AppCompatActivity {

    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    private LineChart mChart;
    private TextView exchangedCurrencyTextView;
    private RadioButton cryptoRadioButton;
    private EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_converter);

        exchangedCurrencyTextView = findViewById(R.id.exchanged_currency_text_view);
        Button exchangeButton = findViewById(R.id.exchange_button);
        cryptoRadioButton = findViewById(R.id.cryptoRadioButton);
        RadioButton currencyRadioButton = findViewById(R.id.currencyRadioButton);
        inputField = findViewById(R.id.cryptocurrency_input);



        Intent dataFrom = getIntent();
        final String dataArray[] = dataFrom.getStringArrayExtra("CurrencyData");

        currencyRadioButton.setText(dataArray[0]);
        cryptoRadioButton.setText(dataArray[2]);
        exchangedCurrencyTextView.setText(R.string.default_currency_value);
        inputField.setText(R.string.default_currency_value);


        mTfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");

        //draw market cap chart
        setChartToDraw();


        exchangeButton.setOnClickListener(v -> {

            String valueToDisplay;
            if (cryptoRadioButton.isChecked()) {

                double userValue = Double.parseDouble(inputField.getText().toString());
                Log.v("uservalue", Double.toString(userValue));

                double exchangeRate = Double.parseDouble(dataArray[1]);

                Log.v("exchangerate", Double.toString(exchangeRate));
                double exchangeValue = userValue * exchangeRate;
                valueToDisplay = String.format(Locale.getDefault(), "%.2f", exchangeValue);
                Log.v("valueToDisplay", valueToDisplay);
                exchangedCurrencyTextView.setText(valueToDisplay);
            } else {


                double userValue = Double.parseDouble(inputField.getText().toString());
                Log.v("uservalue", Double.toString(userValue));

                double exchangeRate = Double.parseDouble(dataArray[1]);

                Log.v("exchangerate", Double.toString(exchangeRate));

                double exchangeValue = userValue / exchangeRate;

                valueToDisplay = String.format(Locale.getDefault(), "%.5f", exchangeValue);
                exchangedCurrencyTextView.setText(valueToDisplay);
            }
        });

//        inputField.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                return false;
//            }
//        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }


    private void setChartToDraw() {
        mChart = findViewById(R.id.marketcap_chart);

        if (mChart == null) {
            Log.v("CubicLineChartActivity", "Null ref");
        } else {
            Log.v("CubicLineChartActivity", mChart.toString());
        }
        //mChart.setViewPortOffsets(0, 0, 0, 0);
        mChart.setBackgroundColor(Color.rgb(41, 129, 134));
        mChart.setExtraOffsets(5f, 5f, 5f, 5f);


        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setMaxHighlightDistance(300);

        XAxis x = mChart.getXAxis();
        x.setTypeface(mTfLight);
        x.setLabelCount(6, false);
        x.setTextColor(Color.WHITE);
        x.setDrawLabels(true);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(true);
        x.setAxisLineWidth(2.0f);
        x.setAxisLineColor(Color.BLACK);
        x.setEnabled(true);

        YAxis y = mChart.getAxisLeft();
        y.setTypeface(mTfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setDrawGridLines(true);
        y.setAxisLineColor(Color.WHITE);
        y.setAxisLineWidth(2.0f);
        y.setAxisLineColor(Color.BLACK);
        y.setAxisMinimum(1f);


        mChart.getAxisRight().setEnabled(false);

        // add data
        setData(45, 100);

        mChart.getLegend().setEnabled(false);

        mChart.animateXY(2000, 2000);

        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 1;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter((dataSet, dataProvider) -> -10);

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTypeface(mTfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mChart.setData(data);
        }
    }

}
