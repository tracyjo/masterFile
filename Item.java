/*package com.company;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by josephtracy on 5/23/16.
 */
/*
@Entity
@Table(name = "inventory")
public class Item implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="inventory_id")
    private Integer itemID;

    @Column(name="inventory_name")
    private String itemName;

    @Column(name="inventory_type")
    private String itemType;

        public Item(int aItemID, String aItemName, String aItemType) {
            this.itemID = aItemID;
            this.itemName = aItemName;
            this.itemType = aItemType;

        }

        public int getItemID(){
            return this.itemID;
        }

        public void setItemID(int itemId) {
            this.itemID = itemId;
        }

        public String getItemName() {
            return this.itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemType() {
            return this.itemType;
        }

        public void setItemType(String ItemType) {
            this.itemType = ItemType;
        }



        public String toItemString() {
            return "Item{" +
                    " item Id= " +        itemID +
                    " item Name='" +      itemName + '\'' +
                    ", item type='" +    itemType + '\'' +
                    '}';
        }

        public String toStringSimple() {
            return "Item = " + itemID + " " + itemName + ", " + itemType;
        }

    }
*/