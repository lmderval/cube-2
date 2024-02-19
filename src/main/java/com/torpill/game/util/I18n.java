package com.torpill.game.util;

import java.util.Locale;
import java.util.Map;

public class I18n {

	private static final LocaleManager manager;

	static {

		manager = new LocaleManager();
	}

	public static void init() {

		manager.init();
	}

	public static Locale getLocale() {

		return manager.getLocale();
	}

	public static void setLocale(Locale locale) {

		manager.setLocale(locale);
	}

	public static void setLocale(String language, String country) {

		setLocale(new Locale(language, country));
	}

	public static String format(String str, Object... params) {

		return manager.formatMessage(str, params);
	}
	
	public static Map<Locale, String> getLanguages() {
		
		return manager.getLanguages();
	}
}
