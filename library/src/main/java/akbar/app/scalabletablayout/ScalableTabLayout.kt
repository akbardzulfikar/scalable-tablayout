package akbar.app.scalabletablayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kotlin.math.roundToInt

class ScalableTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.tabStyle
) : TabLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val tabStrip = getChildAt(0) as? ViewGroup
        val childCount = tabStrip?.childCount ?: 0

        if (childCount > 0 && tabStrip != null) {
            var widthPixels = MeasureSpec.getSize(widthMeasureSpec)

            // Convert 16dp to px per item (approximate default paddings)
            val perItemPaddingPx = (16 * resources.displayMetrics.density).roundToInt()
            widthPixels -= perItemPaddingPx * childCount

            if (widthPixels > 0) {
                val tabMinWidth = widthPixels / childCount
                var remainder = widthPixels % childCount

                for (i in 0 until childCount) {
                    val child = tabStrip.getChildAt(i)
                    child.minimumWidth = if (remainder > 0) {
                        remainder--
                        tabMinWidth + 1
                    } else {
                        tabMinWidth
                    }
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}