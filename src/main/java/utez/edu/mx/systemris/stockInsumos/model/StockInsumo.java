package utez.edu.mx.systemris.stockInsumos.model;

public class StockInsumo {
    private Long id;
    private String insumo;
    private int stock;
    private String description;
    private boolean available;

    public StockInsumo() {
    }

    public StockInsumo(Long id, String insumo, int stock, String description, boolean available) {
        this.id = id;
        this.insumo = insumo;
        this.stock = stock;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
