package win.gdzrch.kotlinsample

import android.app.Application
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger


/**
 * Created by w on 2017/5/25.
 */
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.init("sample")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
    }
}