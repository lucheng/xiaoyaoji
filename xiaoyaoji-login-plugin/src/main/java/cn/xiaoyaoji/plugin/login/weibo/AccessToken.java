package cn.xiaoyaoji.plugin.login.weibo;

/**
 * @author: zhoujingjie
 * @Date: 16/9/2
 */
public class AccessToken extends cn.xiaoyaoji.plugin.login.AccessToken {
    private int expires_in;
    private String uid;

    public AccessToken() {
    }

    public AccessToken(String access_token, int expires_in, String uid) {
        super.setAccess_token(access_token);
        this.expires_in = expires_in;
        this.uid = uid;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
