package com.example.recycler_view_practice

import android.media.MediaPlayer
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler_view_practice.databinding.PlayMusicBinding

class viewPagerAdapter(var playpage: ArrayList<playPage>)  : RecyclerView.Adapter<viewPagerAdapter.playViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): playViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.play_music,parent,false)
        return playViewHolder(PlayMusicBinding.bind(view))

    }
    override fun onBindViewHolder(holder: playViewHolder, position: Int) {
        holder.bind(playpage[position])


    }

    override fun getItemCount(): Int {
        return playpage.size
    }


    class playViewHolder(binding: PlayMusicBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val title = binding.playMusic
        val singer = binding.playSinger
        val musicimage = binding.musicImage
        var mediaPlayer : Int? = null

        fun bind (model : playPage){
            if(model.title == null)
            {
                title.text = "노래 제목"
            }
            else title.text = model.title

            if(model.singer == null)
            {
                singer.text = "노래 제목"
            }
            else singer.text = model.singer

            if(model.imageView== null)
            {
                musicimage.setImageResource(R.drawable.celebrity)
            }
            else musicimage.setImageResource(model.imageView)
            //media를 뷰홀더로 바인딩한것이다.
            mediaPlayer = model.music

            Glide
                .with(App.instance)
                .load(model.imageView)
                //.centerCrop()
                .placeholder(R.drawable.music)
                .into(musicimage);


        }


    }
}
