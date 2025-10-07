package kz.dehaliboch.decomposesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import kz.dehaliboch.decomposesample.root.RootComponent
import kz.dehaliboch.decomposesample.root.RootScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val component = RootComponent(defaultComponentContext())

        setContent {
            RootScreen(component)
        }
    }
}