package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (nonNull(unitOfMeasureCommand)) {
            final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setDescription(unitOfMeasureCommand.getDescription());
            unitOfMeasure.setId(unitOfMeasureCommand.getId());
            return unitOfMeasure;
        }
        return null;
    }
}
