package ch.pforster.quiz.model;

public enum LearningLevel {
	NEW(0), BAD(1), OK(2), AVERAGE(3), GOOD(4), EXCELLENT(5);

	private int level;

	private LearningLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public LearningLevel next() {
		if (this.level == EXCELLENT.getLevel()) {
			return EXCELLENT;
		} else {
			int newLevel = this.level + 1;
			return LearningLevel.values()[newLevel];
		}
	}

	public LearningLevel previous() {
		if (this.level == NEW.getLevel()) {
			return NEW;
		} else {
			int newLevel = this.level - 1;
			return LearningLevel.values()[newLevel];
		}
	}
}
