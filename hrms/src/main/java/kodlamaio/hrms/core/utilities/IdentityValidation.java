package kodlamaio.hrms.core.utilities;

import kodlamaio.hrms.services.FakeMernis;

public class IdentityValidation {
	public static boolean isRealPerson(String tcNo) {
		return FakeMernis.validate(tcNo);
	}
}