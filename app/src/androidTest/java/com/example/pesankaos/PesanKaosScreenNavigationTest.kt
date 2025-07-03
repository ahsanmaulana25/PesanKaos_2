package com.example.pesankaos

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.pesankaos.ui.theme.PesanKaosApp
import com.example.pesankaos.ui.theme.PesanKaosScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class PesanKaosScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupPesanKaosNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            PesanKaosApp(navController = navController)
        }
    }
    fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        assertEquals(
            expectedRouteName,
            currentBackStackEntry?.destination?.route
        )
    }
    @Test
    fun pesankaosNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(PesanKaosScreen.Start.name)
    }
    @Test
    fun pesankaosNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText =
            composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }
    @Test
    fun pesankaosNavHost_clickSatuKaos_navigatesToSelectColorScreen() {
        val satukaosText =
            composeTestRule.activity.getString(R.string.satu_kaos)
        composeTestRule.onNodeWithContentDescription(satukaosText).performClick()
        navController.assertCurrentRouteName(PesanKaosScreen.Color.name)
    }
}
