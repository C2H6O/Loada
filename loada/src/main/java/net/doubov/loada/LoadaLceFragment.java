package net.doubov.loada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

public abstract class LoadaLceFragment<CV extends View, M> extends LoadaBaseFragment implements LoadaLceView<M> {
    protected View mLoadingView;
    protected CV mContentView;
    protected TextView mErrorView;
    protected boolean mAnimateChanges = true;

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
        onLceViewsCreated();

    }

    protected void onLceViewsCreated() {

    }

    protected abstract void onErrorViewClicked();

    protected void setAnimateChanges(boolean animateChanges) {
        mAnimateChanges = animateChanges;
    }

    @Override
    public void showLoading() {
        LoadaLceAnimator.showLoading(mLoadingView, mContentView, mErrorView);
    }
    @Override
    public void showContent() {
        LoadaLceAnimator.showContent(mAnimateChanges, mLoadingView, mContentView, mErrorView);
    }

    @Override
    public void showError(String message) {
        mErrorView.setText(message);
        LoadaLceAnimator.showErrorView(mAnimateChanges, mLoadingView, mContentView, mErrorView);
    }

}
