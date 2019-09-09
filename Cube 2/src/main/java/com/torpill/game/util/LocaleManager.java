package com.torpill.game.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class LocaleManager {

	private Locale currentLocale;
	private List<Locale> availableLocale = new ArrayList<Locale>();
	private Map<Locale, String> languages = new HashMap<Locale, String>();
	private Map<String, String> map = new HashMap<String, String>();
	private Map<String, String> properties = new HashMap<String, String>();
	private final Splitter splitterlang = Splitter.on('_').limit(2);
	private final Splitter splitterdata = Splitter.on('=').limit(2);

	public void init() {

		this.loadProperties();
		this.findAvailableLocale();

		if (this.isAvailable(Locale.getDefault())) {

			this.currentLocale = Locale.getDefault();

		} else {

			this.currentLocale = Locale.US;
		}

		String s = this.currentLocale.toLanguageTag().replace('-', '_');
		String s1 = "/lang/" + s + ".lang";

		File file = new File(LocaleManager.class.getResource(s1).getFile().replace("%20", " "));

		try {

			FileInputStream in = new FileInputStream(file);
			loadLocaleData(in);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void loadProperties() {

		File file = new File(LocaleManager.class.getResource("/lang/languages.properties").getFile().replace("%20", " "));

		try {

			FileInputStream in = new FileInputStream(file);
			Iterator<String> it = IOUtils.readLines(in, StandardCharsets.UTF_8).iterator();

			while (it.hasNext()) {

				String s2 = (String) it.next();

				if (!s2.isEmpty() && s2.charAt(0) != 35) {

					String[] astring = (String[]) Iterables.toArray(this.splitterdata.split(s2), String.class);

					if (astring != null && astring.length == 2) {

						String s3 = astring[0];
						String s4 = astring[1];
						this.properties.put(s3, s4);
					}
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void findAvailableLocale() {

		File dir = new File(LocaleManager.class.getResource("/lang/").getFile().replace("%20", " "));

		if (dir.exists()) {

			if (dir.isDirectory()) {

				FileFilter filter = new FileFilter() {

					public boolean accept(File path) {

						return path.getPath().endsWith(".lang");
					}
				};

				File[] langs = dir.listFiles(filter);

				for (File lang : langs) {

					String code = lang.getName().replace(".lang", "");

					String[] astring = (String[]) Iterables.toArray(this.splitterlang.split(code), String.class);

					if (astring != null && astring.length == 2) {

						String language = astring[0];
						String country = astring[1];
						Locale locale = new Locale(language, country);
						this.availableLocale.add(locale);
						this.languages.put(locale, this.properties.get(lang.getName()));
					}
				}
			}
		}
	}

	public void loadLocaleData(InputStream in) throws IOException {

		Iterator<String> it = IOUtils.readLines(in, StandardCharsets.UTF_8).iterator();

		while (it.hasNext()) {

			String s2 = (String) it.next();

			if (!s2.isEmpty() && s2.charAt(0) != 35) {

				String[] astring = (String[]) Iterables.toArray(this.splitterdata.split(s2), String.class);

				if (astring != null && astring.length == 2) {

					String s3 = astring[0];
					String s4 = astring[1];
					this.map.put(s3, s4);
				}
			}
		}
	}

	public Locale getLocale() {

		return this.currentLocale;
	}

	public void setLocale(Locale locale) {

		if (!this.isAvailable(locale)) {

			return;
		}

		this.currentLocale = locale;

		String s = this.currentLocale.toLanguageTag().replace('-', '_');
		String s1 = "/lang/" + s + ".lang";

		File file;

		try {

			file = new File(LocaleManager.class.getResource(s1).getFile().replace("%20", " "));

		} catch (NullPointerException e) {

			file = new File(LocaleManager.class.getResource("/lang/en_US.lang").getFile().replace("%20", " "));
		}

		try {

			FileInputStream in = new FileInputStream(file);
			this.map.clear();
			loadLocaleData(in);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public boolean isAvailable(Locale locale) {

		for (Locale localez : this.availableLocale) {

			if (locale.toLanguageTag().equals(localez.toLanguageTag())) {

				return true;
			}
		}

		return false;
	}

	private String translateKeyPrivate(String str) {

		String s1 = this.map.get(str);
		return s1 == null ? str : s1;
	}

	public String formatMessage(String str, Object[] params) {

		String s1 = this.translateKeyPrivate(str);

		try {

			return String.format(s1, params);

		} catch (IllegalFormatException illegalformatexception) {

			return "Format error: " + s1;
		}
	}
}
