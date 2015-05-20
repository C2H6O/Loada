package net.doubov.loada;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

public class LoadaBaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(getActivity());
        onDependenciesInjected();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.inject(this, view);
        onButterKnifeInjected();

    }

    protected void injectDependencies(Context context) {

    }

    protected void onDependenciesInjected() {

    }

    protected void onButterKnifeInjected() {

    }


}
