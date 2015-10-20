package tn.orange.secoure;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";

    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();
        fragment.mContent = content;

        return fragment;
    }

    private String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    	View v = inflater.inflate(R.layout.first_step, container, false);
    	if(mContent.equals("Žtape 1"))
    	{
        v = inflater.inflate(R.layout.first_step, container, false);
    	}
    	else if(mContent.equals("Žtape 2"))
    	{
            v = inflater.inflate(R.layout.second_step, container, false);
        }
    	else if(mContent.equals("Žtape 3"))
    	{
            v = inflater.inflate(R.layout.third_step, container, false);
        }
    	else if(mContent.equals("Žtape 4"))
    	{
            v = inflater.inflate(R.layout.fourth_step, container, false);
            Button btn1 = (Button) v.findViewById(R.id.button1);
            btn1.setBackgroundColor(Color.parseColor("#990000"));
            btn1.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				Intent i = new Intent(v.getContext(),com.example.boutonand.Etouffement.class);
    				v.getContext().startActivity(i);
    			}
    		});
            
            Button btn2 = (Button) v.findViewById(R.id.button2);
            btn2.setBackgroundColor(Color.parseColor("#990000"));
            btn2.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				Intent i = new Intent(v.getContext(),com.example.boutonand.Hemoragie.class);
    				v.getContext().startActivity(i);
    			}
    		});
            
            Button btn3 = (Button) v.findViewById(R.id.button3);
            btn3.setBackgroundColor(Color.parseColor("#990000"));
           btn3.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				Intent i = new Intent(v.getContext(),com.example.boutonand.Inconscience.class);
    				v.getContext().startActivity(i);
    			}
    		});
            
            Button btn4 = (Button) v.findViewById(R.id.button4);
            btn4.setBackgroundColor(Color.parseColor("#990000"));
            
            btn4.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				Intent i = new Intent(v.getContext(),com.example.boutonand.Malaise.class);
    				v.getContext().startActivity(i);
    			}
    		});
            Button btn5 = (Button) v.findViewById(R.id.button5);
            btn5.setBackgroundColor(Color.parseColor("#990000"));
            btn5.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				Intent i = new Intent(v.getContext(),com.example.boutonand.Brulure.class);
    				v.getContext().startActivity(i);
    			}
    		});
            
            Button btn6 = (Button) v.findViewById(R.id.button6);
            btn6.setBackgroundColor(Color.parseColor("#990000"));
            btn6.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				Intent i = new Intent(v.getContext(),com.example.boutonand.Traumatisme.class);
    				v.getContext().startActivity(i);
    			}
    		});
            
            Button btn7 = (Button) v.findViewById(R.id.button7);
            btn7.setBackgroundColor(Color.parseColor("#990000"));
            btn7.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				Intent i = new Intent(v.getContext(),com.example.boutonand.Fracture.class);
    				v.getContext().startActivity(i);
    			}
    		});
        }
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
}
