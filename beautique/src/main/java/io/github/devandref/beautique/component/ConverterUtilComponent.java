package io.github.devandref.beautique.component;

import org.modelmapper.ModelMapper;

public class ConverterUtilComponent <S, T> {

    private final ModelMapper modelMapper;
    private final Class<S> sourceType;
    private final Class<T> targetType;

    public ConverterUtilComponent(Class<S> sourceType, Class<T> targetType) {
        this.modelMapper = new ModelMapper();
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    public T convertToTarget(S source) {
        return modelMapper.map(source, targetType);
    }

    public S convertToSource(T target) {
        return modelMapper.map(target, sourceType);
    }

}
