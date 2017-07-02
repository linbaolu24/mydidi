package com.didi.druid;

import com.alibaba.druid.filter.config.ConfigTools;

public class PasswordGet {
	public static void main(String[] args) throws Exception {

		System.out.println(ConfigTools.decrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI98dtsIeL1NhbMzkVcxUP0Avs/13nG4OyB07nUpkW8tl7I5vfVymitnDrXtz2L2wkR/kxkgsAFMb1r06w9u6vUCAwEAAQ==",
				"LBOEktd1yMB+PU61tNka7kG2ktQrlfFDTIp3jn0Msy3bcHGWmUoP9BGFQTx4z1xS4b4YenlxI6Y5Syay6z/Yhg=="));
	}
}
