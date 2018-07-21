package dxexwxexy.activities.Unit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import dxexwxexy.activities.R;

public class ConversionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conversion_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ftom:
                Toast.makeText(this, "Feet to Meters", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itoc:
                Toast.makeText(this, "Inch to Centimeters", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ptog:
                Toast.makeText(this, "Pound to Grams", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.quit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
