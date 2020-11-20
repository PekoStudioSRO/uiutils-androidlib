package cz.pekostudio.uiutilstestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cz.pekostudio.uiutils.formatCurrency
import cz.pekostudio.uiutils.formatDecimal
import java.util.*

/**
 * @author Miroslav Hýbler
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val debugTag = "uiutils-debug"
        val num = 123456
        val numD = 123456.789
        val numF = 123456.789f

        Log.d(debugTag, "Int.formatDecimal() -> ${num.formatDecimal()}")
        Log.d(debugTag,"Int.formatDecimal(2) -> ${num.formatDecimal(2)}")

        Log.d(debugTag, "Double.formatDecimal() -> ${numD.formatDecimal()}")
        Log.d(debugTag,"Double.formatDecimal(2) -> ${numD.formatDecimal(2)}")

        Log.d(debugTag, "Float.formatDecimal() -> ${numF.formatDecimal()}")
        Log.d(debugTag,"Float.formatDecimal(2) -> ${numF.formatDecimal(2)}")

        Log.d(debugTag, "Int.toCurrency() -> ${num.formatCurrency()}")
        Log.d(debugTag, "Double.toCurrency(Locale.UK) ->  ${numD.formatCurrency(Locale.UK)}")
        Log.d(debugTag, "Float.toCurrency() ->  ${numF.formatCurrency()}")

    }
}
