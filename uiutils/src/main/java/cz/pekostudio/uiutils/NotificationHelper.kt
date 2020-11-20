package cz.pekostudio.uiutils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Created by Lukas Urbanek on 16/09/2020.
 */

class NotificationsHelper(
    val context: Context,
    block: (NotificationsHelper.() -> Unit)? = null
) {

    init {
        block?.invoke(this)
    }

    fun getManager() = NotificationManagerCompat.from(context)

    @SuppressLint("WrongConstant")
    fun registerChannel(
        id: String,
        name: String,
        importance: Int = NotificationManagerCompat.IMPORTANCE_HIGH,
        config: (NotificationChannel.() -> Unit)? = null
    ) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel(id, name, importance).run {
                config?.invoke(this)
                getManager().createNotificationChannel(this)
            }

        }
    }

    fun postNotification(
        channelId: String,
        notificationId: Int,
        builder: NotificationCompat.Builder.() -> NotificationCompat.Builder
    ) {
        NotificationCompat.Builder(context, channelId).run {
            getManager().notify(notificationId, builder(this).build())
        }
    }

    fun cancelNotification(notificationId: Int) = getManager().cancel(notificationId)

    fun cancelAllNotifications() = getManager().cancelAll()
}

fun Context.notificationsHelper(block: NotificationsHelper.() -> Unit) {
    NotificationsHelper(this, block)
}