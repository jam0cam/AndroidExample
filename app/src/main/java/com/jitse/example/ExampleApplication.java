package com.jitse.example;

import android.app.Application;

import com.jitse.example.retrofit.BrandService;
import com.jitse.example.retrofit.ProductService;
import com.jitse.example.retrofit.SearchService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by jitse on 10/21/14.
 */
public class ExampleApplication extends Application {

    public static String API = "https://api.zappos.com";
    public static String MAFIA_API = "https://mafia.integ.amazon.com/";
    public static String MAFIA_ADMIN_API = "https://admin-mafia.integ.amazon.com/";
    private static final String KEY = "5ca1aa6b9151f729f5b7f05b14dba5ff8aedb975";
    BrandService mBrandService;

    SearchService mSearchService;
    ProductService mProductService;

    @Override
    public void onCreate() {
        super.onCreate();
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

        RestAdapter mafiaAdminRestAdapter = new RestAdapter.Builder()
                .setEndpoint(MAFIA_ADMIN_API)
                .build();

        mSearchService = mafiaRestAdapter.create(SearchService.class);
        mProductService = mafiaAdminRestAdapter.create(ProductService.class);
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
}
