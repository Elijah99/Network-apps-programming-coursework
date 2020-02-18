package entity;

public class Admin extends AbstractUser {
    private boolean isSuper;

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    public Admin() {
    }

    public Admin(boolean isSuper) {
        this.isSuper = isSuper;
    }
}
