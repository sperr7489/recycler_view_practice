package com.example.recycler_view_practice

import com.example.recycler_view_practice.databinding.MusicRankingBinding

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.nfc.Tag
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.insertImage
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler_view_practice.databinding.MusicRankingBinding.*
import java.io.ByteArrayOutputStream

class RankRecyclerAdapter(var rankModel: ArrayList<RankModel>) : RecyclerView.Adapter<RankRecyclerAdapter.RankViewHolder>() {
//todo 매개변수로 받은 부분을 지금 어댑터에서 사용하고 있는지를 생각해보자.

    //뷰홀더가 생성 되었을 rankviewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.music_ranking, parent, false)

        return RankViewHolder(bind(view))


    }

    //뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        //super.onBindViewHolder(holder, position)

        holder.bind(rankModel[position])
        holder.itemView.setOnClickListener { view->
                Log.d("로그", "몇 ${position}번째 아이템이 추가되었습니다. ")
                holder.add_button.setColorFilter(Color.RED)

            }
        }

    override fun getItemCount(): Int {

        return rankModel.size
    }

    class RankViewHolder(binding: MusicRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        val musicTitle = itemView.findViewById<TextView>(R.id.recommend_music_title)
//        val singer = itemView.findViewById<TextView>(R.id.recommend_music_singer)
//        val music = itemView.findViewById<ImageView>(R.id.recommend_music)

        val musicTitle = binding.rankingTitle
        val singer = binding.rankingSinger
        val music = binding.rankingMusic
        val rank = binding.musicRank
        val add_button = binding.playlistAddButton
        fun bind(model: RankModel) {

            if (model.music == null) {
                musicTitle.text = "노래"
            } else musicTitle.text = model.music

            if (model.singer == null) {
                singer.text = "가수"
            } else singer.text = model.singer

            if (model.imageView == null) {
                music.setImageResource(R.drawable.music)
            } else music.setImageResource(model.imageView)
            if(model.rank== 0)
            rank.text = adapterPosition.toString()


            Glide
                .with(App.instance)
                .load(model.imageView)
                //.centerCrop()
                .placeholder(R.drawable.music)
                .into(music);


        }


    }

    fun removeTask(position: Int)
    {
        rankModel.removeAt(position)
        notifyDataSetChanged()
    }

}

