import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
public class PojoClass {

    private String name;
    private int id;

    public static class PojoClassBuilder {
        public PojoClass build() {
            System.out.println("Inside PojoClassBuilder");
            if (this.name == null) this.name = "Default Name";
            if (this.id == 0) this.id = 1234;
            return new PojoClass(this.name, this.id);
        }
    }
}
