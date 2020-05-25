package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.NotesCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final NotesCommandToNotes notesCommandToNotes;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (nonNull(recipeCommand)) {
            final Recipe recipe = new Recipe();
            recipe.setId(recipeCommand.getId());
            recipe.setCookTime(recipeCommand.getCookTime());
            recipe.setPrepTime(recipeCommand.getPrepTime());
            recipe.setDescription(recipeCommand.getDescription());
            recipe.setDifficulty(recipeCommand.getDifficulty());
            recipe.setDirections(recipeCommand.getDirections());
            recipe.setServings(recipeCommand.getServings());
            recipe.setSource(recipeCommand.getSource());
            recipe.setUrl(recipeCommand.getUrl());
            recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));

            if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
                recipeCommand.getCategories()
                        .forEach(category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
            }

            if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
                recipeCommand.getIngredients()
                        .forEach(ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
            }
            return recipe;
        }
        return null;
    }
}
