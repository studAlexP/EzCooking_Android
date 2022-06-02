package com.example.ezcooking.testRecipe

data class Recipe(
    val id: String,
    val title: String,
    val categorie: String,
    val images: List<String>,
    val ingredients: String,
    val steps: String
)

fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            id = "ChickenAvocado",
            title = "Chicken breast with avocado salad",
            categorie = "Salad",
            images = listOf("https://images.immediate.co.uk/production/volatile/sites/30/2020/08/recipe-image-legacy-id-654469_11-0a00ead.jpg?quality=90&webp=true&resize=300,272"),
            ingredients = "\n    1 skinless chicken breast\n" +
                    "    2 tsp olive oil (1 for the salad)\n" +
                    "    1 heaped tsp smoked paprika\n" +
                    "\n" +
                    "For the salad:" +
                    "\n" +
                    "    ½ small avocado , diced\n" +
                    "    1 tsp red wine vinegar\n" +
                    "    1 tbsp flat-leaf parsley , roughly chopped\n" +
                    "    1 medium tomato , chopped\n" +
                    "    half small red onion , thinly sliced",
            steps = "\n" +"\nSTEP 1" +
                    "\n" +
                    "Heat grill to medium. Rub the chicken all over with 1 tsp of the olive oil and the paprika. Cook for 4-5 mins each side until lightly charred and cooked through.\n" +
                    "\n" +
                    "STEP 2" +
                    "\n" +
                    "Mix the salad ingredients together, season and add the rest of the oil. thickly slice the chicken and serve with salad."
        ),

        Recipe(
            id = "TofuStirFry",
            title = "Tofu stir-fry",
            categorie = "Vegetarian",
            images = listOf("https://images.immediate.co.uk/production/volatile/sites/30/2022/01/Tofu-stir-fry-16f5101.jpg?quality=90&webp=true&resize=300,272"),
            ingredients = "   3 tbsp low-sodium soy sauce\n" +
                    "    1½ tbsp honey\n" +
                    "    1 tbsp white wine vinegar\n" +
                    "    300g tofu, cut into chunks\n" +
                    "    2 tbsp sunflower oil\n" +
                    "    2 garlic cloves, sliced\n" +
                    "    2cm piece ginger, sliced\n" +
                    "    2 carrots, sliced into thin batons\n" +
                    "    300g broccoli, cut into small florets\n" +
                    "    1 red pepper, sliced into strips\n" +
                    "    1 tsp cornflour\n" +
                    "\n" +
                    "To garnish\n" +
                    "\n" +
                    "    1 spring onion, sliced\n" +
                    "    2 tsp sesame seeds\n" +
                    "    small bunch of coriander, chopped",
            steps = "STEP 1\n" +
                    "\n" +
                    "Stir together the soy sauce, honey and vinegar in a bowl, then set aside.\n" +
                    "STEP 2\n" +
                    "\n" +
                    "Pat the tofu chunks dry, then season well. Heat half the oil in a wok or large frying pan over a high heat. Fry the tofu for 5 mins until golden, stirring occasionally. Add the garlic and ginger and fry for 1 min more. Tip the tofu mixture into a bowl, pour over half the soy dressing and set aside.\n" +
                    "STEP 3\n" +
                    "\n" +
                    "Add the remaining oil to the wok. Add the vegetables and cook for 5 mins until beginning to turn golden, then add a good splash of water and cook for 3-5 mins more until tender.\n" +
                    "STEP 4\n" +
                    "\n" +
                    "Stir the cornflour and 1 tbsp water into the remaining soy dressing, then tip into the wok, allow to bubble for 30 seconds, stirring to coat everything in the sauce. Sprinkle over the spring onion, sesame seeds and coriander. Serve with rice."
        )
    )
}