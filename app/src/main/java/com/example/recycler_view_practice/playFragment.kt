package com.example.recycler_view_practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.recycler_view_practice.databinding.PlayMusicBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [playFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class playFragment : Fragment() {

//    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var layoutManager: RecyclerView.LayoutManager
//    var modelList = ArrayList<RecommendModel>()
    private  lateinit var viewPager : ViewPager2

    var musicPlay = ArrayList<playPage>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_playfragment, container, false)
        viewPager =view.findViewById(R.id.viewPager)


        for (i in 1..20) {
            musicPlay.add(playPage(R.drawable.goldfish, "금붕어의 꿈", "오담률",R.raw.bewhy))
            musicPlay.add(playPage(R.drawable.loveletter, "love Letter", "볼빨간 사춘기",R.raw.bewhy))
            musicPlay.add(playPage(R.drawable.celebrity, "celebrity", "아이유",R.raw.bewhy))
            musicPlay.add(playPage(R.drawable.dont_lov, "우리 사랑하지는 말자", "기리보이",R.raw.bewhy))
        }


        val pagerAdapter = viewPagerAdapter(musicPlay)
        viewPager.adapter = pagerAdapter

        val ZoomOutPageTransformer = ZoomOutPageTransformer()
        viewPager.setPageTransformer(ZoomOutPageTransformer)



        return view
    }
}
private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f


class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}
