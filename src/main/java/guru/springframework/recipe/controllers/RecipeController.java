package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recipe")
@AllArgsConstructor
public class RecipeController {
    RecipeService recipeService;
    @RequestMapping("/show/{id}")
    public String getRecipeById(@PathVariable("id") String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }
}
