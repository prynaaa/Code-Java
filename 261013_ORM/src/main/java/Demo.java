public class Demo {
    @Noah
    public void methodWithAnnotations() {
        System.out.println("Method with annotations");
    }

    public void methodWithoutAnnotations() {
        System.out.println("Method without annotations");
    }

    @NoahParameter
    public void methodWithParameters() {
        System.out.println("Method with parameter");
    }

    @NoahParameter(name = "HAK", value = 20)
    public void methodWithParameterName() {
        System.out.println("Method with parameter");
    }
}
