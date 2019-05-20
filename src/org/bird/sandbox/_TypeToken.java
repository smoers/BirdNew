package org.bird.sandbox;

import com.google.common.reflect.TypeToken;
import org.bird.gui.controllers.display.DisplayDashboardItemAuthor;

public class _TypeToken {

    public static void main(String[] args) {

        TypeToken<String> type = new TypeToken<String>(String.class){};
        System.out.println(type.getType().getTypeName());

        System.out.println(new _AbstractToken<DisplayDashboardItemAuthor>(){}.type);

    }

}
