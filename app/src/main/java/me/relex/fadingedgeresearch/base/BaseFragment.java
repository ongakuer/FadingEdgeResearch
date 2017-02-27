package me.relex.fadingedgeresearch.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import me.relex.fadingedgeresearch.R;

public class BaseFragment extends Fragment {

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        View toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((Toolbar) toolbar).setNavigationOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }
    }
}
