package cll.pf.com.livecll.ui;

import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.listener.ICallBack;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.router.RouterPath;
import cll.pf.com.livecll.utils.PhoneFormatCheckUtils;
import cll.pf.com.livecll.vo.CllUser;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

@CllRouter(value = ConstantPath.LOGIN_INDEX)
public class LoginActivity extends BaseActivity{
    private EditText mEtPhone, mEtCode;
    private TextView mTvCode;
    private Button mBtLogin, mBtWeChat;

    @Override
    public void initView() {
        // TODO: 2019-06-06 微信登录按钮UI 暂时不做了 微信这货限制太死
        setContentView(R.layout.activity_login);
        mEtCode = findViewById(R.id.et_code);
        mEtPhone = findViewById(R.id.et_phone);
        mTvCode = findViewById(R.id.tv_code);
        mBtLogin = findViewById(R.id.btn_login);
        mBtWeChat = findViewById(R.id.btn_login_wechat);
        mTvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onEvent(LoginActivity.this, "event_send_code");
                String phone = String.valueOf(mEtPhone.getText());
                // TODO: 2019-06-05 手机号合法验证
                if (!PhoneFormatCheckUtils.isPhoneLegal(phone)) {
                    Snackbar.make(mTvCode, "手机号码不正确", Snackbar.LENGTH_LONG).show();
                    return;
                }
                sendCode(phone, new ICallBack() {
                    @Override
                    public void success() {
                        mDownTimer.start();
                    }

                    @Override
                    public void fail() {
                        Snackbar.make(mTvCode, "发送验证码失败", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onEvent(LoginActivity.this, "event_login");
                final String phone = String.valueOf(mEtPhone.getText());
                final String code = String.valueOf(mEtCode.getText());
                // 无需验证验证码 登录接口包含验证功能
                login(phone, code, new ICallBack() {
                    @Override
                    public void success() {
                        writePreferences("phone", phone);
//                        Intent intent = new Intent(LoginActivity.this, TabActivity.class);
//                        startActivity(intent);
                        RouterPath.getInstance().navigation(LoginActivity.this,
                                ConstantPath.TAB_INDEX);
                        finish();
                    }

                    @Override
                    public void fail() {
                        //207
                    }
                });
            }
        });
        mBtWeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, TabActivity.class);
//                startActivity(intent);
                RouterPath.getInstance().navigation(LoginActivity.this, ConstantPath.TAB_INDEX);
            }
        });
        mEtPhone.addTextChangedListener(new EditTextWatch(mEtCode));
        mEtCode.addTextChangedListener(new EditTextWatch(mEtPhone));
    }

    @Override
    public void initData() {
        String phone = readPreferences("phone");
        if (!TextUtils.isEmpty(phone)) {
//            Intent intent = new Intent(LoginActivity.this, TabActivity.class);
//            startActivity(intent);
            RouterPath.getInstance().navigation(LoginActivity.this, ConstantPath.TAB_INDEX);
            finish();
        }
    }

    private CountDownTimer mDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            mTvCode.setText(getString(R.string.login_code, l/1000));
            mTvCode.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGrey));
            mBtLogin.setEnabled(true);
        }

        @Override
        public void onFinish() {
            mTvCode.setText(getString(R.string.login_send_code));
            mTvCode.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownTimer.cancel();
    }

    class EditTextWatch implements TextWatcher {
        EditText mEditText;

        EditTextWatch(EditText editText) {
            mEditText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0 && mEditText.getText().length() > 0) {
                mBtLogin.setEnabled(true);
            } else {
                mBtLogin.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    /**
     * 发送验证码
     */
    private void sendCode(String phone, final ICallBack callBack){
        BmobSMS.requestSMSCode(phone, getString(R.string.login_ver_code_name), new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    callBack.success();
                } else {
                    callBack.fail();
                }
            }
        });
    }

    /**
     * 验证验证码
     * @param phone
     * @param code
     */
    private void verifyCode(String phone, String code, final ICallBack callBack){
        BmobSMS.verifySmsCode(phone, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBack.success();
                } else {
                    callBack.fail();
                }
            }
        });
    }

    private void login(final String phone, final String code, final ICallBack callBack) {
        CllUser user = new CllUser();
        //设置手机号码（必填）
        user.setMobilePhoneNumber(phone);
        //设置用户名，如果没有传用户名，则默认为手机号码
        user.setUsername(phone);
        //设置用户密码
        user.setPassword("");
        CllUser.signOrLoginByMobilePhone(phone, code, new LogInListener<CllUser>() {
            @Override
            public void done(CllUser u, BmobException e) {
                if (e == null) {
                    callBack.success();
                } else {
                    callBack.fail();
                }
            }
        });
    }
}
