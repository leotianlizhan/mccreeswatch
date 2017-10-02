package something.mccreewatch;


import android.content.Context;
import android.widget.GridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SbAdapter extends BaseAdapter{
    private final Context ctx;
    private final SbButton[] buttons;

    public SbAdapter(Context ctx, SbButton[] buttons) {
        this.ctx = ctx;
        this.buttons = buttons;
    }

    @Override
    public int getCount() {
        return buttons.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SbButton btn = buttons[position];
        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(ctx);
            convertView = layoutInflater.inflate(R.layout.soundboard_button, null);

            final ImageView imgHero = (ImageView)convertView.findViewById(R.id.img_sound_button);

            final ViewHolder viewHolder = new ViewHolder(imgHero);
            convertView.setTag(viewHolder);

        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        // viewHolder.imageViewFavorite.setImageResource(book.getIsFavorite() ? R.drawable.star_enabled : R.drawable.star_disabled);
        Picasso.with(ctx).load(btn.getImageResource()).resize(260, 260).into(viewHolder.imgHero);
        return convertView;
    }
    private class ViewHolder {
        private final ImageView imgHero;

        public ViewHolder(ImageView img) {
            this.imgHero = img;
        }
    }
}
