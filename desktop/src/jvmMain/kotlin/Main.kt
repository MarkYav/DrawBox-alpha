import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import drawbox.common.DrawBox
import drawbox.common.DrawController


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val controller = remember { DrawController() }
        DrawBox(controller = controller)
    }
}
