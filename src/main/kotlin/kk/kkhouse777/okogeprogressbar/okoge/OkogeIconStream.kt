package kk.kkhouse777.okogeprogressbar.okoge

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.swing.Icon

class OkogeIconStream(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    private var currentSign = true
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    init {
        // TODO リークはとりあえず気にしてない
        scope.launch {
            while (true) {
                currentSign = !currentSign
                delay(500)
            }
        }
    }

    val okogeForwardIcon: Icon
        get() = if (currentSign) OkogeIcons.OkogeForwardIcon else OkogeIcons.OkogeForwardIcon2

    val okogeBackwardIcon: Icon
        get() = if (currentSign) OkogeIcons.OkogeBackwardIcon else OkogeIcons.OkogeBackwardIcon2

    companion object {
        val instance: OkogeIconStream by lazy { OkogeIconStream() }
    }
}
