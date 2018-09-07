package com.chisw.data.db.specification;

import android.support.annotation.Nullable;

public interface SqlSpecification extends Specification {

    @Nullable
    String[] projection();

    @Nullable
    String[] selectionArgs();

    @Nullable
    String selection();

    @Nullable
    String sortOrder();

    @Nullable
    String groupBy();

    @Nullable
    String having();
}
