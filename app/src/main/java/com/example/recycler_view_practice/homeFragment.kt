package com.example.recycler_view_practice

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_practice.databinding.FragmentHomeBinding
import com.example.recycler_view_practice.databinding.RecommendRecyclerviewItemBinding
import kotlinx.coroutines.NonCancellable.cancel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeFragment : Fragment() {


    //추천 음악
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var modelList = ArrayList<RecommendModel>()

    //랭킹 음악
    private lateinit var rank_recyclerView : RecyclerView
    private lateinit var rankRecyclerAdapter: RankRecyclerAdapter
    private lateinit var rlayoutManager: RecyclerView.LayoutManager
    var rankList = ArrayList<RankModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val add_button = view.findViewById(R.id.add_music) as ImageView

        recyclerView = view.findViewById(R.id.recommend_recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager
        recyclerView.scrollToPosition(0)
     //   (layoutManager as LinearLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL

        for (i in 1..20) {
            modelList.add(RecommendModel(R.drawable.goldfish, "금붕어의 꿈", "오담률"))
            modelList.add(RecommendModel(R.drawable.loveletter, "love Letter", "볼빨간 사춘기"))
            modelList.add(RecommendModel(R.drawable.celebrity, "celebrity", "아이유"))
            modelList.add(RecommendModel(R.drawable.dont_lov, "우리 사랑하지는 말자", "기리보이"))
        }
        myRecyclerAdapter = MyRecyclerAdapter(modelList)
        recyclerView.adapter = myRecyclerAdapter

        //todo 음악 랭킹을 보여주는 것.
        rlayoutManager =LinearLayoutManager(context)
        rank_recyclerView = view.findViewById(R.id.ranking_music_recyclerview) as RecyclerView
        rank_recyclerView.setHasFixedSize(true)
        rank_recyclerView.layoutManager = rlayoutManager
        rank_recyclerView.scrollToPosition(0)
        (rlayoutManager as LinearLayoutManager).orientation = LinearLayoutManager.VERTICAL

        for (i in 1..5) {
            rankList.add(RankModel(0,R.drawable.goldfish, "금붕어의 꿈", "오담률"))
            rankList.add(RankModel(0,R.drawable.loveletter, "love Letter", "볼빨간 사춘기"))
            rankList.add(RankModel(0,R.drawable.celebrity, "celebrity", "아이유"))
            rankList.add(RankModel(0,R.drawable.dont_lov, "우리 사랑하지는 말자", "기리보이"))
        }

      add_button.setOnClickListener{
            view ->
            val editDialogFragment = EditDialogFragment(modelList)
            editDialogFragment.show(requireActivity().supportFragmentManager,"sample")
            Toast.makeText(view.context, "정보 수정 완료", Toast.LENGTH_SHORT).show()


        }

       rankRecyclerAdapter = RankRecyclerAdapter(rankList)

        rank_recyclerView.adapter = rankRecyclerAdapter



        //아이템을 밀어서 삭제하는 터치 헬퍼

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP or ItemTouchHelper.DOWN)
        {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myRecyclerAdapter.removeTask(viewHolder.adapterPosition)

            }

        }).apply{
            attachToRecyclerView(recyclerView)
            Log.d("로그","아이템 삭제가 이루어졌다")


        }
//        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END)
//        {
//            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//               // myRecyclerAdapter.removeTask(viewHolder.adapterPosition)
//                val editDialogFragment =EditDialogFragment(modelList.get(viewHolder.adapterPosition))
//                    editDialogFragment.show(activity!!.supportFragmentManager, "SampleDialog")
//                 //  alertDialog.show()
//
//            }
//
//        }).apply{
//            attachToRecyclerView(recyclerView)
//            Log.d("로그","아이템 추가가 이루어졌다")
//
//
//        }
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}
//class FireMissilesDialogFragment : DialogFragment() {
//
//    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
//        return activity?.let {
//            // Use the Builder class for convenient dialog construction
//            val builder = AlertDialog.Builder(it)
//            builder.setMessage(R.string.dialog_fire_missiles)
//                .setPositiveButton(R.string.save,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // FIRE ZE MISSILES!
//                    })
//                .setNegativeButton(R.string.cancel,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // User cancelled the dialog
//                    })
//            // Create the AlertDialog object and return it
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//}