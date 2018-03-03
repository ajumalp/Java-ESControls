package com.es.Resource;

import java.net.URL;

public class Res {

	public static URL getResource(String aValue) {
		return Res.class.getResource(aValue);
	}

	static public class Messages {

	}

	static public class Images {
		static public class PNG {
			static public class Size16 {
				public static final URL DateTime = Res.getResource("/images/png/16x16/datetime.png");
				public static final URL PreYear = Res.getResource("/images/png/16x16/pre_year.png");
				public static final URL PreMonth = Res.getResource("/images/png/16x16/pre_month.png");
				public static final URL Today = Res.getResource("/images/png/16x16/today.png");
				public static final URL NextMonth = Res.getResource("/images/png/16x16/next_month.png");
				public static final URL NextYear = Res.getResource("/images/png/16x16/next_year.png");
			}
		}
	}
}
