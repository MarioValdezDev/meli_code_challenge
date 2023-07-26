package mx.mariovaldez.melicodechallenge

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import mx.mariovaldez.melicodechallenge.actions.ClickItem
import mx.mariovaldez.melicodechallenge.actions.ScrollToBottom
import mx.mariovaldez.melicodechallenge.search.presentation.SearchActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UITest {

    private lateinit var scenario: ActivityScenario<SearchActivity>
    private lateinit var context: Context

    @Test
    fun fullTest() {
        scenario = ActivityScenario.launch(SearchActivity::class.java)
        context = InstrumentationRegistry.getInstrumentation().targetContext

        val queries = listOf("halo", "laptop", "redmi", "pelota", "lampara")

        var iterator = 0

        while (iterator < queries.size) {
            search(queries[iterator % 5])
            results()
            iterator++
        }
    }

    private fun search(query: String) {
        onView(withId(R.id.search_text_input_edit_text)).perform(click())
        onView(withId(R.id.search_text_input_edit_text)).perform(typeText(query))
        onView(withId(R.id.search_text_input_edit_text)).perform(ViewActions.pressImeActionButton())
    }

    private fun results() {
        var accumulate = 0
        Thread.sleep(2000)
        for (k in 0 until 2) {
            for (i in 0 until 5) {
                onView(withId(R.id.products_recycler_view)).perform(
                    actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        accumulate + 1,
                        ClickItem(R.id.product_container)
                    )
                )
                Espresso.pressBack()
                accumulate++
            }
            onView(withId(R.id.products_recycler_view)).perform(ScrollToBottom())
        }
        Espresso.pressBack()
    }
}
