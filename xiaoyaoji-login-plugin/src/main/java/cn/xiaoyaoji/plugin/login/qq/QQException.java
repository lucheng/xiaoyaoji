package cn.xiaoyaoji.plugin.login.qq;


import cn.xiaoyaoji.plugin.login.exception.ThirdlyException;

/**
 * @author zhoujingjie
 * @date 2016-07-28
 */
public class QQException extends ThirdlyException {
    public QQException() {
        super();
    }

    public QQException(String message) {
        super(message);
    }

    public QQException(String message, Throwable cause) {
        super(message, cause);
    }

    public QQException(Throwable cause) {
        super(cause);
    }

    protected QQException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
