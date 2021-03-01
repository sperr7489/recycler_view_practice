package com.example.recycler_view_practice

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.insertImage
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler_view_practice.databinding.RecommendRecyclerviewItemBinding
import java.io.ByteArrayOutputStream

class MyRecyclerAdapter(var recommendModel: ArrayList<RecommendModel>) : RecyclerView.Adapter<MyRecyclerAdapter.RecommendViewHolder>() {
//todo 매개변수로 받은 부분을 지금 어댑터에서 사용하고 있는지를 생각해보자.
    // private var modelList = ArrayList<RecommendModel>()

    //뷰홀더가 생성 되었을 RecommendViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recommend_recyclerview_item, parent, false)

        return RecommendViewHolder(RecommendRecyclerviewItemBinding.bind(view))


    }

    //뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        //super.onBindViewHolder(holder, position)

        holder.bind(recommendModel[position])
        holder.itemView.setOnClickListener { view ->

            val context = view.context
            val intent = Intent(context, MusicCard::class.java)
            val bitmap = holder.music.drawable.toBitmap()
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            //todo imageview를 클릭했을 때 이제 다른 화면에 그 화면이 나와게끔한다.
//            val context = view.context
//            val intent = Intent(context, MusicCard::class.java)
//         intent.putExtra("제목",holder.musicTitle.toString())
//         intent.putExtra("가수",holder.singer.toString())
//         intent.putExtra("커버",holder.music)
//
//            context.startActivity(intent)

        }
    }

    //목록의 아이템 갯수.
    override fun getItemCount(): Int {

        return recommendModel.size

    }

    class RecommendViewHolder(binding: RecommendRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        val musicTitle = itemView.findViewById<TextView>(R.id.recommend_music_title)
//        val singer = itemView.findViewById<TextView>(R.id.recommend_music_singer)
//        val music = itemView.findViewById<ImageView>(R.id.recommend_music)

        val musicTitle = binding.recommendMusicTitle
        val singer = binding.recommendMusicSinger
        val music = binding.recommendMusic


        fun bind(model: RecommendModel) {

            if (model.music == null) {
                musicTitle.text = "노래"
            } else musicTitle.text = model.music

            if (model.singer == null) {
                singer.text = "가수"
            } else singer.text = model.singer

            if (model.imageView == null) {
                music.setImageResource(R.drawable.music)
            } else music.setImageResource(model.imageView)


            Glide
                .with(App.instance)
                .load(model.imageView)
                //.centerCrop()
                .placeholder(R.drawable.music)
                .into(music);


        }


    }

//    //외부에서 데이터 넘기기
//    fun submitList(modelList: ArrayList<RecommendModel>)
//    {
//        recommendModel = modelList
//    }

    fun removeTask(position: Int)
    {
        recommendModel.removeAt(position)
        notifyDataSetChanged()

    }

}