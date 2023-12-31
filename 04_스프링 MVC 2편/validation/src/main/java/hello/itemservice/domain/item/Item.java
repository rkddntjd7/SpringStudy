package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
public class Item {

    //@NotNull(groups = UpdateFCheck.class) //수정 요구사항
    private Long id;

    //@NotBlank(groups = {SaveCheck.class, UpdateFCheck.class})
    private String itemName;

    //@NotNull(groups = {SaveCheck.class, UpdateFCheck.class})
    //@Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateFCheck.class})
    private Integer price;

    //@NotNull(groups = {SaveCheck.class, UpdateFCheck.class})
    //@Max(value = 9999, groups = {SaveCheck.class}) //수정 요구사항 추가
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
