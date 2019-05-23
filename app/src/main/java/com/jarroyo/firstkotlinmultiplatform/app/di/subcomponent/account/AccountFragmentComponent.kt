package com.jarroyo.firstkotlinmultiplatform.app.di.subcomponent.account

import com.jarroyo.firstkotlinmultiplatform.ui.account.fragment.AccountFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AccountFragmentModule::class))
interface AccountFragmentComponent {
    fun injectTo(fragment: AccountFragment)
}