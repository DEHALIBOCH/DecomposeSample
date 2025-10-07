package kz.dehaliboch.decomposesample.person.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kz.dehaliboch.decomposesample.Person

class PersonDetailsComponent(
    componentContext: ComponentContext,
    val person: Person,
    private val onDelete: (Person) -> Unit = {},
) : ComponentContext by componentContext {

    val state = MutableStateFlow(State(title = person.toString()))

    private val navigation = SlotNavigation<Config>()
    val slots = childSlot(
        source = navigation,
        serializer = Config.serializer(),
        childFactory = ::createChildSlots,
    )

    fun createChildSlots(config: Config, componentContext: ComponentContext) = when(config) {
        Config.Delete -> Child.Delete
    }

    fun deletePersonClick() {
        navigation.activate(Config.Delete)
    }

    fun dismissDeletePersonDialog() {
        navigation.dismiss()
    }

    fun deleteConfirm() = onDelete(person)

    data class State(val title: String)

    @Serializable
    sealed interface Config {
        @Serializable
        data object Delete : Config
    }

    sealed interface Child {
        data object Delete : Child
    }
}