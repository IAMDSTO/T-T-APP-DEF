import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ttdef.R

class SpinnerStatus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_pedido)

        // Vincula el Spinner
        val spinnerEstado = findViewById<Spinner>(R.id.spinnerEstado)

        // Configura el adapter para el Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.opciones_estado, // Asegúrate de tener este array en strings.xml
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstado.adapter = adapter

        // Configura un listener para manejar la selección
        spinnerEstado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val opcionSeleccionada = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@SpinnerStatus, "Seleccionaste: $opcionSeleccionada", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Manejar caso donde no hay selección
            }
        }
    }
}
