package com.dag.homerent.composebase.navcontroller

sealed class NavScreen(val route:String) {

    object SplashScreen : NavScreen("splash_screen")
    object WelcomeScreen : NavScreen("welcome_screen")
    object PhoneScreen:NavScreen("phone_screen")
    object PasswordScreen:NavScreen("password_screen")
    object LoginScreen:NavScreen("login_screen")
    object HomeScreen:NavScreen("home_screen")
    object HomeListScreen:NavScreen("homelist_screen")
    object MainActivity:NavScreen("mainactivity_screen")

}

fun NavScreen.addData(data:Any):String{
    return this.route.plus(data)
}

fun NavScreen.addPath(path:String):String{
    return this.route.plus("/").plus(path)
}