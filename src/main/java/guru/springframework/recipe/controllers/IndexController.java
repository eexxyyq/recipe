package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class IndexController {
    private final RecipeService recipeService;
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {
        log.debug("Getting an index page");
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "index";
    }
}
