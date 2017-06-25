package cn.com.didi.webBase.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.DomainMessageConstans;
import cn.com.didi.webBase.util.IAccountResolver;
import cn.com.didi.webBase.util.impl.AccountResolver;

public class AccessFilter extends OncePerRequestFilter implements Filter {
	private static final  Logger LOGGER=LoggerFactory.getLogger(AccessFilter.class);
	public String getIgnoreUrl() {
		return ignoreUrl;
	}

	public void setIgnoreUrl(String ignoreUrl) {
		this.ignoreUrl = ignoreUrl;
	}

	protected IAccountResolver resolver = new AccountResolver();
	protected String[] filters;
	protected String ignoreUrl;
	protected String notLoging;

	@Override
	protected void initFilterBean() {
		if (!StringUtils.isEmpty(ignoreUrl)) {
			String[] temps = ignoreUrl.split(";|,");
			for (int i = 0; i < temps.length; i++) {
				temps[i]=temps[i].trim();
				if (!temps[i].startsWith("/")) {
					temps[i] = "/" + temps[i];
				}
			}
			this.filters = temps;
		}
		IResult result = ResultFactory.error(DomainMessageConstans.CODE_USER_NOT_LOGIN, "用户未登录。");
		notLoging = JSON.toJSONString(result);

	}

	@Override
	public void destroy() {

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Long accountId = resolver.resolve(request);
		if (accountId != null) {
			filterChain.doFilter(request, response);
		} else {
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(notLoging);
		}

	}

	/**
	 * Can be overridden in subclasses for custom filtering control, returning
	 * {@code true} to avoid filtering of the given request.
	 * <p>
	 * The default implementation always returns {@code false}.
	 * 
	 * @param request
	 *            current HTTP request
	 * @return whether the given request should <i>not</i> be filtered
	 * @throws ServletException
	 *             in case of errors
	 */
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if (filters == null || filters.length == 0) {
			return false;
		}
		String str = request.getServletPath();
		if (StringUtils.isEmpty(str) || str.equals("/")) {
			str = request.getPathInfo();
		} else {
			String pathInfo = request.getPathInfo();
			if (StringUtils.isNotBlank(pathInfo)) {
				if (str.endsWith("/")) {
					str = str + pathInfo.substring(1);
				} else {
					str = str + pathInfo;
				}
			}
		}
		LOGGER.debug("过滤URL{}",str);
		for (String one : filters) {
			if (one.equals(str)) {
				return true;
			}
		}
		return false;
	}

}
