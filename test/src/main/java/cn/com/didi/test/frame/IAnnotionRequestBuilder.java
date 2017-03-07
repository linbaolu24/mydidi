package cn.com.didi.test.frame;

import cn.com.didi.test.annotion.Uri;

public interface IAnnotionRequestBuilder {
	public void url(Uri uri);

	public void file(Uri uri);

	public Request build();
}
