package guru.springframework.recipe.commands;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnitOfMeasureCommand {
    private Long id;
    private String description;
}
