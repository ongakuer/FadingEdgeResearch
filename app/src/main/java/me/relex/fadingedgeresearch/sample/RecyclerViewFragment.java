package me.relex.fadingedgeresearch.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;
import me.relex.fadingedgeresearch.R;
import me.relex.fadingedgeresearch.base.BaseFragment;

public class RecyclerViewFragment extends BaseFragment {

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SimpleAdapter(getContext()));
    }

    public class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        private final List<String> mList;

        private SimpleAdapter(Context context) {
            String[] data = context.getResources().getStringArray(R.array.simple_data);
            mList = Arrays.asList(data);
        }

        @Override public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_text, parent, false));
        }

        @Override public void onBindViewHolder(SimpleViewHolder holder, int position) {
            ((TextView) holder.itemView).setGravity(Gravity.CENTER);
            ((TextView) holder.itemView).setText(mList.get(position));
        }

        @Override public int getItemCount() {
            return mList.size();
        }
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
