package org.bird.sandbox;

import com.google.common.reflect.TypeToken;

public abstract class _AbstractToken<T> {
    TypeToken<T> type = new TypeToken<T>(getClass()) {};
}
