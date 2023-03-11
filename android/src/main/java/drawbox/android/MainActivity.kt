package drawbox.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import drawbox.common.DrawBox
import drawbox.common.DrawController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val controller = remember { DrawController() }
                DrawBox(controller = controller)
            }
        }
    }
}