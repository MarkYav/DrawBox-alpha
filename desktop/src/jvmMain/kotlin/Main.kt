import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import drawbox.common.DrawBox
import drawbox.common.DrawController

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val controller = remember { DrawController() }
        val bitmap by controller.getBitmapFlow(250).collectAsState(ImageBitmap(250, 250, ImageBitmapConfig.Argb8888))

        controller.background = DrawController.DrawBoxBackground.ColourBackground(color = Color.Blue, alpha = 0.25f)

        Row {
            DrawBox(
                controller = controller,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .padding(100.dp)
                    .border(width = 1.dp, color = Color.Blue),
            )
            Column {
                Text("Result:")
                Image(
                    bitmap,
                    contentDescription = "drawn bitmap",
                    modifier = Modifier.size(250.dp).border(width = 1.dp, color = Color.Red),
                )
            }
        }
    }
}
