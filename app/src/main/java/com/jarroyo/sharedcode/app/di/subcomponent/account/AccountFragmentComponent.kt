package com.jarroyo.sharedcode.app.di.subcomponent.account

import com.jarroyo.sharedcode.ui.account.fragment.AccountFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AccountFragmentModule::class))
interface AccountFragmentComponent {
    fun injectTo(fragment: AccountFragment)
}