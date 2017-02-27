package me.relex.fadingedgeresearch.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import me.relex.fadingedgeresearch.R;

public class FragmentContainerActivity extends FragmentActivity {

    public static final String INTENT_FRAGMENT_NAME = "INTENT_FRAGMENT_NAME";

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        String fragmentName = getIntent().getStringExtra(INTENT_FRAGMENT_NAME);
        if (TextUtils.isEmpty(fragmentName)) {
            onBackPressed();
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, Fragment.instantiate(this, fragmentName))
                .commit();
    }
}
