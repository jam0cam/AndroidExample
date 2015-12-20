package com.jitse.example;

import android.app.Application;
import android.graphics.Bitmap;

import com.jitse.example.retrofit.BrandService;
import com.jitse.example.retrofit.PhetchService;
import com.jitse.example.retrofit.ProductService;
import com.jitse.example.retrofit.SearchService;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.branch.referral.Branch;
import io.fabric.sdk.android.Fabric;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by jitse on 10/21/14.
 */
public class ExampleApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "WEyOBPFdqY2IhYUBmGNE8IXKf";
    private static final String TWITTER_SECRET = "35oIo77kbnHxbpPBfws9PUq52v60dXACJGzOOyFSvaAnGncwo5";

    public static String API = "https://api.zappos.com";
    public static String MAFIA_API = "https://mafia.integ.amazon.com/";
    public static String PHETCH_API = "http://phetch.elasticbeanstalk.com/";
    private static final String KEY = "5ca1aa6b9151f729f5b7f05b14dba5ff8aedb975";
    BrandService mBrandService;

    SearchService mSearchService;
    ProductService mProductService;
    PhetchService mPhetchService;

    public Bitmap mBitmap;

    @Override
    public void onCreate() {
        super.onCreate();
        Branch.getAutoInstance(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    // Sure, https://www.zappos.com.russia.com is valid!
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Can't set up the trust all ssl store");
        } catch (KeyManagementException e) {
            System.out.println("Can't set up the trust all ssl store");
        }


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("User-Agent", "Android-App v" + BuildConfig.VERSION_CODE);
                request.addQueryParam("key", KEY);
            }
        };

        RestAdapter zapposRestAdapter = new RestAdapter.Builder()
                .setEndpoint(API)
                .setRequestInterceptor(requestInterceptor)
                .build();

        mBrandService = zapposRestAdapter.create(BrandService.class);

        RestAdapter mafiaRestAdapter = new RestAdapter.Builder()
                .setEndpoint(MAFIA_API)
                .build();

        RestAdapter phetchRestAdapter = new RestAdapter.Builder()
                .setEndpoint(PHETCH_API)
                .build();

        mSearchService = mafiaRestAdapter.create(SearchService.class);
        mProductService = zapposRestAdapter.create(ProductService.class);
        mPhetchService = phetchRestAdapter.create(PhetchService.class);
    }

    public BrandService getBrandService() {
        return mBrandService;
    }

    public SearchService getSearchService() {
        return mSearchService;
    }

    public ProductService getProductService() {
        return mProductService;
    }

    public PhetchService getPhetchService() {
        return mPhetchService;
    }
}
