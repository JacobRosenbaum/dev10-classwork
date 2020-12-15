public class Balloon {
    private String color;
    private double psi;

    public Balloon(String color) {
        this.color = color;
    }


    // 1. Create a new method in the Balloon class.
    // Name: isExploded
    // Inputs: none
    // Output: boolean
    // Description: if the psi field is greater than 16.0, returns true.
    // Otherwise, returns false.

    public boolean isExploded(){
        if (getPsi() > 16.0){
            return true;
        }
        else{
            return false;
        }
    }

    public void inflate(){
        this.psi = this.psi + Math.random() * 5.0;
    }

    public String getColor(){
        return color;
    }

    public void setColor(){
        this.color = color;
    }
    // 2. Edit the getPsi method.
    // If the psi field is greater than 16.0, return Double.POSITIVE_INFINITY.
    // Otherwise, return psi.

    public double getPsi(){
        if (psi > 16.0) {
            return Double.POSITIVE_INFINITY;
        }
        else{
            return psi;
        }
    }

    public void setPsi(){
        this.psi = psi;
    }

}
