package cwm.usingwearablesdk;

/**
 * Created by user on 2017/9/10.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import cwm.wearablesdk.TabataSettings;

public class TabataShowFragment extends Fragment {

    private View mView;
    private TextView statusView;
    private TextView itemView;
    private TextView countView;
    private TextView caloriesView;
    private TextView heartBeatView;
   // private TextView strengthView;

    String status = "";
    String item = "";
    String count = "";
    String calories = "";
    String heartBeat = "";
    //String strength = null;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.layout_tabata_show, null);
        }
        statusView = (TextView) mView.findViewById(R.id.status);
        itemView = (TextView) mView.findViewById(R.id.exercise_item);
        countView = (TextView) mView.findViewById(R.id.action_count);
        caloriesView = (TextView) mView.findViewById(R.id.action_calories);
        heartBeatView = (TextView) mView.findViewById(R.id.action_heart);

        statusView.setText("Status:  "+status);
        itemView.setText("Action Item:  "+item);
        countView.setText("Action Count:  "+count);
        caloriesView.setText("Action Calories:  "+calories);
        heartBeatView.setText("Heart Beat:  "+heartBeat);
        return mView;
    }

    public void setTabataResultValue(String status, String item, String count, String calories, String heartBeat/*, String strength*/)
    {
        this.status = status;
        this.item = item;
        this.count = count;
        this.calories = calories;
        this.heartBeat = heartBeat;
        //this.strength = strength;
    }



}