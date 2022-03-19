package com.spring.pointcut;

public class BehaviorImpl implements Behavior {

	@Override
	public void 잠자기() {
		System.out.println("하루종일 잡니다.");
	}

	@Override
	public void 공부하기() {
		System.out.println("조금만 공부합니다.");
	}

	@Override
	public void 밥먹기() {
		System.out.println("스테이크를 먹습니다.");
	}

	@Override
	public void 데이트() {
		System.out.println("데이트좀 하고 싶네.");
	}

	@Override
	public void 운동() {
		System.out.println("운동은 Cloeting.");
	}

	@Override
	public void 놀기() {
		System.out.println("간만에 메이플 하기");
	}

	@Override
	public void 정신수양() {
		System.out.println("독서는 마음의 양식");
	}

}
