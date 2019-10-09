# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#---------------------------------1.实体类---------------------------------
-keep class com.ooo.mvp.model.entity.** { *; }


#---------------------------------2.第三方包-------------------------------
# ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider

# eventBus
#保护给定的可选属性
-keepattributes *Annotation*
#保护指定的类的成员的名称（如果他们不会压缩步骤中删除）
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#动态权限库
-keep class pub.devrel.** { *; }
-dontwarn pub.devrel.**

#rxjava
-dontwarn rx.**
-keep class rx.** { *; }

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-dontwarn org.robovm.**
-keep class org.robovm.** { *; }

#okhttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-keep class okhttp3.** { *;}
-keep class okio.** { *;}
-dontwarn sun.security.**
-keep class sun.security.** { *;}
-dontwarn okio.**
-dontwarn okhttp3.**


#okhttp网络访问监视日志
-keep class leavesc.hello.monitor.** { *; }
-dontwarn leavesc.hello.monitor.**

# fastJson
-keep class com.alibaba.fastjson.**{*;}
-dontwarn com.alibaba.fastjson.**
#gson包
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keep class java.lang.** { *; }
-dontwarn java.lang.**


#Glade图片加载
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.integration.okhttp3.OkHttpGlideModule

# zxing 二维码
-dontwarn com.google.zxing.**
-keep  class com.google.zxing.**{*;}

#greenDao
-keep class org.greenrobot.greendao.** { *; }
-dontwarn org.greenrobot.greendao.**
-keep class net.sqlcipher.database.SQLiteOpenHelper.** { *; }
-dontwarn net.sqlcipher.database.SQLiteOpenHelper.**


#汉字转拼音排序
-keep class demo.** { *; }
-dontwarn demo.**

#解决Toast token失效问题
-keep class me.drakeet.support.toast.** { *; }
-dontwarn me.drakeet.support.toast.**


# x5浏览器内核
-keep class com.tencent.** { *; }
-dontwarn com.tencent.**

# 骨架屏
-keep class com.ethanhua.** { *; }
-dontwarn com.ethanhua.**




#---------------------------------基本指令区----------------------------------
-optimizationpasses 5 # 代码混淆压缩比，在0~7之间，默认为5
-dontusemixedcaseclassnames #混合时不使用大小写混合，混合后的类名为小写
-dontskipnonpubliclibraryclasses #指定不去忽略非公共库的类
-verbose
-dontskipnonpubliclibraryclassmembers # 指定不去忽略非公共库的类
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/* # 混淆时所采用的算法
-keepattributes *Annotation*,InnerClasses #保留Annotation不混淆
-keepattributes Signature #避免混淆泛型
-keepattributes SourceFile,LineNumberTable #抛出异常时保留代码行号
#-ignorewarnings

-keepattributes EnclosingMethod
-dontpreverify # 不做预校验,加快混淆速度
#----------------------------------------------------------------------------
