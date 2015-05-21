package net.doubov.loada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

public abstract class LoadaLceFragment<CV extends View, M> extends LoadaBaseFragment implements LoadaLceView<M> {

    public static final String TAG = LoadaLceFragment.class.getSimpleName();

    public static final String STATE_VIEW_STATE = TAG + "STATE_VIEW_STATE";

    protected View mLoadingView;
    protected CV mContentView;
    protected TextView mErrorView;
    protected boolean mAnimateChanges = true;
    private ViewState mViewState = ViewState.LOADING;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingView = view.findViewById(R.id.loadingView);
        mContentView = (CV) view.findViewById(R.id.contentView);
        mErrorView = (TextView) view.findViewById(R.id.errorView);

        if (mLoadingView == null) {
            throw new NullPointerException(
                    "Loading view is null! Have you specified a loading view in your layout xml file?"
                            + " You have to give your loading View the id R.id.loadingView");
        }

        if (mContentView == null) {
            throw new NullPointerException(
                    "Content view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your content View the id R.id.contentView");
        }

        if (mErrorView == null) {
            throw new NullPointerException(
                    "Error view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your error View the id R.id.errorView");
        }

        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onErrorViewClicked();
            }
        });

        if (savedInstanceState != null) {
            mViewState = (ViewState) savedInstanceState.getSerializable(STATE_VIEW_STATE);
        }
        restoreViewState(mViewState);
        onLceViewsCreated();
    }

    private void restoreViewState(ViewState state) {
        switch (state) {
            case CONTENT:
                showContent();
                break;
            case LOADING:
                showLoading();
                break;
            case ERROR:
                showError();
                break;
        }
    }

    protected ViewState getViewState() {
        return mViewState;
    }

    private enum ViewState {
        LOADING,
        CONTENT,
        ERROR
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(STATE_VIEW_STATE, mViewState);
    }

    protected void onLceViewsCreated() {

    }

    protected abstract void onErrorViewClicked();

    protected void setAnimateChanges(boolean animateChanges) {
        mAnimateChanges = animateChanges;
    }

    @Override
    public void showLoading() {
        mViewState = ViewState.LOADING;
        LoadaLceAnimator.showLoading(mLoadingView, mContentView, mErrorView);
    }
    @Override
    public void showContent() {
        mViewState = ViewState.CONTENT;
        LoadaLceAnimator.showContent(mAnimateChanges, mLoadingView, mContentView, mErrorView);
    }

    @Override
    public void showError() {
        mViewState = ViewState.ERROR;
        LoadaLceAnimator.showErrorView(mAnimateChanges, mLoadingView, mContentView, mErrorView);
    }

    @Override
    public void showError(String message) {
        mErrorView.setText(message);
        showError();
    }

}
