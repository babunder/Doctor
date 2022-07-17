package demo.com.mydoctors.fragment

import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import demo.com.mydoctors.Pref
import demo.com.mydoctors.R
import demo.com.mydoctors.TextUtils

class FragmentHomeMedicineKit : Fragment(), OnTouchListener {
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private var imageView: ImageView? = null
    var currentLanguage: String? = null
    var LANGUAGE_TYPE_ENGLISH = "en"
    var LANGUAGE_TYPE_HINDI = "hi"
    var LANGUAGE_TYPE_MARATHI = "mr"
    var SCREEN_GI_HOME_MED_KIT_TAG = "Home Medicine Kit"

    // These matrices will be used to scale points of the image
    var matrix = Matrix()
    var savedMatrix = Matrix()
    var mode = NONE

    // these PointF objects are used to record the point(s) the user is touching
    var start = PointF()
    var mid = PointF()
    var oldDist = 1f
    private var mImageResource = 0
    private var currentView: View? = null


    /*public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.title = SCREEN_GI_HOME_MED_KIT_TAG
        setContentView(R.layout.activity_general_info_home_med_kit)
        currentLanguage = Pref.getmInstance(context).language
        imageView = findViewById(R.id.imp_img)
        imageView.setImageResource(imageResource)
        imageView.setOnTouchListener(this)
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.activity_general_info_home_med_kit, container, false)
        }
        currentLanguage = Pref.getmInstance(context).language
        currentView?.let { initView(it) }
        return currentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TextUtils.hideActionBar((activity as AppCompatActivity?)!!)
        super.onCreate(savedInstanceState)
    }

    private fun initView(view: View) {
        view.findViewById<TextView>(R.id.tvTitle).text = SCREEN_GI_HOME_MED_KIT_TAG
        view.findViewById<ImageView>(R.id.imp_img).setImageResource(getImageResource())
        view.findViewById<ImageView>(R.id.imp_img).setOnTouchListener(this)

    }

    fun getImageResource(): Int {
        mImageResource = R.drawable.medinesenglish
        if (currentLanguage.equals(LANGUAGE_TYPE_ENGLISH, ignoreCase = true)) {
            mImageResource = R.drawable.medinesenglish
        } else if (currentLanguage.equals(LANGUAGE_TYPE_HINDI, ignoreCase = true)) {
            mImageResource = R.drawable.medineshindi
        } else if (currentLanguage.equals(LANGUAGE_TYPE_MARATHI, ignoreCase = true)) {
            mImageResource = R.drawable.medinesmarathi
        }
        return mImageResource
    }

     /*fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        scaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    private inner class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            imageView!!.scaleX = mScaleFactor
            imageView!!.scaleY = mScaleFactor
            return true
        }
    }*/

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val view = v as ImageView
        view.scaleType = ImageView.ScaleType.MATRIX
        val scale: Float
        dumpEvent(event)
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                savedMatrix.set(matrix)
                start[event.x] = event.y
                Log.d(TAG, "mode=DRAG") // write to LogCat
                mode = DRAG
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                mode = NONE
                Log.d(TAG, "mode=NONE")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oldDist = spacing(event)
                Log.d(TAG, "oldDist=$oldDist")
                if (oldDist > 5f) {
                    savedMatrix.set(matrix)
                    midPoint(mid, event)
                    mode = ZOOM
                    Log.d(TAG, "mode=ZOOM")
                }
            }
            MotionEvent.ACTION_MOVE -> if (mode == DRAG) {
                matrix.set(savedMatrix)
                matrix.postTranslate(event.x - start.x, event.y - start.y) // create the transformation in the matrix  of points
            } else if (mode == ZOOM) {
                // pinch zooming
                val newDist = spacing(event)
                Log.d(TAG, "newDist=$newDist")
                if (newDist > 5f) {
                    matrix.set(savedMatrix)
                    scale = newDist / oldDist // setting the scaling of the
                    // matrix...if scale > 1 means
                    // zoom in...if scale < 1 means
                    // zoom out
                    matrix.postScale(scale, scale, mid.x, mid.y)
                }
            }
        }
        view.imageMatrix = matrix // display the transformation on screen
        return true // indicate event was handled
    }

    /*
     * --------------------------------------------------------------------------
     * Method: spacing Parameters: MotionEvent Returns: float Description:
     * checks the spacing between the two fingers on touch
     * ----------------------------------------------------
     */
    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt((x * x + y * y).toDouble()).toFloat()
    }

    /*
     * --------------------------------------------------------------------------
     * Method: midPoint Parameters: PointF object, MotionEvent Returns: void
     * Description: calculates the midpoint between the two fingers
     * ------------------------------------------------------------
     */
    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point[x / 2] = y / 2
    }

    /**
     * Show an event in the LogCat view, for debugging
     */
    private fun dumpEvent(event: MotionEvent) {
        val names = arrayOf("DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?")
        val sb = StringBuilder()
        val action = event.action
        val actionCode = action and MotionEvent.ACTION_MASK
        sb.append("event ACTION_").append(names[actionCode])
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(action shr MotionEvent.ACTION_POINTER_ID_SHIFT)
            sb.append(")")
        }
        sb.append("[")
        for (i in 0 until event.pointerCount) {
            sb.append("#").append(i)
            sb.append("(pid ").append(event.getPointerId(i))
            sb.append(")=").append(event.getX(i).toInt())
            sb.append(",").append(event.getY(i).toInt())
            if (i + 1 < event.pointerCount) sb.append(";")
        }
        sb.append("]")
        Log.d("Touch Events ---------", sb.toString())
    }

    companion object {
        private const val TAG = "Touch"
        private const val MIN_ZOOM = 1f
        private const val MAX_ZOOM = 1f

        // The 3 states (events) which the user is trying to perform
        const val NONE = 0
        const val DRAG = 1
        const val ZOOM = 2

    }

}