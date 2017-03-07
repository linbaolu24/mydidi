package cn.com.didi.thirdExt.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import cn.com.didi.core.resource.IFileStorage;
import cn.com.didi.core.resource.IResource;

public class QiniuFileStorage implements IFileStorage {
	private static final Logger LOGGER = LoggerFactory.getLogger(QiniuFileStorage.class);
	private String accessKey;
	private String secretKey;
	private String bucket;
	private Integer zone = 0;
	Configuration cfg;
	UploadManager uploadManager;
	private String downLoadUrl = "http://devtools.qiniu.com";
	private boolean individual = true;
	// private boolean

	public void init() {
		Zone tZone = null;
		switch (zone) {
		case 0:
			tZone = Zone.zone0();
			break;
		case 1:
			tZone = Zone.zone1();
			break;
		case 2:
			tZone = Zone.zone2();
			break;
		case 3:
			tZone = Zone.zoneNa0();
			break;
		default:
			tZone = Zone.zone0();
		}

		if (uploadManager == null) {
			if (cfg == null) {
				cfg = new Configuration(tZone);
			}
			uploadManager = new UploadManager(cfg);
		}
	}

	@Override
	public String storeFile(IResource<InputStream> in, long size, String path) throws IOException {
		String str = null;
		try {

			String key = null;
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);
			Response response = uploadManager.put(in.getResource(), key, upToken, null, null);
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			str = contribute(putRet, path, auth);
		} catch (QiniuException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new IOException("上传文件失败。");
		}
		return str;
	}

	public String contribute(DefaultPutRet putRet, String path, Auth auth) throws UnsupportedEncodingException {
		String domainOfBucket = downLoadUrl;
		String encodedFileName = URLEncoder.encode(putRet.hash, "utf-8");
		String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
		;
		if (individual) {
			long expireInSeconds = 3600;// 1小时，可以自定义链接过期时间
			finalUrl = auth.privateDownloadUrl(finalUrl, expireInSeconds);
		}
		return finalUrl;

	}

	@Override
	public IResource<InputStream> getFile(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public Integer getZone() {
		return zone;
	}

	public void setZone(Integer zone) {
		this.zone = zone;
	}

	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

	public UploadManager getUploadManager() {
		return uploadManager;
	}

	public void setUploadManager(UploadManager uploadManager) {
		this.uploadManager = uploadManager;
	}

	public String getDownLoadUrl() {
		return downLoadUrl;
	}

	public void setDownLoadUrl(String downLoadUrl) {
		this.downLoadUrl = downLoadUrl;
	}

	public boolean isIndividual() {
		return individual;
	}

	public void setIndividual(boolean individual) {
		this.individual = individual;
	}
}
