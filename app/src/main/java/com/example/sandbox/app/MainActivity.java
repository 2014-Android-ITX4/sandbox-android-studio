package com.example.sandbox.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity
  extends ActionBarActivity
{

  @Override
  protected void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    TextView hello_world = (TextView)findViewById( R.id.hello_world );

    InputStream input_stream = null;
    BufferedOutputStream output_stream = null;
    Typeface typeface = null;

    try
    {
      // フォントを一時ファイルに
      String font_file = getCacheDir() + "/tmp" + System.currentTimeMillis() + ".raw";

      input_stream = getResources().openRawResource( R.raw.uzura );
      output_stream = new BufferedOutputStream( new FileOutputStream( font_file ) );

      byte[] buffer = new byte[ input_stream.available() ];
      int length = 0;

      while ( ( length = input_stream.read( buffer ) ) > 0 )
        output_stream.write( buffer, 0, length );

      // 読み込んだら一時ファイルを消す
      typeface = Typeface.createFromFile( font_file );
      new File(font_file).delete();

    }
    catch ( IOException e )
    { e.printStackTrace(); }
    finally
    {
      if( input_stream != null )
        try
        { input_stream.close(); }
        catch ( IOException e )
        { e.printStackTrace(); }
      if( output_stream != null )
        try
        { output_stream.close(); }
        catch ( IOException e )
        { e.printStackTrace(); }
    }

    hello_world.setTypeface( typeface );

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
