package kz.dehaliboch.decomposesample.util.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle

@Composable
fun defaultPreviewComponentContext(): ComponentContext {

    check(LocalInspectionMode.current) {
        "rememberPreviewComponentContext() should be called only in Preview"
    }

    val lifecycle = remember {
        object : Lifecycle {
            override val state: Lifecycle.State
                get() = Lifecycle.State.RESUMED

            override fun subscribe(callbacks: Lifecycle.Callbacks) = Unit

            override fun unsubscribe(callbacks: Lifecycle.Callbacks) = Unit
        }
    }

    return remember {
        DefaultComponentContext(
            lifecycle = lifecycle
        )
    }
}