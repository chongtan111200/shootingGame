package com.example.chongtian.catching_game;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Control buttons for the superman, displayed after started the game
 * Created by chong tian on 3/26/2017.
 */

public class ControlButtonFragment extends Fragment {
    //control buttons
    private Button upButton;
    private Button leftButton;
    private Button downButton;
    private Button rightButton;
    private OnControlChanged occ;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_control_buttons,
                container, false);

        //control button initialization
        upButton=(Button)v.findViewById(R.id.up_button);
        leftButton=(Button) v.findViewById(R.id.left_button);
        rightButton=(Button) v.findViewById(R.id.right_button);
        downButton=(Button) v.findViewById(R.id.down_button);

        //changed the direction by passing the direction integer, it represents the direction
        leftButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        occ.onControlChanged(1);
                    }
                }
        );

        downButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        occ.onControlChanged(2);
                    }
                }
        );

        rightButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        occ.onControlChanged(3);
                    }
                }
        );

        upButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        occ.onControlChanged(4);
                    }
                }
        );

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            occ=(OnControlChanged)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Main activity must implement OnControlChanged");
        }
    }

    // interface to communicate with the main activity
    public interface OnControlChanged {
        public void onControlChanged(int direction);
    }

}