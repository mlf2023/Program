package com.ztt.mt.config;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "9021000122682360";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDITBxSYhi4uSEbHeHZrPUSFw+C1FZtSqXylUGuP1q2Tx2h/iLLbmkHOoSeoJh+dw7dfyC5LnrX5TU1PvGUB7W0eNCJ8ifMAKmepgHhkJ8RNSnU24P4W+83MeESQpflKWp+xGgFNsdEEeyxEs30slbysV7SDHYxIK2g2DidoVpPfFoyIbY7e5oZFKT12Px8Cy7tLFd6Cq0ucbpUdCtO1UPAhG3zr6nuSJsT+LRMvxpzb/3JoMUcsZ42W3EZQZg15T66Cricfy6aXXtepdTD70mmkIhN7bwh693bEuIT1rGqJ+bLoKYQBaiuKn2CRGiqP7IgX3P7PF4chkD5aEEEA+5ZAgMBAAECggEBAKLZXt2MbFUkD8qgTRRSDx8aKL5SLcLGQo4s1C37zKEVSh5/JYMIzdPWvCTMpa+M1UU5Kz3kkHQgI1abSob0jcOhxsuhWsH+d+pJRpZCo1G2S8CIiY/QD3psz3WtNxpWxgMzZO94TiMF/88oXtNofFDt7Q4Sm7Xtv71ajw16NpcQLwvAnuUL35gNJjAKecLYV5b5uxA/8VAf/H8DL1bSeaWEnJZhkYVqw+1cIprau62ycxjiwPQ91k15k7r1SeYcCNs/KX8GUTrd/Oounqv6Rq7c7i00YfcSWSmUQaK8YGD16JmbOy8S5IZ678fB0fsCPM12BvbrVugTdGUyqKRCxkECgYEA7diKEwomuNtAcTv24qJjWU7PHmUWkBg0TZ7+fECyVbM+B+jJqUxcfygxxmgWNkXsoA9r1DxmBzU2g84vQTXNLsItD+2vq0Cxoyow/3S6xU4q2eAjyv+7NrnxgHICheXFMWV4gvXYcNdBB+flmgbrEWN3EWOp2B5IuZGlensjveUCgYEA15XhTkLnDBQLv/jCta5KSjJpW9tEPoaF7ioh4gyvt5HKOPF7cLwTiZikAr/VAM9pph+5tSvdiWHjGaoeLIND5zEJ5QkO5GAJPnY9tmn4kF7CmCUr+c2rNBeXcKB5UEZEfaGi+S1dfr8ajMkyRXA10pwIIX82XYriR9lXqoBJx2UCgYEAnblvNtkQfJ3Iyk59liUhxOOiLgHTSJMEfETndfZhTLukcxJISOC+pgQ9ndkAgdU06Z/QXdn/vt/m2tksNQ48lrllsJ5qVCJ+qjkcbQs3oAgKGpmsor2bpNsB76+rRKfucKddl1r4wTu9gESj2dSkpc9T8lApHYxXpdeT6Pkn5pECgYEAoauRYbpEQEeMJDOt+McyiP/TCQKe4/DH4ToRXM68u4IGT7uwIXO5hxdD75fiaZAhn2XtQ1MY+JzCzQ1JQYJS2r5ouCyZ0WO/qurLDs/bipC1h5lgqB23RQJoHBsLvspjNH+RkyGVGqa/h1Kec3pZso42fsBCidXMKatw1bkNGw0CgYEA1gkpc0ocRF6HCIdoeu9k4nBlgNYFMWYujlF2GfP8z2GxR40WXGQinjV3mQNGCfJQfvJI81VIqn5Bsk/mVbQPttZn4x8JneUuxF2nS0cFjr/c9iuj3wrIETw/+JcVSEh62AuPHQWcOKcFsKvvf/+eeuzm/1A6+k5sccGkgaNw1EA=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:8080/order/afterPayOrder";
    // 请求网关地址
    public static String URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt8OtdUXRqXmQj8QMrFCE3ZenGuhEghMSzTupg3nRLOj5y1M3jb3X50JNqIYE7+3LwxlqjXjWS5lGSSEH/H3mLMbz0/PMj8ghUp7zkXVVrYZMf408KXf1f76OhV+lEsOnrku58jGmTQdYIB3NWglstTTnvBSBjr0lX2u8y2R0YsGhS5pWigCVonWL7wPapTMjKqS4ZvzLNtw49zHOylq3prgXFCvNB5uixoFV26Y7BOnuyWHCLw9fa9Zc31gQRaTtawYGjF98mxn8/9n2A51jSMb30prGUAXWg/+vxHcocTIWa4crG51/9oqoouFSLapZDZAY/Ma9oJ5GHu02hY11PQIDAQAB";
    // 日志记录目录定义在 logFile 中
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}

