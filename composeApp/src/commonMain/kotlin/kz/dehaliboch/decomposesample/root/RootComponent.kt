package kz.dehaliboch.decomposesample.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import kz.dehaliboch.decomposesample.Person
import kz.dehaliboch.decomposesample.person.create.CreatePersonComponent
import kz.dehaliboch.decomposesample.person.detail.PersonDetailsComponent
import kz.dehaliboch.decomposesample.person.list.PersonsComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val persons = PersonsComponent(
        componentContext = childContext(key = PersonsComponent.KEY),
        onAddNewPersonClick = ::onAddNewPerson,
        onPersonClick = ::onPersonClicked
    )

    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        childFactory = ::createChild,
        initialConfiguration = Config.Persons,
        handleBackButton = true,
    )

    private fun onPersonClicked(person: Person) {
        navigation.pushNew(Config.Detail(person))
    }

    private fun onAddNewPerson() {
        navigation.pushNew(Config.CreatePerson)
    }

    private fun onCreatePerson(person: Person) {
        navigation.pop()
        persons.updatePerson(person)

//        val child = stack.value.active.instance
//        if (child is Child.Persons) {
//            child.component.updatePerson(person)
//        }
    }

    private fun onDismissCreatePersonScreen() {
        navigation.pop()
    }

    private fun onDeletePerson(person: Person) {
        navigation.pop()
        persons.deletePerson(person)
    }

    private fun createChild(
        config: Config,
        context: ComponentContext
    ): Child {
        return when (config) {
            Config.CreatePerson -> {
                Child.CreatePerson(
                    CreatePersonComponent(
                        componentContext = context,
                        onDismiss = ::onDismissCreatePersonScreen,
                        onFinish = ::onCreatePerson,
                    )
                )
            }

            is Config.Detail -> {
                Child.PersonDetails(
                    PersonDetailsComponent(
                        componentContext = context,
                        person = config.person,
                        onDelete = ::onDeletePerson
                    )
                )
            }

            Config.Persons -> {
                Child.Persons(persons)
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

        class Persons(val component: PersonsComponent) : Child

        class PersonDetails(val component: PersonDetailsComponent) : Child
    }
}