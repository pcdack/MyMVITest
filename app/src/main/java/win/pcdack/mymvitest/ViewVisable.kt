package win.pcdack.mymvitest

import android.view.View

/**
 * Created by pcdack on 17-12-30.
 *
 */
var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if(value) View.VISIBLE else View.GONE
    }