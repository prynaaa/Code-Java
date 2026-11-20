package pojos;

import annotations.HtmlElement;
import annotations.HtmlShow;

import java.util.Objects;

@HtmlShow
public class Car {
    @HtmlElement(name = "Modellnname", required = true)
    private String modelName;
    @HtmlElement(name = "Leistung", required = true)
    private int power;
    private boolean isElectic;

    public Car(String modelName, int power, boolean isElectic) {
        this.modelName = modelName;
        this.power = power;
        this.isElectic = isElectic;
    }

    public void setElectic(boolean isElectic) {
        this.isElectic = isElectic;
    }
    public String getModelName() {
        return modelName;
    }
    public int getPower() {
        return power;
    }
    public boolean isElectic() {
        return isElectic;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public boolean canEqual(Object object) {
        return object instanceof Car;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return power == car.power && isElectic == car.isElectic && Objects.equals(modelName, car.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, power, isElectic);
    }

    @Override
    public String toString() {
        return "Car{" +
                "modelName='" + modelName + '\'' +
                ", power=" + power +
                ", isElectic=" + isElectic +
                '}';
    }
}
