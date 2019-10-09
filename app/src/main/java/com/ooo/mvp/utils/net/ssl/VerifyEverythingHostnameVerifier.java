package com.ooo.mvp.utils.net.ssl;

import com.ooo.mvp.config.BaseConfig;
import com.socks.library.KLog;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 实现HostnameVerifier接口，不进行url和服务器主机名的验证。
 * Created by lhfBoy 2016/11/8 10:52
 */
public class VerifyEverythingHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        KLog.d(BaseConfig.LOG, "url和服务器主机名验证s:" + s);
        KLog.d(BaseConfig.LOG, "url和服务器主机名验证sslSession_PeerHost:" + sslSession.getPeerHost() + ",Protocol:" + sslSession.getProtocol() + ",CipherSuite:" + sslSession.getCipherSuite());
        return true;
    }
}
