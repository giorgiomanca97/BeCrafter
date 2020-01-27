package logic.entity;


public class Beer {
    private String id;
    private String name;
    private BeerType type;
    private BeerColor color;
    private BeerFiltering filtering;
    private float alcoholContent;
    private float pricePerLiter;
    private String description;
    private Recipe recipe;

    
    public Beer(String id) {
    	this.id = id;
    	this.name = "";
    	this.type = null;
    	this.color = null;
    	this.filtering = null;
    	this.alcoholContent = 0;
    	this.pricePerLiter = 0;
    	this.description = "";
    	this.recipe = null;
    }

    
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeerType getType() {
        return this.type;
    }

    public void setType(BeerType type) {
        this.type = type;
    }

    public BeerColor getColor() {
        return this.color;
    }

    public void setColor(BeerColor color) {
        this.color = color;
    }

    public BeerFiltering getFiltering() {
        return this.filtering;
    }

    public void setFiltered(BeerFiltering filtering) {
        this.filtering = filtering;
    }

    public float getAlcoholContent() {
        return this.alcoholContent;
    }

    public void setAlcoholContent(float perc) {
        this.alcoholContent = perc;
    }

    public float getPricePerLiter() {
        return this.pricePerLiter;
    }

    public void setPricePerLiter(float price) {
        this.pricePerLiter = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
    	return "Beer: " + id + " " + name + " " + type.toString() + " " + color.toString() + " " + filtering.toString() + " " + alcoholContent + " " + pricePerLiter + " " + recipe.getId();
    }
}
