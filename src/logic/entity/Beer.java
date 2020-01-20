package logic.entity;


public class Beer {
    private String id;

    private String name;

    private BeerType type;

    private BeerColor color;

    private boolean filtered;

    private float alcoholContent;

    private float pricePerLiter;

    private String description;

    private Recipe recipe;

    public Beer(String id) {
    }

    public String getId() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.id;
    }

    public String getName() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.name;
    }

    public void setName(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.name = value;
    }

    public BeerType getType() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.type;
    }

    public void setType(BeerType value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.type = value;
    }

    public BeerColor getColor() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.color;
    }

    public void setColor(BeerColor value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.color = value;
    }

    public boolean isFiltered() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.filtered;
    }

    public void setFiltered(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.filtered = value;
    }

    public float getAlcoholContent() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.alcoholContent;
    }

    public void setAlcoholContent(float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.alcoholContent = value;
    }

    public float getPricePerLiter() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.pricePerLiter;
    }

    public void setPricePerLiter(float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.pricePerLiter = value;
    }

    public String getDescription() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.description;
    }

    public void setDescription(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.description = value;
    }

    public Recipe getRecipe() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.recipe;
    }

    public void setRecipe(Recipe value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.recipe = value;
    }

}
