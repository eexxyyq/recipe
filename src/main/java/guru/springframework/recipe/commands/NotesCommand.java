package guru.springframework.recipe.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesCommand {
    private Long id;
    private RecipeCommand recipe;
    private String recipeNotes;
}
