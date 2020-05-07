package guru.springframework.recipe.bootstrap;

import guru.springframework.recipe.domain.*;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    private List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        //get units of measure
        Map<String, UnitOfMeasure> mapOUM = getUnitsOfMeasure();
        // get categories
        Map<String, Category> categoryMap = getCategories();
        //guacamole recipe
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("""
                1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon.
                2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)
                3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.        
                  Add the chopped onion, cilantro, black pepper, and chilies. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.
                  Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.
                  Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.
                4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.
                """);
        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("""
                Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!
                Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.
                Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.
                Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.                
                """);
        guacamole.setNotes(guacamoleNotes);
        guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), mapOUM.get("Each")));
        guacamole.addIngredient(new Ingredient("kosher salt", new BigDecimal(".5"), mapOUM.get("Teaspoon")));
        guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), mapOUM.get("Tablespoon")));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), mapOUM.get("Tablespoon")));
        guacamole.addIngredient(new Ingredient("serrano chilies, stems and seeds removed, minced", new BigDecimal(2), mapOUM.get("Each")));
        guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), mapOUM.get("Tablespoon")));
        guacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), mapOUM.get("Dash")));
        guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), mapOUM.get("Each")));

        guacamole.getCategories().add(categoryMap.get("American"));
        guacamole.getCategories().add(categoryMap.get("Mexican"));

        recipes.add(guacamole);
        //end guacamole
        //tacos
        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setPrepTime(9);
        tacos.setCookTime(20);
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setDirections("""
                1 Prepare a gas or charcoal grill for medium-high, direct heat.                             
                2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.                                
                  Set aside to marinate while the grill heats and you prepare the rest of the toppings.
                3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.
                4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.                                
                  Wrap warmed tortillas in a tea towel to keep them warm until serving.                                
                5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.
                """);
        Notes tacosNote = new Notes();
        tacosNote.setRecipeNotes("""
                We have a family motto and it is this: Everything goes better in a tortilla.               
                Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.
                Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!                
                First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.             
                Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!
                """);
        tacos.setNotes(tacosNote);
        tacos.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), mapOUM.get("Tablespoon")));
        tacos.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), mapOUM.get("Teaspoon")));
        tacos.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), mapOUM.get("Teaspoon")));
        tacos.addIngredient(new Ingredient("sugar", new BigDecimal(1), mapOUM.get("Teaspoon")));
        tacos.addIngredient(new Ingredient("salt", new BigDecimal(".5"), mapOUM.get("Teaspoon")));
        tacos.addIngredient(new Ingredient("garlic, finely chopped", new BigDecimal(1), mapOUM.get("Each")));
        tacos.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), mapOUM.get("Tablespoon")));
        tacos.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), mapOUM.get("Tablespoon")));
        tacos.addIngredient(new Ingredient("olive oil", new BigDecimal(2), mapOUM.get("Tablespoon")));
        tacos.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(5), mapOUM.get("Each")));
        tacos.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), mapOUM.get("Each")));
        tacos.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), mapOUM.get("Cup")));
        tacos.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), mapOUM.get("Each")));
        tacos.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(2), mapOUM.get("Each")));
        tacos.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), mapOUM.get("Pint")));
        tacos.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), mapOUM.get("Each")));
        tacos.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), mapOUM.get("Each")));
        tacos.addIngredient(new Ingredient("sour cream", new BigDecimal(".5"), mapOUM.get("Cup")));
        tacos.addIngredient(new Ingredient("milk", new BigDecimal(".25"), mapOUM.get("Cup")));
        tacos.addIngredient(new Ingredient("lime", new BigDecimal(1), mapOUM.get("Each")));

        tacos.getCategories().add(categoryMap.get("American"));
        tacos.getCategories().add(categoryMap.get("Fast Food"));

        recipes.add(tacos);
        //end tacos

        return recipes;
    }

    private Map<String, Category> getCategories() {
        Map<String, Category> resultMap = new HashMap<>();
        List<Optional<Category>> optionalList = new ArrayList<>();
        List<String> descriptionList = List.of("American", "Italian", "Mexican", "Fast Food");
        descriptionList.forEach(s -> optionalList.add(categoryRepository.findByDescription(s)));
        for (Optional<Category> category : optionalList) {
            if (category.isPresent()) {
                resultMap.put(category.get().getDescription(), category.get());
            } else {
                throw new RuntimeException("Expected category not found.");
            }
        }
        return resultMap;
    }

    private Map<String, UnitOfMeasure> getUnitsOfMeasure() {
        Map<String, UnitOfMeasure> mapUOM = new HashMap<>();
        List<Optional<UnitOfMeasure>> listOptional = new ArrayList<>();
        List<String> listOfDescription = List.of("Teaspoon", "Tablespoon", "Dash", "Each", "Cup", "Pint");
        listOfDescription.forEach(s -> listOptional.add(unitOfMeasureRepository.findByDescription(s)));
        for (Optional<UnitOfMeasure> unitOfMeasure : listOptional) {
            if (unitOfMeasure.isPresent()) {
                mapUOM.put(unitOfMeasure.get().getDescription(), unitOfMeasure.get());
            } else {
                throw new RuntimeException("Expected UOM not found");
            }
        }
        return mapUOM;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getAllRecipes());
        log.debug("Loading bootstrap data...");
    }
}
