package com.example.sandbox.app;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.List;


public class MainActivity
  extends ActionBarActivity
{
    private SensorManager sensor_manager;

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    TextView sensor_view = (TextView)findViewById( R.id.sensor );
    sensor_manager = (SensorManager)getSystemService( SENSOR_SERVICE );
    List<Sensor> sensors = sensor_manager.getSensorList( Sensor.TYPE_ALL );
    String sensors_text = "";
    for(Sensor s : sensors){
      sensors_text += s.getName() + "\n";
    }
    sensor_view.setText( sensors_text );
  }



  @Override
  public boolean onCreateOptionsMenu( Menu menu )
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate( R.menu.main, menu );
    return true;
  }

  @Override
  public boolean onOptionsItemSelected( MenuItem item )
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if ( id == R.id.action_settings )
    {
      return true;
    }
    return super.onOptionsItemSelected( item );
  }
}
