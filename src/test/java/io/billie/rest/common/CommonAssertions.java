package io.billie.rest.common;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommonAssertions {
	public static void assertStatusCode(Integer actual, Integer expected) {
		assertThat("Status code does not match", actual ,equalTo(expected));
	}

}
