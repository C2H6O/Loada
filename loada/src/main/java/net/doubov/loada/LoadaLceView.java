package net.doubov.loada;

public interface LoadaLceView<M> {

    void showLoading();

    void showContent();

    void showError();

    void showError(String message);

    void setData(M data);

    boolean hasData();

    void clearData();

}
