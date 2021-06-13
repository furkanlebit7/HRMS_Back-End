package kodlamaio.hrms.core.utilities.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoConvertManager implements DtoConvertService{
	

	private ModelMapper modelMapper;
	
	@Autowired
	public DtoConvertManager() {
		super();
		this.modelMapper = new ModelMapper();
	}

	@Override
	public <S, T> List<T> dtoConverter(List<S> s, Class<T> targetClass) {
		return s.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}

	@Override
	public <T> Object dtoClassConverter(Object source, Class<T> baseClass) {
		return modelMapper.map(source,baseClass);
	}

}
