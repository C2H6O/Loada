package net.doubov.loada;

public interface LceView<M> {

    void showLoading();

    void showContent();

    void showError(String message);

    void setData(M data);

    boolean hasData();

    void clearData();

}
