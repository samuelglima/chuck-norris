package com.example.chucknorris.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.navigation.NavController
import androidx.navigation.NavDirections

class Util {
}

fun NavController.safeNavigate(@IdRes destinationActionId: Int, @Nullable args: Bundle? = null) {
    currentDestination?.getAction(destinationActionId)?.let {
        navigate(destinationActionId, args)
    }
}

fun NavController.safeNavigate(directions: NavDirections) {
    currentDestination?.getAction(directions.actionId)?.let {
        navigate(directions)
    }
}