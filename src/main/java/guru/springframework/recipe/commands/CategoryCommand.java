package guru.springframework.recipe.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCommand {
    private Long id;
    private String description;
}
