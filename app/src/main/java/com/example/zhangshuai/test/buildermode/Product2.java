package com.example.zhangshuai.test.buildermode;

public class Product2 {
    private String partA;
    private String partB;
    private String partC;

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    public static class Builder2 {
        private Product2 product2 = new Product2();

        public Builder2 buildPartA(String partA) {
            product2.setPartA(partA);
            return this;
        }

        public Builder2 buildPartB(String partB) {
            product2.setPartB(partB);
            return this;
        }

        public Builder2 buildPartC(String partC) {
            product2.setPartC(partC);
            return this;
        }

        public Product2 build() {
            return product2;
        }
    }
}
