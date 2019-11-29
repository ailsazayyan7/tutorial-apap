package apap.tutorial.gopud.service;

import apap.tutorial.gopud.rest.ResultsDetail;
import reactor.core.publisher.Mono;

public interface RecipeRestService {
    Mono<ResultsDetail> getRecipe(String excludeIngredient);
}
