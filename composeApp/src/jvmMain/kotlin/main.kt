import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import com.zer0s2m.keeper.App

fun main() = application {
    Window(
        title = "Keeper",
        state = rememberWindowState(width = 1200.dp, height = 700.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(1000, 400)
        App()
    }
}