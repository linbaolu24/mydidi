package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum State implements ICodeAble{
	VALID("0"), UNVALID("1");
	private String state;

	private State(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isValid(String str) {
		State[] state = State.values();
		for (int i = 0; i < state.length; i++) {
			if (!state[i].getState().equals(str)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getCode() {
		return getState();
	}
}
