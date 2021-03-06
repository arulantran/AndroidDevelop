package com.camera.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Subscriber;

/**
 * by y on 2016/5/4.
 */
public class CompetenceUtils {

    public static final String CAMERA = Manifest.permission.CAMERA;

    public static void getPermissions(final Activity context, final String permissions, final CompetenceCompat competenceCompat) {
        new RxPermissions(context)
                .request(permissions)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Utils", e.getMessage());
                        Toast.makeText(context, "请求权限出现异常:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Boolean isPermissions) {
                        Log.i("onNext", isPermissions + "");
                        if (isPermissions) {
                            competenceCompat.onPermissionsSuccess();
                        } else {
                            competenceCompat.onPermissionsError();
                        }
                    }
                });
    }

    public interface CompetenceCompat {
        void onPermissionsSuccess();

        void onPermissionsError();
    }

}
