package cll.pf.com.livecll;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class CllTinkerApplication extends TinkerApplication {
    public CllTinkerApplication() {
        super(
                ShareConstants.TINKER_ENABLE_ALL,
                "cll.pf.com.livecll.CllApplication",
                "com.tencent.tinker.loader.TinkerLoader",
                false);
    }
}
