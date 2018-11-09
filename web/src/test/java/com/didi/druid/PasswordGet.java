package com.didi.druid;

import com.alibaba.druid.filter.config.ConfigTools;

public class PasswordGet {
    public static void main(String[] args) throws Exception {

        System.out.println(ConfigTools.decrypt(
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI98dtsIeL1NhbMzkVcxUP0Avs/13nG4OyB07nUpkW8tl7I5vfVymitnDrXtz2L2wkR/kxkgsAFMb1r06w9u6vUCAwEAAQ==",
                "LBOEktd1yMB+PU61tNka7kG2ktQrlfFDTIp3jn0Msy3bcHGWmUoP9BGFQTx4z1xS4b4YenlxI6Y5Syay6z/Yhg=="));

        System.out.println(ConfigTools.decrypt(
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAL6YIfJNAn1CyiVVUEUxHdN+dR1i49grchSDKvVU8qMY6OAV3Uox3Ehc6RLnzGoucCJ5o6rbUXPjT/6zxtqOHH0CAwEAAQ==",
                "h4HoEDOeYO+auNNVCgN5l42vrTTflgpS8j/8WNZIipKZl1FuY3ua/X2zD7TE5eLUKwhX46F9nfvppqiNqoCVeA=="));

        // 设置密码
        String password = "12345";
        String[] arr = ConfigTools.genKeyPair(512);
        // 获得
        System.out.println("私有key值:" + arr[0]);
        System.out.println("公共key值:" + arr[1]);
        String pwd = ConfigTools.encrypt(arr[0], password);
        System.out.println("加密后密码:" + pwd);

        // 获得解码密码
        System.out.println(ConfigTools.decrypt(arr[1], pwd));

        // 非key值加密
        String pwd1 = ConfigTools.encrypt("12345");
        System.out.println(pwd1);
        // 解密
        System.out.println(ConfigTools.decrypt(pwd1));

    }
}
