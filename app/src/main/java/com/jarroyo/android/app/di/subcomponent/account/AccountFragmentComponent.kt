package com.jarroyo.android.app.di.subcomponent.account

import com.jarroyo.android.ui.account.fragment.AccountFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AccountFragmentModule::class))
interface AccountFragmentComponent {
    fun injectTo(fragment: AccountFragment)
}