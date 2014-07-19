package com.jacmobile.technews.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.jacmobile.technews.R;

import com.jacmobile.technews.util.ProgressGenerator;

public class Login extends Fragment implements ProgressGenerator.OnCompleteListener
{

	ImageView ivIcon;
	TextView tvItemName;

    ActionProcessButton btnSignIn;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";

	public Login() {  }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		View view = inflater.inflate(R.layout.fragment_layout_one, container, false);

//		ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
//		tvItemName = (TextView) view.findViewById(R.id.frag1_text);
//
//		tvItemName.setText(getArguments().getString(ITEM_NAME));
//		ivIcon.setImageDrawable(view.getResources().getDrawable(
//				getArguments().getInt(IMAGE_RESOURCE_ID)));

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        btnSignIn = (ActionProcessButton) view.findViewById(R.id.btnSignIn);
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnSignIn);
                btnSignIn.setEnabled(false);
            }
        });

		return view;
	}

    @Override
    public void onComplete() {
        Toast.makeText(getActivity(), "Signed in!", Toast.LENGTH_LONG).show();
    }
}
