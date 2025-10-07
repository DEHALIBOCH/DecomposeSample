package kz.dehaliboch.decomposesample.person.create

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kz.dehaliboch.decomposesample.Person

class CreatePersonComponent(
    componentContext: ComponentContext,
    private val onDismiss: () -> Unit,
    private val onFinish: (Person) -> Unit
) : ComponentContext by componentContext {

    private val _state = MutableStateFlow(
        stateKeeper.consume(
            key = this::class.qualifiedName.orEmpty(),
            strategy = State.serializer()
        ) ?: State.DEFAULT
    )
    val state = _state.asStateFlow()

    init {
        stateKeeper.register(
            key = this::class.qualifiedName.orEmpty(),
            strategy = State.serializer(),
            supplier = { _state.value }
        )

        backHandler.register(BackCallback {
            if (state.value == State.DEFAULT) {
                onDismiss.invoke()
            } else {
                _state.update { State.DEFAULT }
            }
        })
    }

    fun updateName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

    fun updateAge(age: String) {
        _state.update {
            it.copy(age = age.toIntOrNull() ?: Int.MAX_VALUE)
        }
    }

    fun finish() {
        onFinish(Person(state.value.age, state.value.name))
    }

    @Serializable
    data class State(
        val name: String,
        val age: Int
    ) {
        companion object {
            val DEFAULT = State("", 0)
        }
    }
}