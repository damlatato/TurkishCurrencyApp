package com.example.tato.turkishlira;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class Chart extends Activity {

    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        // butonla ıslemi baslatma
        openChart();

    }

    private void openChart(){
        int[] x = { 1,2,3,4,5,6,7,8 };
        Double[] income = { 1.92, 2.01, 2.04, 2.00, 1.74, 2.01, 2.08, 2.16 };
        Double [] expense = {1.71, 1.75, 1.79, 1.77, 1.64, 1.84, 2.01, 1.98 };

        // dollar icin xy serisi olusturma
        XYSeries incomeSeries = new XYSeries("Dolar");
        // euro icin xy serisi olusturma
        XYSeries expenseSeries = new XYSeries("Euro");
        // verileri dolar ve euro serilerine ekleme
        for(int i=0;i<x.length;i++){
            incomeSeries.add(i,income[i]);
            expenseSeries.add(i,expense[i]);
        }

        // her seriyi tutmak icin dataset yaratma
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // dolari datasete aktarma
        dataset.addSeries(incomeSeries);
        // euroyu datasete aktarma
        dataset.addSeries(expenseSeries);

        // dolar serisini ayarlama icin  XYSeriesRenderer yaratma 
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.rgb(130, 130, 230));
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(2);
        incomeRenderer.setDisplayChartValues(true);

        // euro serisini ayarlama icin  XYSeriesRenderer yaratma 
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.rgb(220, 80, 80));
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(2);
        expenseRenderer.setDisplayChartValues(true);

        // butun seriyi ayarlama icin  XYSeriesRenderer yaratma 
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Dolar Chart");
        multiRenderer.setXTitle("Year 2012");
        multiRenderer.setYTitle("Amount");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0; i< x.length;i++){
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }

        // ekleme dolarRenderer and euroRenderer multipleRenderera
       
        multiRenderer.addSeriesRenderer(incomeRenderer);
        multiRenderer.addSeriesRenderer(expenseRenderer);

        // plotlama icin  dataset ve multırenderese kullana chart icin  intent yaratma 
        Intent intent = ChartFactory.getBarChartIntent(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);

        // aktivitiy baslat
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chart, menu);
        return true;
    }
}