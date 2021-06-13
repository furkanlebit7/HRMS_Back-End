package kodlamaio.hrms.core.utilities.converters;

import java.util.List;

public interface DtoConvertService {
	<S, T> List<T> dtoConverter(List<S> s, Class<T> targetClass);
	public <T> Object dtoClassConverter(Object source,Class<T> baseClass);
}
