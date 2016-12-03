package com.bijendra.myswipedeck.myswipedeck;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeDeck;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SwipeDeck cardStack;
    private Context context = this;

    private SwipeDeckAdapter adapter;
    private ArrayList<MyCardInfo> cardInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_deck);
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        cardStack.setHardwareAccelerationEnabled(true);

        cardInfoList = new ArrayList<>();
        refreshCards();
        adapter = new SwipeDeckAdapter(cardInfoList, this);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i(TAG, "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i(TAG, "card was swiped right, position in adapter: " + position);
            }

            @Override
            public void cardsDepleted() {
                Log.i(TAG, "no more cards");
                refreshCards();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void cardActionDown() {
                Log.i(TAG, "cardActionDown");
            }

            @Override
            public void cardActionUp() {
                Log.i(TAG, "cardActionUp");
            }

        });
        cardStack.setLeftImage(R.id.left_image);
        cardStack.setRightImage(R.id.right_image);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardLeft(180);

            }
        });
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardRight(180);
            }
        });

        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add card in the list

            }
        });
    }

    private void refreshCards()
    {
        cardInfoList.add(new MyCardInfo(getString(R.string.dayalbagh_title),getString(R.string.dayalbagh_desc),R.drawable.dayalbagh));
        cardInfoList.add(new MyCardInfo(getString(R.string.tajmahal_title),getString(R.string.tajmahal_desc),R.drawable.taj));
        cardInfoList.add(new MyCardInfo(getString(R.string.agrafort_title),getString(R.string.agrafort_desc),R.drawable.redfort));
        cardInfoList.add(new MyCardInfo(getString(R.string.fatehpursikri_title),getString(R.string.fatehpursikri_desc),R.drawable.fethapur));
        cardInfoList.add(new MyCardInfo(getString(R.string.sikendra_title),getString(R.string.sikendra_desc),R.drawable.sikendra));
    }

    public class SwipeDeckAdapter extends BaseAdapter {

        private List<MyCardInfo> data;
        private Context context;

        public SwipeDeckAdapter(List<MyCardInfo> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = getLayoutInflater();

                view = inflater.inflate(R.layout.item_card, parent, false);
            }


            MyCardInfo cInfo=data.get(position);
            ImageView imageView = (ImageView) view.findViewById(R.id.offer_image);
            Picasso.with(context).load(cInfo.imageResId).fit().centerCrop().into(imageView);
            ((TextView) view.findViewById(R.id.title)).setText(cInfo.getTitle());
            ((TextView) view.findViewById(R.id.description)).setText(cInfo.getDesc());


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // do other operation
                }
            });
            return view;
        }
    }
}
