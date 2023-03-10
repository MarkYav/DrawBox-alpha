import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import drawbox.common.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
