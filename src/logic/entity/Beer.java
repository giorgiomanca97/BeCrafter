package logic.entity;


public class Beer {
    private String id;
    private String name;
    private BeerType type;
    private BeerColor color;
    private boolean filtered;
    private float alcoholContent;
    private float pricePerCL;
    private String description;
    private Recipe recipe;

    
    public Beer(String id) {
    	this.id = id;
    	this.name = "";
    	this.type = null;
    	this.color = null;
    	this.filtered = false;
    	this.alcoholContent = 0;
    	this.pricePerCL = 0;
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

    public boolean isFiltered() {
        return this.filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }

    public float getAlcoholContent() {
        return this.alcoholContent;
    }

    public void setAlcoholContent(float perc) {
        this.alcoholContent = perc;
    }

    public float getPricePerLiter() {
        return this.pricePerCL;
    }

    public void setPricePerLiter(float price) {
        this.pricePerCL = price;
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
    	return "Beer: " + id + " " + name + " " + type.toString() + " " + color.toString() + " " + alcoholContent + " " + pricePerCL + " " + recipe.getId();
    }
}
