package kk.kkhouse777.okogeprogressbar.okoge

import javax.swing.Icon

class OkogeProgressBarState(
    private val iconStream: OkogeIconStream = OkogeIconStream.instance,
) {
    var offset = 0
        private set
    private var velocity = 1

    val currentOkogeForwardIcon: Icon
        get() = iconStream.okogeForwardIcon

    fun updateOffset(threshold: Int) {
        offset += velocity
        if (offset <= 2) {
            offset = 2
            velocity = 1
        } else if (offset >= threshold) {
            offset = threshold
            velocity = -1
        }
    }

    fun currentOkogeIcon(): Icon =
        if (velocity > 0) {
            iconStream.okogeForwardIcon
        } else {
            iconStream.okogeBackwardIcon
        }
}
