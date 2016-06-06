package org.indoor_gauge.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Unkon on 04/06/16.
 */
public abstract class ActivityCaller {
    public static void CallActivity(Context sender, Class receiver,
                                    Bundle bundle) {
        Intent intent = new Intent(sender, receiver);
        if (bundle != null)
            intent.putExtras(bundle);
        sender.startActivity(intent);
    }

    public static Bundle putInBundle(Bundle bundle, String name, Integer param) {
        bundle.putInt(name, param);
        return bundle;
    }

    public static Bundle putInBundle(Bundle bundle, String name, String param) {
        bundle.putString(name, param);
        return bundle;
    }
}
