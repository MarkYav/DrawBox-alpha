import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import drawbox.common.DrawBox
import drawbox.common.DrawController


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val controller = remember { DrawController() }
        val bitmap by controller.drawnBitmapState

        Row {
            DrawBox(
                controller = controller,
                modifier = Modifier
                    .size(500.dp)
                    .padding(100.dp)
                    .border(width = 1.dp, color = Color.Blue),
            )
            Column {
                Text("Result:")
                bitmap?.let {
                    Image(
                        it,
                        contentDescription = "drawn bitmap",
                        modifier = Modifier.size(250.dp)
                    )
                }
            }
        }
    }
}
