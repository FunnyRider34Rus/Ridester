package com.funnyrider34rus.ridester.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.funnyrider34rus.ridester.core.components.RidesterBottomNavBar
import com.funnyrider34rus.ridester.core.util.ANIMATENAVTIME
import com.funnyrider34rus.ridester.ui.chat.ScreenChat
import com.funnyrider34rus.ridester.ui.dashboard.DashboardViewModel
import com.funnyrider34rus.ridester.ui.dashboard.ScreenDashboard
import com.funnyrider34rus.ridester.ui.dashboard.add_post.DashboardAddPostDialogViewModel
import com.funnyrider34rus.ridester.ui.dashboard.add_post.ScreenDashboardAddPostDialog
import com.funnyrider34rus.ridester.ui.login.LogInViewModel
import com.funnyrider34rus.ridester.ui.login.ScreenLogIn
import com.funnyrider34rus.ridester.ui.profile.ScreenProfile
import com.funnyrider34rus.ridester.ui.ride.ScreenRide
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Navigation(navController: NavHostController, startDestination: String) {

    Scaffold(
        bottomBar = {
            RidesterBottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            route = Screen.NAVGRAPH.route
        ) {
            composable(route = Screen.LOGIN.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },

                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                }
            ) {
                val viewModel = hiltViewModel<LogInViewModel>()
                val state by viewModel.viewState.collectAsState()
                ScreenLogIn(
                    navigateToMain = { navController.navigate(Screen.DASHBOARDLIST.route) },
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            composable(
                route = Screen.DASHBOARDLIST.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },

                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                }
            ) {
                val viewModel = hiltViewModel<DashboardViewModel>()
                val state by viewModel.viewState.collectAsState()
                ScreenDashboard(
                    modifier = modifier.fillMaxSize(),
                    navigateToComment = { },
                    navigateToAddPost = { navController.navigate(Screen.DASHBOARDADDPOST.route) },
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            composable(route = Screen.DASHBOARDADDPOST.route,
                enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(ANIMATENAVTIME)
                )
            },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },

                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                }
            ) {
                val viewModel = hiltViewModel<DashboardAddPostDialogViewModel>()
                val state by viewModel.viewState.collectAsState()

                ScreenDashboardAddPostDialog(navigateToBack = {
                    navController.navigate(Screen.DASHBOARDLIST.route) {
                        popUpTo(Screen.DASHBOARDLIST.route)
                    }
                },
                state = state,
                onEvent = viewModel::onEvent)
            }

            composable(route = Screen.CHAT.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },

                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                }
            ) {
                ScreenChat(modifier = modifier.fillMaxSize())
            }

            composable(route = Screen.RIDE.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },

                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                }
            ) {
                ScreenRide(modifier = modifier.fillMaxSize())
            }

            composable(route = Screen.PROFILE.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },

                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(ANIMATENAVTIME)
                    )
                }
            ) {
                ScreenProfile(modifier = modifier.fillMaxSize())
            }
        }
    }
}

sealed class Screen(val route: String) {
    object NAVGRAPH : Screen(route = "root_nav_graph")
    object LOGIN : Screen(route = "login_screen")
    object DASHBOARDLIST : Screen(route = "dashboard_list")
    object DASHBOARDCOMMENT : Screen(route = "dashboard_comment")
    object DASHBOARDADDPOST : Screen(route = "dashboard_add_post")
    object CHAT : Screen(route = "chat")
    object RIDE : Screen(route = "ride")
    object PROFILE : Screen(route = "profile")
}

