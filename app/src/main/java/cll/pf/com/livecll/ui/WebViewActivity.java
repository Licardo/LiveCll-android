package cll.pf.com.livecll.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebUIControllerImplBase;
import com.just.agentweb.DefaultWebClient;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.constant.OthersAppId;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.utils.ShareUtils;

@CllRouter(value = ConstantPath.WEBVIEW_INDEX)
public class WebViewActivity extends BaseActivity {

    private AgentWeb mAgentWeb;
    private String mUrl, mTitle, mDes;
    private ShareUtils mUtils;

    @Override
    public void initView() {
        setContentView(R.layout.activity_web_view);
        findViewById(R.id.iv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.iv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUtils.shareUrl(WebViewActivity.this, mUrl, mTitle, mDes);
//                startActivity(new Intent(WebViewActivity.this, TinkerActivity.class));
            }
        });
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mUrl = bundle.getString("key");
        mTitle = bundle.getString("title");
        mDes = bundle.getString("des");
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((ViewGroup) findViewById(R.id.root), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(-1, 3)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setAgentWebUIController(new AgentWebUIControllerImplBase())
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(mUrl);

        mUtils = new ShareUtils();
        mUtils.RegisterApp(this, OthersAppId.WECHAT_ID);
    }

    @Override
    public void onBackPressed() {
        if (!mAgentWeb.back()) {
            super.onBackPressed();
        }
    }
}
