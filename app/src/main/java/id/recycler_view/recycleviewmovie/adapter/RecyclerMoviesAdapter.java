package id.recycler_view.recycleviewmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.recycler_view.recycleviewmovie.R;
import id.recycler_view.recycleviewmovie.Detail.DetailActivity;
import id.recycler_view.recycleviewmovie.model.Result;

public class RecyclerMoviesAdapter extends RecyclerView.Adapter<RecyclerMoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Result> resultList;

    public RecyclerMoviesAdapter(Context mContext, List<Result> resultList) {
        this.mContext = mContext;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public RecyclerMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_movie, parent, false);

        final RecyclerMoviesAdapter.MyViewHolder viewHolder = new RecyclerMoviesAdapter.MyViewHolder(view);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(parent.getContext(), DetailActivity.class);
                Result result = new Result();
                result.setOriginalTitle(resultList.get(viewHolder.getAdapterPosition()).getOriginalTitle());
                result.setOverview(resultList.get(viewHolder.getAdapterPosition()).getOverview());
                result.setPosterPath(resultList.get(viewHolder.getAdapterPosition()).getPosterPath());
                result.setReleaseDate(resultList.get(viewHolder.getAdapterPosition()).getReleaseDate());
                result.setVoteAverage(resultList.get(viewHolder.getAdapterPosition()).getVoteAverage());
                i.putExtra(DetailActivity.EXTRA_MOVIE, result);
                parent.getContext().startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMoviesAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(resultList.get(position).getTitle());
        holder.tvDescription.setText(resultList.get(position).getOverview());
        holder.tvTanggal.setText(resultList.get(position).getReleaseDate());
        holder.tvRating.setText(resultList.get(position).getVoteAverage().toString());
        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w185" + resultList.get(position).getPosterPath())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster,ivRating;
        TextView tvTitle,tvDescription,tvTanggal,tvRating;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.imgMovie);
            ivRating = itemView.findViewById(R.id.imgRating);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDeskripsi);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvRating = itemView.findViewById(R.id.tvRating);
            relativeLayout = itemView.findViewById(R.id.layoutMovie);
        }
    }
}
