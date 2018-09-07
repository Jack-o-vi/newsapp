package com.chisw.data.db.datasource;

import java.util.List;

public interface DataSource {
    interface OnDataListCallback<T> {
        void onDataListLoaded(List<T> data);
        void onFailed();
    }

    interface OnDataCallback<T> {
        void onDataListLoaded(T data);
        void onFailed();
    }
}
