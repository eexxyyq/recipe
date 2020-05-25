package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if (nonNull(recipe)) {
            final RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(recipe.getId());
            recipeCommand.setCookTime(recipe.getCookTime());
            recipeCommand.setPrepTime(recipe.getPrepTime());
            recipeCommand.setDescription(recipe.getDescription());
            recipeCommand.setDirections(recipe.getDirections());
            recipeCommand.setDifficulty(recipe.getDifficulty());
            recipeCommand.setServings(recipe.getServings());
            recipeCommand.setSource(recipe.getSource());
            recipeCommand.setUrl(recipe.getUrl());
            recipeCommand.setNotes(notesToNotesCommand.convert(recipe.getNotes()));


            if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
                recipe.getIngredients()
                        .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
            }
            if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
                recipe.getCategories()
                        .forEach(category -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
            }

            return recipeCommand;
        }
        return null;
    }
}
