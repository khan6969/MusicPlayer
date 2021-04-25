package SP19.BSE_O17_ASSIGN_2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.viewholder> {

    public VideoAdapter(Context context, ArrayList<Model> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    Context context;
    ArrayList<Model> videoArrayList;
    //public AdapterView.OnItemClickListener onItemClickListener;
    OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.videolist, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.title.setText(videoArrayList.get(position).getVideoTitle());
        holder.duration.setText(videoArrayList.get(position).getVideoDuration());
/*
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(position).getVideoTitle(),MediaStore.Images.Thumbnails.MINI_KIND);
        holder.imageView.setImageBitmap(bitmap);
*/
       // String path =  context.getExternalFilesDir();
      //  Bitmap = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.MINI_KIND);
//        Bitmap bp = ThumbnailUtils.createVideoThumbnail(String.valueOf(videoArrayList.get(position)),MediaStore.Images.Thumbnails.MINI_KIND);
  //      holder.imageView.setImageBitmap(bp);
      //  int id = 2;
     //   ContentResolver crThumb = context.getContentResolver();
    //    BitmapFactory.Options options=new BitmapFactory.Options();
  //      options.inSampleSize = 1;
//        Bitmap curThumb = MediaStore.Video.Thumbnails.getThumbnail(crThumb, id, MediaStore.Video.Thumbnails.MICRO_KIND, options);
        String file = "Internal shared storage\\Download";
     //   Bitmap bMap = ThumbnailUtils.createVideoThumbnail(file.getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
      //  holder.imageView.setImageBitmap(curThumb);
        Glide
                .with(context)
                .load( Uri.fromFile( new File( file+videoArrayList.get(position) ) ) )
                .into( holder.imageView );
    }

    @Override
    public int getItemCount() {
        if (videoArrayList.size()>0){
            return videoArrayList.size();
        }else
            return 1;
    }

    public class viewholder extends RecyclerView.ViewHolder {
         ImageView imageView;
        TextView title, duration;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            duration = (TextView) itemView.findViewById(R.id.duration);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Model model = videoArrayList.get(getPosition());
                     String name = model.videoTitle;
//                    onItemClickListener.onItemClick(getAdapterPosition(), v);
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    intent.putExtra("pos",getAdapterPosition());
                    intent.putExtra("title",name);
                    context.startActivity(intent);
                    Toast.makeText(context, "this is video"+getAdapterPosition()+name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(int pos, View v);
    }

}
