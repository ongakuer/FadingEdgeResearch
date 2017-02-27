package me.relex.fadingedgeresearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import me.relex.fadingedgeresearch.base.FragmentContainerActivity;
import me.relex.fadingedgeresearch.sample.CustomViewFragment;
import me.relex.fadingedgeresearch.sample.DrawFadingEdgeFragment;
import me.relex.fadingedgeresearch.sample.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SampleAdapter adapter = new SampleAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.updateData(createSampleList());
    }

    private List<SampleInfo> createSampleList() {
        List<SampleInfo> list = new ArrayList<>();

        list.add(new SampleInfo(getString(R.string.sample_recycler_view),
                RecyclerViewFragment.class));
        list.add(new SampleInfo(getString(R.string.sample_custom_view), CustomViewFragment.class));
        list.add(new SampleInfo(getString(R.string.sample_draw_fading_edge),
                DrawFadingEdgeFragment.class));

        return list;
    }

    public void navigationTo(SampleInfo info) {
        Intent intent = new Intent(this, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.INTENT_FRAGMENT_NAME, info.fragment.getName());
        startActivity(intent);
    }

    private class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> {

        private final List<SampleInfo> list = new ArrayList<>();

        @Override public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SampleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_text, parent, false));
        }

        @Override public void onBindViewHolder(SampleViewHolder holder, int position) {
            holder.bindViewHolder(list.get(position));
        }

        @Override public int getItemCount() {
            return list.size();
        }

        public void updateData(List<SampleInfo> list) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    private class SampleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public SampleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public void bindViewHolder(final SampleInfo info) {
            textView.setText(info.name);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    navigationTo(info);
                }
            });
        }
    }

    private class SampleInfo {
        public String name;
        public Class<? extends Fragment> fragment;

        public SampleInfo(String name, Class<? extends Fragment> fragment) {
            this.name = name;
            this.fragment = fragment;
        }
    }
}
