package kz.dehaliboch.decomposesample.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import kz.dehaliboch.decomposesample.Person
import kz.dehaliboch.decomposesample.person.create.CreatePersonComponent
import kz.dehaliboch.decomposesample.person.detail.PersonDetailsComponent
import kz.dehaliboch.decomposesample.person.list.PersonsComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        childFactory = ::createChild,
        initialConfiguration = Config.Persons
    )

    private val persons = PersonsComponent(childContext(PersonsComponent.KEY))

    private fun createChild(
        config: Config,
        context: ComponentContext
    ): Child {
        return when (config) {
            Config.CreatePerson -> {
                Child.CreatePerson(CreatePersonComponent(context))
            }

            is Config.Detail -> {
                Child.DetailPerson(PersonDetailsComponent(context))
            }

            Config.Persons -> {
                Child.PersonsList(persons)
            }
        }
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data object CreatePerson : Config

        @Serializable
        data object Persons : Config

        @Serializable
        data class Detail(val person: Person) : Config
    }

    sealed interface Child {

        class CreatePerson(val component: CreatePersonComponent) : Child

        class PersonsList(val component: PersonsComponent) : Child

        class DetailPerson(val component: PersonDetailsComponent) : Child
    }
}