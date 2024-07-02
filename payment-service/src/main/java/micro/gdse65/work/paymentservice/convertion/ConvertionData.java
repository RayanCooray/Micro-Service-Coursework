package micro.gdse65.work.paymentservice.convertion;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ConvertionData {
    // No additional data conversion needed for this service
    private final ModelMapper modelMapper;
    public <S, T> T mapTo(S source, Class<T> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }
    public <S, T> List<T> mapTo(List<S> sourceList, Class<T> destinationClass) {
        return sourceList
                .stream()
                .map(source -> modelMapper.map(source, destinationClass))
                .collect(Collectors.toList());
    }

//    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
//    public class ConvertionData {
//        private final ModelMapper modelMapper;
//    }
}
