package something.mccreewatch;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GridViewAdapter extends BaseAdapter{
    private final Context ctx;
    private final SbButton[] buttons;

    public GridViewAdapter(Context ctx, SbButton[] buttons) {
        this.ctx = ctx;
        this.buttons = buttons;
    }

    @Override
    public int getCount() {
        return 0;
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
        return null;
    }
}
