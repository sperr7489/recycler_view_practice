package com.example.recycler_view_practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_practice.databinding.FragmentListfragmentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [listfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class listFragment : Fragment() {

        private lateinit var binding : FragmentListfragmentBinding
        private lateinit var listAdapter: RankRecyclerAdapter
        private lateinit var recyclerView: RecyclerView
        private lateinit var layoutManager : RecyclerView.LayoutManager
        var playlist = ArrayList<RankModel>()

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_listfragment, container, false)

            recyclerView =view.findViewById(R.id.playlist)as RecyclerView
            recyclerView.setHasFixedSize(true)
            layoutManager =LinearLayoutManager(context)
            recyclerView.layoutManager =layoutManager
            recyclerView.scrollToPosition(0)
            (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.VERTICAL

            for (i in 1..20) {
                playlist.add(RankModel(0,R.drawable.goldfish, "금붕어의 꿈", "오담률"))
                playlist.add(RankModel(0,R.drawable.loveletter, "love Letter", "볼빨간 사춘기"))
                playlist.add(RankModel(0,R.drawable.celebrity, "celebrity", "아이유"))
                playlist.add(RankModel(0,R.drawable.dont_lov, "우리 사랑하지는 말자", "기리보이"))
            }
            listAdapter = RankRecyclerAdapter(playlist)
            recyclerView.adapter = listAdapter
            return view

        }
}
//
//class homeFragment : Fragment() {

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        binding = FragmentHomeBinding.inflate(inflater)
//
//
//        recyclerView = view.findViewById(R.id.recommend_recyclerView) as RecyclerView
//        recyclerView.setHasFixedSize(true)
//
//        layoutManager = LinearLayoutManager(context)
//
//        recyclerView.layoutManager = layoutManager
//        recyclerView.scrollToPosition(0)
//        //   (layoutManager as LinearLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
//        (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
//
//        for (i in 1..20) {
//            modelList.add(RecommendModel(R.drawable.goldfish, "금붕어의 꿈", "오담률"))
//            modelList.add(RecommendModel(R.drawable.loveletter, "love Letter", "볼빨간 사춘기"))
//            modelList.add(RecommendModel(R.drawable.celebrity, "celebrity", "아이유"))
//            modelList.add(RecommendModel(R.drawable.dont_lov, "우리 사랑하지는 말자", "기리보이"))
//        }
//        myRecyclerAdapter = MyRecyclerAdapter(modelList)
//        recyclerView.adapter = myRecyclerAdapter
//
//        //todo 음악 랭킹을 보여주는 것.
//        rlayoutManager =LinearLayoutManager(context)
//        rank_recyclerView = view.findViewById(R.id.ranking_music_recyclerview) as RecyclerView
//        rank_recyclerView.setHasFixedSize(true)
//        rank_recyclerView.layoutManager = rlayoutManager
//        rank_recyclerView.scrollToPosition(0)
//        (rlayoutManager as LinearLayoutManager).orientation = LinearLayoutManager.VERTICAL
//
//        for (i in 1..20) {
//            rankList.add(RankModel(0,R.drawable.goldfish, "금붕어의 꿈", "오담률"))
//            rankList.add(RankModel(0,R.drawable.loveletter, "love Letter", "볼빨간 사춘기"))
//            rankList.add(RankModel(0,R.drawable.celebrity, "celebrity", "아이유"))
//            rankList.add(RankModel(0,R.drawable.dont_lov, "우리 사랑하지는 말자", "기리보이"))
//        }
//
//        binding.addMusic.setOnClickListener{
//                view ->
////            val editDialogFragment = EditDialogFragment(modelList)
////            editDialogFragment.show(requireActivity().supportFragmentManager,"sample")
//            Toast.makeText(view.context, "정보 수정 완료", Toast.LENGTH_SHORT).show()
//
//
//        }
//
//        rankRecyclerAdapter = RankRecyclerAdapter(rankList)
//
//        rank_recyclerView.adapter = rankRecyclerAdapter
//
//
//
//        //아이템을 밀어서 삭제하는 터치 헬퍼
//
//        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP or ItemTouchHelper.DOWN)
//        {
//            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                myRecyclerAdapter.removeTask(viewHolder.adapterPosition)
//
//            }
//
//        }).apply{
//            attachToRecyclerView(recyclerView)
//            Log.d("로그","아이템 삭제가 이루어졌다")
//
//
//        }
//
//        return view
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//
//    }
//
//}
