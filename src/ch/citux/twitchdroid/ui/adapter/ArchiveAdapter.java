package ch.citux.twitchdroid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ch.citux.twitchdroid.R;
import ch.citux.twitchdroid.data.model.Archive;
import com.yixia.zi.utils.ImageFetcher;

import java.util.ArrayList;

public class ArchiveAdapter extends BaseAdapter {

    private ArrayList<Archive> data;
    private LayoutInflater inflater;
    private ImageFetcher imageFetcher;

    public ArchiveAdapter(Context context) {
        init(context, new ArrayList<Archive>());
    }

    public ArchiveAdapter(Context context, ArrayList<Archive> data) {
        init(context, data);
    }

    private void init(Context context, ArrayList<Archive> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.imageFetcher = new ImageFetcher(context);
    }

    public void setData(ArrayList<Archive> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void clear() {
        data.clear();
        notifyDataSetInvalidated();
    }

    private String formatTime(long duration) {
        long minutes = duration / 60;
        long seconds = duration - (minutes * 60);
        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Archive getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        Archive item = getItem(position);
        if (item != null) {
            return item.getId();
        }
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Archive archive = data.get(position);
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_archive, null);
            holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.imgThumbnail);
            holder.lblDuration = (TextView) convertView.findViewById(R.id.lblDuration);
            holder.lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        imageFetcher.loadImage(archive.getThumbnail(), holder.imgThumbnail, R.drawable.default_archive_thumbnail);
        holder.lblDuration.setText(formatTime(archive.getDuration()));
        holder.lblTitle.setText(archive.getTitle());
        return convertView;
    }

    private class ViewHolder {
        ImageView imgThumbnail;
        TextView lblDuration;
        TextView lblTitle;
    }

}