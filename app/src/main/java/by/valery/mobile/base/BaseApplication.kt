package by.valery.mobile.base

import android.app.Application
import by.valery.mobile.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {
	
	override fun onCreate() {
		super.onCreate()
		startKoin {
			modules(Modules.allModules)
			androidContext(this@BaseApplication)
		}
		
		Timber.plant(Timber.DebugTree())
	}
}
