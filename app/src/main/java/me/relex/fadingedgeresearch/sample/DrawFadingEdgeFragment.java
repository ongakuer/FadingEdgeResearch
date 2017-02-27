package me.relex.fadingedgeresearch.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.relex.fadingedgeresearch.R;
import me.relex.fadingedgeresearch.base.BaseFragment;

public class DrawFadingEdgeFragment extends BaseFragment {

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_draw_fading_edge_view, container, false);
    }
}
