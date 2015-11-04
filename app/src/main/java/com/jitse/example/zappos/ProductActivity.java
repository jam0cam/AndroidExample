package com.jitse.example.zappos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jitse.example.R;
import com.jitse.example.activities.ProductRecyclerAdapter;
import com.jitse.example.views.MyScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProductActivity extends Activity {

    private static final String TAG = ProductActivity.class.getName();

    @InjectView(R.id.sv)
    MyScrollView mSv;

    @InjectView(R.id.rl_main)
    RelativeLayout mRlMain;

    @InjectView(R.id.rv_styles)
    RecyclerView mRvStyles;

    @InjectView(R.id.rv_similar_items)
    RecyclerView mRvSimilarItems;

    @InjectView(R.id.product_info_container)
    RelativeLayout mStickyHeader;

    @InjectView(R.id.img_product)
    ImageView mImgProduct;

    @InjectView(R.id.tv_product_brand)
    TextView mTvBrand;

    @InjectView(R.id.tv_2)
    TextView mTvProduct;

    @InjectView(R.id.tv_price)
    TextView mTvPrice;

    @InjectView(R.id.tv_reviews)
    TextView mTvReviews;

    @InjectView(R.id.tv_details)
    TextView mTvDetails;

    @InjectView(R.id.btn_add_to_cart)
    ImageButton mAddToCart;

    @InjectView(R.id.rb_ratings)
    RatingBar mRbRatings;

    Product mProduct;
    Style mCurrentStyle;

    ProductRecyclerAdapter mAdapterStyles;
    ProductRecyclerAdapter mAdapterSimilarItems;

    ImageLoader mImageLoader;
    private boolean mIsAnimating = false;
    private boolean mIsRequestUp = false;
    private boolean mIsRequestDown = false;

    private String[] colors = {"Black Patent",
            "Black Suede",
            "Cognac Suede",
            "Deep Gray",
            "Navy Suede",
            "Pink Suede",
            "Sandy Luster Patent"
    };

    private String[] productNames = {"Pinkish",
            "Cleo",
            "Janna",
            "Natalie",
            "Carra",
            "Tirra"
    };

    private String[] brandNames = {"Ivanka Trump",
            "Ivanka Trump",
            "Ivanka Trump",
            "Ivanka Trump",
            "Ivanka Trump",
            "Ivanka Trump"
    };

    private String productDetails = "<html>\n" +
            "<ul class=\"product-description\">\n" +
            "<li>Make a posh statement with these sleek pumps!</li>\n" +
            "<li>Easy slip-on wear.</li>\n" +
            "<li>Polished patent leather upper.</li>\n" +
            "<li>Leather lining.</li>\n" +
            "<li>Lightly cushioned leather footbed.</li>\n" +
            "<li>Wrapped heel.</li>\n" +
            "<li>Leather sole.</li>\n" +
            "<li>Imported.</li>\n" +
            "<li class=\"measurements\">Measurements:\n" +
            "<ul>\n" +
            "<li> Heel Height: 3 <sup>1</sup>&frasl;<sub>2</sub> in</li>\n" +
            "<li> Weight: 7 oz</li>\n" +
            "</ul>\n" +
            "</li>\n" +
            "<li>Product measurements were taken using size 7.5, width M. Please note that measurements may vary by size.</li>\n" +
            "</ul>\n" +
            "</html>";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.inject(this);

        mStickyHeader.bringToFront();
        mRlMain.invalidate();

        setupFab();
        setupScrolling();
        setupStyles();
        setupDetails();
        setupSimilarItems();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupFab() {
        // Adjust and setup the FAB for the cart.
        mAddToCart.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int diameter = getResources().getDimensionPixelSize(R.dimen.floating_action_button_size);

                outline.setOval(0, 0, diameter, diameter);
            }
        });

        mAddToCart.setClipToOutline(true);

    }

    private void setupDetails() {
        mTvDetails.setText(Html.fromHtml(productDetails));
    }

    private void setupSimilarItems() {
        mRvSimilarItems.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvSimilarItems.setLayoutManager(mLayoutManager);


        Drawable[] similarItems = {
                getResources().getDrawable(R.drawable.pinkish),
                getResources().getDrawable(R.drawable.cleo),
                getResources().getDrawable(R.drawable.janna),
                getResources().getDrawable(R.drawable.natalie),
                getResources().getDrawable(R.drawable.carra),
                getResources().getDrawable(R.drawable.tirra)
        };

        //
        mAdapterSimilarItems = new ProductRecyclerAdapter(null, productNames, brandNames, similarItems);
        mRvSimilarItems.setAdapter(mAdapterSimilarItems);
    }

    private void setupStyles() {
        mRvStyles.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvStyles.setLayoutManager(mLayoutManager);

        Drawable[] styles = {
                getResources().getDrawable(R.drawable.black_patent),
                getResources().getDrawable(R.drawable.black_suede),
                getResources().getDrawable(R.drawable.cognac_suede),
                getResources().getDrawable(R.drawable.grey_suede),
                getResources().getDrawable(R.drawable.navy),
                getResources().getDrawable(R.drawable.pink),
                getResources().getDrawable(R.drawable.sandy)
        };



        mAdapterStyles = new ProductRecyclerAdapter(colors, null, null, styles);
        mRvStyles.setAdapter(mAdapterStyles);

    }

    private void animateCartDown() {
        mAddToCart.animate().translationY(mStickyHeader.getMeasuredHeight()).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mIsRequestUp) {
                    animateCartUp();
                }
                mIsRequestDown = false;
                mIsAnimating = false;
            }
        }).start();
    }

    private void animateCartUp() {
        mAddToCart.animate().translationY(0).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mIsRequestDown) {
                    animateCartDown();
                }
                mIsRequestUp = false;
                mIsAnimating = false;
            }
        }).start();
    }

    private void setupScrolling() {

        mSv.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollChanged() {
                //parallax effect
                mImgProduct.setTranslationY(mSv.getScrollY() / 2);

                /* ***** This is to adjust the position of the sticky header  ***** */
                mStickyHeader.setTranslationY(Math.max(0, mSv.getScrollY() - mStickyHeader.getTop()));

                /* This flips the positions of the FAB */
                int topFab = mAddToCart.getTop();
                int topSticky = mStickyHeader.getTop();
                int y = mSv.getScrollY();

                if (!mIsAnimating) {
                    if (topFab + topSticky < y) {
                        animateCartDown();
                    } else {
                        animateCartUp();
                    }
                } else {
                    //we need to remember what the request was. For example, if we are animating up, and there
                    //is a request for animating down, we don't want to just ignore it. We want to remember it
                    if (topFab + topSticky < y) {
                        mIsRequestDown = true;
                    } else {
                        mIsRequestUp = true;
                    }
                }
            }
        });
    }
}
